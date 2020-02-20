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
import kyh.tam.dao.BoardDao;
import kyh.tam.dao.MemberDao;
import kyh.tam.dao.StuffDao;
import kyh.tam.dao.proxy.BoardDaoProxy;
import kyh.tam.dao.proxy.MemberDaoProxy;
import kyh.tam.dao.proxy.StuffDaoProxy;
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

  Deque<String> commandStack;
  Queue<String> commandQueue;

  String host;
  int port;

  public ClientApp() {
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();
  }

  public void service() throws Exception {

    try {
      if ((host = prompt.inputString("Server IP(enter) : ")).equals("")) {
        host = "127.0.0.1";
      }
      try {
        port = prompt.inputInt("Port : ");
      } catch (Exception e) {
        port = 12345;
      }

    } catch (Exception e) {
      System.out.print("[User input] : ");
      System.out.println("Invalid server address(IP) or port number");
      e.printStackTrace();
      br.close();
    }

    String command;
    while (true) {
      System.out.println("--------------------------------------------------");
      command = prompt.inputString("\n$ ");
      if (command.length() == 0)
        continue;

      if (command.equalsIgnoreCase("history")) {
        printCommandHistory(commandStack.iterator());
        continue;
      } else if (command.equalsIgnoreCase("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      } else if (command.equalsIgnoreCase("quit")) {
        break;
      }

      commandStack.push(command);
      commandQueue.offer(command);

      processCommand(command);
    }
    br.close();
  }

  private void processCommand(String command) throws Exception {

    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      System.out.println("Connection complete");

      BoardDao boardDao = new BoardDaoProxy(in, out);
      MemberDao memberDao = new MemberDaoProxy(in, out);
      StuffDao stuffDao = new StuffDaoProxy(in, out);

      HashMap<String, Command> commandMap = new HashMap<>();

      commandMap.put("/board/list", new BoardListCommand(boardDao));
      commandMap.put("/board/add", new BoardAddCommand(boardDao, prompt));
      commandMap.put("/board/detail", new BoardDetailCommand(boardDao, prompt));
      commandMap.put("/board/update", new BoardUpdateCommand(boardDao, prompt));
      commandMap.put("/board/delete", new BoardDeleteCommand(boardDao, prompt));
      commandMap.put("/member/list", new MemberListCommand(memberDao));
      commandMap.put("/member/add", new MemberAddCommand(memberDao, prompt));
      commandMap.put("/member/detail", new MemberDetailCommand(memberDao, prompt));
      commandMap.put("/member/update", new MemberUpdateCommand(memberDao, prompt));
      commandMap.put("/member/delete", new MemberDeleteCommand(memberDao, prompt));
      commandMap.put("/stuff/list", new StuffListCommand(stuffDao));
      commandMap.put("/stuff/add", new StuffAddCommand(stuffDao, prompt));
      commandMap.put("/stuff/detail", new StuffDetailCommand(stuffDao, prompt));
      commandMap.put("/stuff/update", new StuffUpdateCommand(stuffDao, prompt));
      commandMap.put("/stuff/delete", new StuffDeleteCommand(stuffDao, prompt));

      commandMap.put("shutdown", () -> {
        try {
          out.writeUTF(command);
          out.flush();
          System.out.println("Server : " + in.readUTF());
          System.out.println("Bye");
        } catch (Exception e) {
        }
      });

      Command commandHandler = commandMap.get(command);
      if (commandHandler == null) {
        System.out.println("실행할 수 없는 명령입니다.");
        return;
      }
      commandHandler.execute();
    } catch (Exception e) {
      System.out.print("[Socket or I/O stream] : ");
      System.out.println("Invalid socket or I/O stream");
      e.printStackTrace();
    }
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
