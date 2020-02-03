package kyh.tam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import kyh.tam.context.ApplicationContextListener;
import kyh.tam.domain.Board;
import kyh.tam.domain.Member;
import kyh.tam.domain.Stuff;
import kyh.tam.handler.BoardAddCommand;
import kyh.tam.handler.BoardDeleteCommand;
import kyh.tam.handler.BoardDetailCommand;
import kyh.tam.handler.BoardListCommand;
import kyh.tam.handler.BoardUpdateCommand;
import kyh.tam.handler.Command;
import kyh.tam.handler.HelloCommand;
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

public class App {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  Deque<String> commandStack = new ArrayDeque<>();
  Queue<String> commandQueue = new LinkedList<>();
  List<Board> boardList = new LinkedList<>();
  List<Stuff> stuffList = new LinkedList<>();
  List<Member> memberList = new LinkedList<>();

  Set<ApplicationContextListener> listeners = new LinkedHashSet<>();

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  private void notifyApplicationInitialized() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized();
    }
  }

  private void notifyApplicationDestroyed() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed();
    }
  }

  public void service() throws Exception {
    notifyApplicationInitialized();
    loadStuffData();
    loadMemberData();
    loadBoardData();

    Prompt prompt = new Prompt(br);
    HashMap<String, Command> commandMap = new HashMap<>();

    commandMap.put("/board/add", new BoardAddCommand(prompt, boardList));
    commandMap.put("/board/list", new BoardListCommand(boardList));
    commandMap.put("/board/detail", new BoardDetailCommand(prompt, boardList));
    commandMap.put("/board/delete", new BoardDeleteCommand(prompt, boardList));
    commandMap.put("/board/update", new BoardUpdateCommand(prompt, boardList));

    commandMap.put("/stuff/add", new StuffAddCommand(prompt, stuffList));
    commandMap.put("/stuff/list", new StuffListCommand(stuffList));
    commandMap.put("/stuff/detail", new StuffDetailCommand(prompt, stuffList));
    commandMap.put("/stuff/delete", new StuffDeleteCommand(prompt, stuffList));
    commandMap.put("/stuff/update", new StuffUpdateCommand(prompt, stuffList));

    commandMap.put("/member/add", new MemberAddCommand(prompt, memberList));
    commandMap.put("/member/list", new MemberListCommand(memberList));
    commandMap.put("/member/detail", new MemberDetailCommand(prompt, memberList));
    commandMap.put("/member/delete", new MemberDeleteCommand(prompt, memberList));
    commandMap.put("/member/update", new MemberUpdateCommand(prompt, memberList));

    // add a simple command hello to test Command Design Pattern
    commandMap.put("/hello", new HelloCommand(prompt));
    String command;

    while (true) {
      System.out.printf(
          "-----------------------------------------------------------------------------\n");
      System.out.printf("\n$ ");
      command = br.readLine();
      if (command.length() == 0)
        continue;

      if (command.equalsIgnoreCase("quit")) {
        System.out.println(
            "-----------------------------------------------------------------------------");
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
    saveStuffData();
    saveMemberData();
    saveBoardData();
    notifyApplicationDestroyed();
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

  @SuppressWarnings("unchecked")
  private void loadStuffData() throws Exception {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(new File("./data/stuff.ser2"))))) {
      stuffList = (List<Stuff>) in.readObject();
      System.out.printf("%d 개의 물품 데이터를 로딩했습니다.\n", stuffList.size());
    } catch (FileNotFoundException e) {
      System.out.println("파일을 읽는 도중 문제가 발생했습니다 : " + e.getMessage());
    }
  }

  private void saveStuffData() throws IOException {
    ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("./data/stuff.ser2")));
    out.writeObject(stuffList);
    System.out.printf("%d 개의 물품 데이터를 저장했습니다.\n", stuffList.size());
    out.close();
  }

  @SuppressWarnings("unchecked")
  private void loadMemberData() throws Exception {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(new File("./data/member.ser2"))))) {
      memberList = (List<Member>) in.readObject();
      System.out.printf("%d 개의 유저 데이터를 로딩했습니다.\n", memberList.size());
    } catch (FileNotFoundException e) {
      System.out.println("파일을 읽는 도중 문제가 발생했습니다 : " + e.getMessage());
    }
  }

  private void saveMemberData() throws IOException {
    ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream("./data/member.ser2")));
    out.writeObject(memberList);
    System.out.printf("%d 개의 유저 데이터를 저장했습니다.\n", memberList.size());
    out.close();
  }

  @SuppressWarnings("unchecked")
  private void loadBoardData() throws Exception {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(new File("./data/board.ser2"))))) {
      boardList = (List<Board>) in.readObject();
      System.out.printf("%d 개의 게시글 데이터를 로딩했습니다.\n", boardList.size());
    } catch (FileNotFoundException e) {
      System.out.println("파일을 읽는 도중 문제가 발생했습니다 : " + e.getMessage());
    }
  }

  private void saveBoardData() throws IOException {
    ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("./data/board.ser2")));
    out.writeObject(boardList);
    System.out.printf("%d 개의 게시글 데이터를 저장했습니다.\n", boardList.size());
    out.close();
  }

  public static void main(String[] args) throws Exception {
    App app = new App();
    app.service();
  }
}
