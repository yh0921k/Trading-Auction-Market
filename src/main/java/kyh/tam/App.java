package kyh.tam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
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

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static Deque<String> commandStack = new ArrayDeque<>();
  static Queue<String> commandQueue = new LinkedList<>();
  static LinkedList<Board> boardList = new LinkedList<>();
  static LinkedList<Stuff> stuffList = new LinkedList<>();
  static LinkedList<Member> memberList = new LinkedList<>();

  public static void main(String[] args) throws Exception {
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
        System.out.println("Bye");
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
  }

  private static void printCommandHistory(Iterator<String> it) throws Exception {
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

  private static void loadStuffData() throws IOException {
    BufferedReader in = null;
    try {
      in = new BufferedReader(new FileReader(new File("./stuff.csv")));

      String line = "";
      int count = 0;
      while ((line = in.readLine()) != null) {
        String[] data = line.split(",");
        Stuff stuff = new Stuff();
        stuff.setNumber(Integer.parseInt(data[0]));
        stuff.setName(data[1]);
        stuff.setState(data[2]);
        stuff.setSeller(data[3]);
        stuff.setCategory(data[4]);
        stuff.setPrice(Integer.parseInt(data[5]));

        stuffList.add(stuff);
        count++;
      }
      System.out.printf("%d 개의 물품 데이터를 로딩했습니다.\n", count);
    } catch (FileNotFoundException e) {
      System.out.println("파일을 읽는 도중 문제가 발생했습니다 : " + e.getMessage());
    } finally {
      try {
        in.close();
      } catch (Exception e) {
        // pass
      }
    }
  }

  private static void saveStuffData() throws IOException {
    BufferedWriter bw = null;
    bw = new BufferedWriter(new FileWriter(new File("./stuff.csv")));

    Iterator<Stuff> it = stuffList.iterator();
    int count = 0;
    while (it.hasNext()) {
      Stuff stuff = it.next();
      String line = String.format("%d,%s,%s,%s,%s,%d\n", stuff.getNumber(), stuff.getName(),
          stuff.getState(), stuff.getSeller(), stuff.getCategory(), stuff.getPrice());
      bw.write(line);
      count++;
    }
    System.out.printf("%d 개의 물품 데이터를 저장했습니다.\n", count);
    bw.close();
  }

  private static void loadMemberData() throws IOException {
    BufferedReader in = null;
    try {
      in = new BufferedReader(new FileReader(new File("./member.csv")));

      String line = "";
      int count = 0;
      while ((line = in.readLine()) != null) {
        String[] data = line.split(",");
        Member member = new Member();
        member.setNumber(Integer.parseInt(data[0]));
        member.setName(data[1]);
        member.setEmail(data[2]);
        member.setAddress(data[3]);
        member.setPassword(data[4]);
        member.setPhoto(data[5]);
        member.setTel(data[6]);
        member.setRegisteredDate(Date.valueOf(data[7]));

        memberList.add(member);
        count++;
      }
      System.out.printf("%d 개의 유저 데이터를 로딩했습니다.\n", count);
    } catch (FileNotFoundException e) {
      System.out.println("파일을 읽는 도중 문제가 발생했습니다 : " + e.getMessage());
    } finally {
      try {
        in.close();
      } catch (Exception e) {
        // pass
      }
    }
  }

  private static void saveMemberData() throws IOException {
    BufferedWriter bw = null;
    bw = new BufferedWriter(new FileWriter(new File("./member.csv")));

    Iterator<Member> it = memberList.iterator();
    int count = 0;
    while (it.hasNext()) {
      Member member = it.next();
      String line = String.format("%d,%s,%s,%s,%s,%s,%s,%s\n", member.getNumber(), member.getName(),
          member.getEmail(), member.getAddress(), member.getPassword(), member.getPhoto(),
          member.getTel(), member.getRegisteredDate());
      bw.write(line);
      count++;
    }
    System.out.printf("%d 개의 유저 데이터를 저장했습니다.\n", count);
    bw.close();
  }

  private static void loadBoardData() throws IOException {
    BufferedReader in = null;
    try {
      in = new BufferedReader(new FileReader(new File("./board.csv")));

      String line = "";
      int count = 0;
      while ((line = in.readLine()) != null) {
        String[] data = line.split(",");
        Board board = new Board();
        board.setNumber(Integer.parseInt(data[0]));
        board.setTitle(data[1]);
        board.setWriteDate(Date.valueOf(data[2]));
        board.setViewCount(Integer.parseInt(data[3]));
        board.setWriter(data[4]);
        boardList.add(board);
        count++;
      }
      System.out.printf("%d 개의 게시글 데이터를 로딩했습니다.\n", count);
    } catch (FileNotFoundException e) {
      System.out.println("파일을 읽는 도중 문제가 발생했습니다 : " + e.getMessage());
    } finally {
      try {
        in.close();
      } catch (Exception e) {
        // pass
      }
    }
  }

  private static void saveBoardData() throws IOException {
    BufferedWriter bw = null;
    bw = new BufferedWriter(new FileWriter(new File("./board.csv")));

    Iterator<Board> it = boardList.iterator();
    int count = 0;
    while (it.hasNext()) {
      Board board = it.next();
      String line = String.format("%d,%s,%s,%d,%s\n", board.getNumber(), board.getTitle(),
          board.getWriteDate(), board.getViewCount(), board.getWriter());
      bw.write(line);
      count++;
    }
    System.out.printf("%d 개의 게시글 데이터를 저장했습니다.\n", count);
    bw.close();
  }
}
