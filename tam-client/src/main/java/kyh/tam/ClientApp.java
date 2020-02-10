package kyh.tam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import kyh.tam.handler.Command;
import kyh.util.Prompt;

public class ClientApp {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  Prompt prompt = new Prompt(br);

  public void service() throws Exception {

    HashMap<String, Command> commandMap = new HashMap<>();
    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();

    String command;

    while (true) {
      System.out.println("--------------------------------------------------");
      System.out.printf("\n$ ");
      command = br.readLine();
      if (command.length() == 0)
        continue;

      if (command.equalsIgnoreCase("quit")) {
        System.out.println("--------------------------------------------------");
        break;
      } else if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      }

      commandStack.push(command);
      commandQueue.offer(command);

      Command commandHandler = commandMap.get(command);
      if (commandHandler != null)
        try {
          commandHandler.execute();
        } catch (Exception e) {
          System.out.printf("명령어 실행 중 오류 발생 : %s\n", e.getMessage());
        }
      else
        System.out.println("실행할 수 없는 명령입니다.");

    }
    br.close();
    System.out.println("Bye");
  }

  private void printCommandHistory(Iterator<String> it) throws Exception {
    int count = 0;
    while (it.hasNext()) {
      System.out.println(it.next());
      if (++count % 5 == 0) {
        System.out.printf(": ");
        if (br.readLine().equalsIgnoreCase("q"))
          break;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    System.out.println("[TAM Client Start]");
    ClientApp app = new ClientApp();
    app.service();
    // String servAddr = null;
    // int portNum = 0;
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //
    // try {
    // System.out.print("Server IP : ");
    // servAddr = br.readLine();
    // System.out.print("Port : ");
    // portNum = Integer.parseInt(br.readLine());
    //
    // } catch (Exception e) {
    // System.out.print("[User input] : ");
    // System.out.println("Invalid server address(IP) or port number");
    // e.printStackTrace();
    // br.close();
    // }
    //
    // try (Socket socket = new Socket(servAddr, portNum);
    // BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    // BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
    //
    // System.out.println("Connection complete");
    // System.out.println("--------------------------------------------------");
    // System.out.print("Message : ");
    // String sendMessage = br.readLine();
    //
    // out.write(sendMessage + System.lineSeparator());
    // out.flush();
    // System.out.println("Send complete");
    //
    // String receivedMessage = in.readLine();
    // System.out.println("Server : " + receivedMessage);
    // System.out.println("Receive complete");
    //
    // System.out.println("Connection close");
    // System.out.println("--------------------------------------------------");
    //
    // } catch (Exception e) {
    // System.out.print("[Socket or I/O stream] : ");
    // System.out.println("Invalid socket or I/O stream");
    // e.printStackTrace();
    // }
    // br.close();
  }
}