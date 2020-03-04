package kyh.tam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kyh.util.Prompt;

public class ClientApp {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  Prompt prompt = new Prompt(br);

  Deque<String> commandStack;
  Queue<String> commandQueue;

  public ClientApp() throws ClassNotFoundException, SQLException {
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();
  }

  public void service() throws Exception {

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
    System.out.println("--------------------------------------------------");
    br.close();
  }

  private void processCommand(String command) throws Exception {
    String protocol = null;
    String host = null;
    int port = 0;
    String servletPath = null;

    try {
      Pattern[] pattern = new Pattern[2];
      pattern[0] = Pattern.compile("^([a-zA-Z]*)://([\\w\\d.]*):([0-9]{0,5})(.*)$");
      pattern[1] = Pattern.compile("^([a-zA-Z]*)://([\\w\\d.]*)(.*)$");

      Matcher matcher = null;
      for (Pattern p : pattern) {
        matcher = p.matcher(command);
        if (matcher.find())
          break;
      }

      protocol = matcher.group(1);
      host = matcher.group(2);

      if (matcher.groupCount() == 3) {
        port = 12345;
        servletPath = matcher.group(3);
      } else {
        port = Integer.parseInt(matcher.group(3));
        servletPath = matcher.group(4);
      }

      if (!protocol.equals("tam"))
        throw new Exception("Invalid protocol");

    } catch (Exception e) {
      System.out.printf("[processCommand()] : Invalid url format\n");
      e.printStackTrace();
    }

    try (Socket socket = new Socket(host, port);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

      out.write(servletPath + System.lineSeparator());
      out.flush();

      while (true) {
        String response = in.readLine();
        if (response.equalsIgnoreCase("!end!"))
          break;
        System.out.println(response);

      }

    } catch (Exception e) {
      System.out.printf("[processCommand()] : Socket() or Data communication error\n");
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
