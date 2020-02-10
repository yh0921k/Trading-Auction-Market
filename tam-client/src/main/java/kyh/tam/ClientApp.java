package kyh.tam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import kyh.tam.handler.BoardAddCommand;
import kyh.tam.handler.BoardDeleteCommand;
import kyh.tam.handler.BoardDetailCommand;
import kyh.tam.handler.BoardListCommand;
import kyh.tam.handler.BoardUpdateCommand;
import kyh.tam.handler.Command;
import kyh.tam.handler.MemberAddCommand;
import kyh.tam.handler.MemberDeleteCommand;
import kyh.tam.handler.MemberDetailCommand;
import kyh.tam.handler.MemberListCommand;
import kyh.tam.handler.MemberUpdateCommand;
import kyh.tam.handler.StuffAddCommand;
import kyh.tam.handler.StuffDeleteCommand;
import kyh.tam.handler.StuffDetailCommand;
import kyh.tam.handler.StuffListCommand;
import kyh.tam.handler.StuffUpdateCommand;
import kyh.util.Prompt;

public class ClientApp {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  Prompt prompt = new Prompt(br);

  public void service() throws Exception {
    String serverIPAddress = null;
    int serverPortNumber = 0;

    try {
      if ((serverIPAddress = prompt.inputString("Server IP(enter) : ")).equals("")) {
        serverIPAddress = "127.0.0.1";
      }
      try {
        serverPortNumber = prompt.inputInt("Port : ");
      } catch (Exception e) {
        serverPortNumber = 12345;
      }

    } catch (Exception e) {
      System.out.print("[User input] : ");
      System.out.println("Invalid server address(IP) or port number");
      e.printStackTrace();
      br.close();
    }

    try (Socket socket = new Socket(serverIPAddress, serverPortNumber);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      System.out.println("Connection complete");
      processCommand(out, in);

    } catch (Exception e) {
      System.out.print("[Socket or I/O stream] : ");
      System.out.println("Invalid socket or I/O stream");
      e.printStackTrace();
    }
    br.close();
  }

  private void processCommand(ObjectOutputStream out, ObjectInputStream in) throws Exception {

    HashMap<String, Command> commandMap = new HashMap<>();
    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();

    commandMap.put("/board/list", new BoardListCommand(out, in));
    commandMap.put("/board/add", new BoardAddCommand(out, in, prompt));
    commandMap.put("/board/detail", new BoardDetailCommand(out, in, prompt));
    commandMap.put("/board/update", new BoardUpdateCommand(out, in, prompt));
    commandMap.put("/board/delete", new BoardDeleteCommand(out, in, prompt));
    commandMap.put("/member/list", new MemberListCommand(out, in));
    commandMap.put("/member/add", new MemberAddCommand(out, in, prompt));
    commandMap.put("/member/detail", new MemberDetailCommand(out, in, prompt));
    commandMap.put("/member/update", new MemberUpdateCommand(out, in, prompt));
    commandMap.put("/member/delete", new MemberDeleteCommand(out, in, prompt));
    commandMap.put("/stuff/list", new StuffListCommand(out, in));
    commandMap.put("/stuff/add", new StuffAddCommand(out, in, prompt));
    commandMap.put("/stuff/detail", new StuffDetailCommand(out, in, prompt));
    commandMap.put("/stuff/update", new StuffUpdateCommand(out, in, prompt));
    commandMap.put("/stuff/delete", new StuffDeleteCommand(out, in, prompt));
    String command;

    while (true) {
      System.out.println("--------------------------------------------------");
      command = prompt.inputString("\n$ ");
      if (command.length() == 0)
        continue;

      if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("shutdown")) {
        out.writeUTF(command);
        out.flush();
        System.out.println("Server : " + in.readUTF());
        System.out.println("Bye");
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
          System.out.printf("[processCommand] : %s", "while commandHandler.execute()");
          System.out.println("--------------------------------------------------");
        }
      else {
        System.out.println("Invalid command");
      }

    }
    br.close();
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
    System.out.println("--------------------------------------------------");
    System.out.println("|                TAM client start                |");
    System.out.println("--------------------------------------------------");
    ClientApp app = new ClientApp();
    app.service();
  }
}
