package kyh.tam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kyh.tam.context.ApplicationContextListener;
import kyh.tam.dao.BoardDao;
import kyh.tam.dao.MemberDao;
import kyh.tam.dao.PhotoBoardDao;
import kyh.tam.dao.PhotoFileDao;
import kyh.tam.dao.StuffDao;
import kyh.tam.dao.mariadb.BoardDaoImpl;
import kyh.tam.dao.mariadb.MemberDaoImpl;
import kyh.tam.dao.mariadb.StuffDaoImpl;
import kyh.tam.servlet.BoardAddServlet;
import kyh.tam.servlet.BoardDeleteServlet;
import kyh.tam.servlet.BoardDetailServlet;
import kyh.tam.servlet.BoardListServlet;
import kyh.tam.servlet.BoardUpdateServlet;
import kyh.tam.servlet.MemberAddServlet;
import kyh.tam.servlet.MemberDeleteServlet;
import kyh.tam.servlet.MemberDetailServlet;
import kyh.tam.servlet.MemberListServlet;
import kyh.tam.servlet.MemberSearchServlet;
import kyh.tam.servlet.MemberUpdateServlet;
import kyh.tam.servlet.PhotoBoardAddServlet;
import kyh.tam.servlet.PhotoBoardDeleteServlet;
import kyh.tam.servlet.PhotoBoardDetailServlet;
import kyh.tam.servlet.PhotoBoardListServlet;
import kyh.tam.servlet.PhotoBoardUpdateServlet;
import kyh.tam.servlet.Servlet;
import kyh.tam.servlet.StuffAddServlet;
import kyh.tam.servlet.StuffDeleteServlet;
import kyh.tam.servlet.StuffDetailServlet;
import kyh.tam.servlet.StuffListServlet;
import kyh.tam.servlet.StuffUpdateServlet;
import kyh.tam.sql.ConnectionProxy;
import kyh.tam.util.ConnectionFactory;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new LinkedHashSet<>();
  Map<String, Object> context = new LinkedHashMap<>();
  Map<String, Servlet> servletMap = new HashMap<>();

  ExecutorService executorService = Executors.newCachedThreadPool();
  boolean shutdown = false;

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  private void notifyApplicationInitialized() throws Exception {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }

  private void notifyApplicationDestroyed() throws Exception {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }

  public void service() throws Exception {
    notifyApplicationInitialized();

    ConnectionFactory connectionFactory = (ConnectionFactory) context.get("connectionFactory");

    BoardDao boardDao = (BoardDaoImpl) context.get("boardDao");
    MemberDao memberDao = (MemberDaoImpl) context.get("memberDao");
    StuffDao stuffDao = (StuffDaoImpl) context.get("stuffDao");
    PhotoBoardDao photoBoardDao = (PhotoBoardDao) context.get("photoBoardDao");
    PhotoFileDao photoFileDao = (PhotoFileDao) context.get("photoFileDao");

    servletMap.put("/board/list", new BoardListServlet(boardDao));
    servletMap.put("/board/add", new BoardAddServlet(boardDao));
    servletMap.put("/board/detail", new BoardDetailServlet(boardDao));
    servletMap.put("/board/update", new BoardUpdateServlet(boardDao));
    servletMap.put("/board/delete", new BoardDeleteServlet(boardDao));

    servletMap.put("/stuff/list", new StuffListServlet(stuffDao));
    servletMap.put("/stuff/add", new StuffAddServlet(stuffDao));
    servletMap.put("/stuff/detail", new StuffDetailServlet(stuffDao));
    servletMap.put("/stuff/update", new StuffUpdateServlet(stuffDao));
    servletMap.put("/stuff/delete", new StuffDeleteServlet(stuffDao));

    servletMap.put("/member/list", new MemberListServlet(memberDao));
    servletMap.put("/member/add", new MemberAddServlet(memberDao));
    servletMap.put("/member/detail", new MemberDetailServlet(memberDao));
    servletMap.put("/member/update", new MemberUpdateServlet(memberDao));
    servletMap.put("/member/delete", new MemberDeleteServlet(memberDao));
    servletMap.put("/member/search", new MemberSearchServlet(memberDao));

    servletMap.put("/photoboard/list", new PhotoBoardListServlet(photoBoardDao, stuffDao));
    servletMap.put("/photoboard/detail", new PhotoBoardDetailServlet(photoBoardDao, photoFileDao));
    servletMap.put("/photoboard/add",
        new PhotoBoardAddServlet(photoBoardDao, stuffDao, photoFileDao, connectionFactory));
    servletMap.put("/photoboard/update",
        new PhotoBoardUpdateServlet(photoBoardDao, photoFileDao, connectionFactory));
    servletMap.put("/photoboard/delete",
        new PhotoBoardDeleteServlet(photoBoardDao, photoFileDao, connectionFactory));

    try (ServerSocket serverSocket = new ServerSocket(12345)) {
      while (true) {
        System.out.println("waiting for client to connect...");
        Socket connectedSocket = serverSocket.accept();
        System.out.println("Client connection complete");
        executorService.submit(() -> {
          processRequest(connectedSocket);
          ConnectionProxy con = (ConnectionProxy) connectionFactory.removeConnection();
          if (con != null) {
            try {
              con.realClose();
            } catch (Exception e) {
              System.out.println("[ServerApp.java] : ConnectionProxy object called realClose()");
            }
          }
          System.out.println("--------------------------------------------------");
        });

        if (shutdown)
          break;
      }
    } catch (Exception e) {
      System.out.println("[service()] : serverSocket error");
      e.printStackTrace();
    }

    executorService.shutdown();
    while (true) {
      if (executorService.isTerminated())
        break;
      try {
        Thread.sleep(500);
      } catch (Exception e) {

      }
    }
    notifyApplicationDestroyed();
    System.out.println("Bye");
  }

  private void processRequest(Socket connectedSocket) {
    try (Socket clientSocket = connectedSocket;
        BufferedReader in =
            new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter out =
            new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));) {

      System.out.println("Data I/O stream ready");

      String request = in.readLine();
      System.out.println("Receive complete");
      System.out.printf("Client Message : [\"%s\"]\n", request);

      if (request.equalsIgnoreCase("/shutdown")) {
        quit(out);
        return;
      }

      Servlet servlet = servletMap.get(request);
      if (servlet != null) {
        try {
          servlet.service(in, out);

        } catch (Exception e) {
          out.write("Error occurs during request processing" + System.lineSeparator());
          out.write(e.getMessage() + System.lineSeparator());
          System.out.println("[servlet.service()] : " + e.getMessage());
          e.printStackTrace();
        }
      } else {
        notfound(out);
      }
      out.write("!end!" + System.lineSeparator());
      out.flush();
      System.out.println("Send complete");

    } catch (Exception e) {
      System.out.printf("[processRequest()] : %s\n", e.getMessage());
      e.printStackTrace();
    }
  }

  private void quit(BufferedWriter out) throws IOException {
    shutdown = true;
    out.write("Server : OK" + System.lineSeparator());
    out.write("!end!");
    out.flush();
  }

  private void notfound(BufferedWriter out) throws IOException {
    out.write("Error occurs during request processing" + System.lineSeparator());
    out.flush();
  }

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    System.out.println("--------------------------------------------------");
    System.out.println("|                TAM server start                |");
    app.service();
  }
}
