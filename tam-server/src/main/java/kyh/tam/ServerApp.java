package kyh.tam;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kyh.tam.context.ApplicationContextListener;
import kyh.tam.dao.json.BoardJsonFileDao;
import kyh.tam.dao.json.MemberJsonFileDao;
import kyh.tam.dao.json.StuffJsonFileDao;
import kyh.tam.servlet.BoardAddServlet;
import kyh.tam.servlet.BoardDeleteServlet;
import kyh.tam.servlet.BoardDetailServlet;
import kyh.tam.servlet.BoardListServlet;
import kyh.tam.servlet.BoardUpdateServlet;
import kyh.tam.servlet.MemberAddServlet;
import kyh.tam.servlet.MemberDeleteServlet;
import kyh.tam.servlet.MemberDetailServlet;
import kyh.tam.servlet.MemberListServlet;
import kyh.tam.servlet.MemberUpdateServlet;
import kyh.tam.servlet.Servlet;
import kyh.tam.servlet.StuffAddServlet;
import kyh.tam.servlet.StuffDeleteServlet;
import kyh.tam.servlet.StuffDetailServlet;
import kyh.tam.servlet.StuffListServlet;
import kyh.tam.servlet.StuffUpdateServlet;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new LinkedHashSet<>();
  Map<String, Object> context = new LinkedHashMap<>();
  Map<String, Servlet> servletMap = new HashMap<>();

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

    BoardJsonFileDao boardDao = (BoardJsonFileDao) context.get("boardDao");
    MemberJsonFileDao memberDao = (MemberJsonFileDao) context.get("memberDao");
    StuffJsonFileDao stuffDao = (StuffJsonFileDao) context.get("stuffDao");

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

    try (ServerSocket serverSocket = new ServerSocket(12345)) {
      while (true) {
        System.out.println("waiting for client to connect...");
        Socket connectedSocket = serverSocket.accept();
        if (processRequest(connectedSocket) == 1) {
          break;
        }
        System.out.println("--------------------------------------------------");
      }
    } catch (Exception e) {
      System.out.println("[service()] : serverSocket error");
      e.printStackTrace();
    }
    notifyApplicationDestroyed();
    System.out.println("Bye");
  }

  private int processRequest(Socket connectedSocket) {
    try (Socket clientSocket = connectedSocket;
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

      System.out.println("Data I/O stream ready");
      while (true) {
        String request = in.readUTF().toLowerCase();
        System.out.println("Receive complete");
        System.out.printf("Client Message : [\"%s\"]\n", request);

        switch (request) {
          case "quit":
            quit(out);
            return 0;
          case "shutdown":
            quit(out);
            return 1;
        }

        Servlet servlet = servletMap.get(request);
        if (servlet != null) {
          try {
            servlet.service(in, out);

          } catch (Exception e) {
            out.writeUTF("fail");
            out.writeUTF(e.getMessage());
            System.out.println("[servlet.service()] : " + e.getMessage());
            e.printStackTrace();
          }
        } else {
          notfound(out);
        }
        out.flush();
        System.out.println("Send complete");
        System.out.println("--------------------------------------------------");
      }
    } catch (Exception e) {
      System.out.printf("[processRequest()] : %s\n" + e.getMessage());
      e.printStackTrace();
      return -1;
    }
  }

  private void quit(ObjectOutputStream out) throws IOException {
    out.writeUTF("ok");
    out.flush();
  }

  private void notfound(ObjectOutputStream out) throws IOException {
    out.writeUTF("fail");
    out.writeUTF("요청한 명령을 처리할 수 없습니다.");
  }

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    System.out.println("--------------------------------------------------");
    System.out.println("|                TAM server start                |");
    app.service();
  }
}
