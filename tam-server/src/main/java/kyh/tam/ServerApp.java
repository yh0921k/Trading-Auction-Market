package kyh.tam;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kyh.tam.context.ApplicationContextListener;
import kyh.tam.domain.Board;
import kyh.tam.domain.Member;
import kyh.tam.domain.Stuff;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new LinkedHashSet<>();
  Map<String, Object> context = new LinkedHashMap<>();

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

  @SuppressWarnings({"unused", "unchecked"})
  public void service() throws Exception {
    notifyApplicationInitialized();

    List<Board> boardList = (List<Board>) context.get("boardList");
    List<Member> memberList = (List<Member>) context.get("memberList");
    List<Stuff> stuffList = (List<Stuff>) context.get("stuffList");

    notifyApplicationDestroyed();
    System.out.println("Bye");
  }

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
    // System.out.println("[TAM Server Start]");
    // try (ServerSocket servSocket = new ServerSocket(12345)) {
    // while (true) {
    // Socket socket = servSocket.accept();
    // System.out.println("Client connection success");
    // processRequest(socket);
    // System.out.println("--------------------------------------------------");
    // }
    //
    // } catch (Exception e) {
    // System.out.print("[Server socket] : " + e.getMessage());
    // e.printStackTrace();
    // return;
    // }
  }

  // public static void processRequest(Socket connectSocket) {
  // try (Socket socket = connectSocket;
  // BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  // BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
  //
  // System.out.println("Data I/O ready");
  // String message = in.readLine();
  // System.out.println("Client : " + message);
  // out.write("Echo message : " + message + System.lineSeparator());
  // out.flush();
  //
  // } catch (Exception e) {
  // System.out.print("[processRequest()] : " + e.getMessage());
  // e.printStackTrace();
  // }
  // }
}