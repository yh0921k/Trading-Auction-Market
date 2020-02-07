package kyh.tam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientApp {
  public static void main(String[] args) throws IOException {
    System.out.println("[TAM Client Start]");
    String servAddr = null;
    int portNum = 0;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    try {
      System.out.print("Server IP : ");
      servAddr = br.readLine();
      System.out.print("Port : ");
      portNum = Integer.parseInt(br.readLine());

    } catch (Exception e) {
      System.out.print("[User input] : ");
      System.out.println("Invalid server address(IP) or port number");
      e.printStackTrace();
      br.close();
    }

    try (Socket socket = new Socket(servAddr, portNum);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

      System.out.println("Connection complete");
      System.out.println("--------------------------------------------------");
      System.out.print("Message : ");
      String sendMessage = br.readLine();

      out.write(sendMessage + System.lineSeparator());
      out.flush();
      System.out.println("Send complete");

      String receivedMessage = in.readLine();
      System.out.println("Server : " + receivedMessage);
      System.out.println("Receive complete");

      System.out.println("Connection close");
      System.out.println("--------------------------------------------------");

    } catch (Exception e) {
      System.out.print("[Socket or I/O stream] : ");
      System.out.println("Invalid socket or I/O stream");
      e.printStackTrace();
    }
    br.close();
  }
}
