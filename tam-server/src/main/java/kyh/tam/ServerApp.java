package kyh.tam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
  public static void main(String[] args) {
    System.out.println("[TAM Server Start]");
    try (ServerSocket servSocket = new ServerSocket(12345)) {
      while (true) {
        Socket socket = servSocket.accept();
        System.out.println("Client connection success");
        processRequest(socket);
        System.out.println("--------------------------------------------------");
      }

    } catch (Exception e) {
      System.out.print("[Server socket] : " + e.getMessage());
      e.printStackTrace();
      return;
    }
  }

  public static void processRequest(Socket connectSocket) {
    try (Socket socket = connectSocket;
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

      System.out.println("Data I/O ready");
      String message = in.readLine();
      System.out.println("Client : " + message);
      out.write("Echo message : " + message + System.lineSeparator());
      out.flush();

    } catch (Exception e) {
      System.out.print("[processRequest()] : " + e.getMessage());
      e.printStackTrace();
    }
  }
}
