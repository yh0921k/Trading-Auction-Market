package kyh.tam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
  static List<Board> boardList = new LinkedList<>();
  static List<Stuff> stuffList = new LinkedList<>();
  static List<Member> memberList = new LinkedList<>();

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
    System.out.println("Bye");
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
    try (DataInputStream in = new DataInputStream(
        new BufferedInputStream(new FileInputStream(new File("./stuff.data"))))) {
      int size = in.readInt();
      for (int i = 0; i < size; i++) {
        Stuff stuff = new Stuff();
        stuff.setNumber(in.readInt());
        stuff.setName(in.readUTF());
        stuff.setState(in.readUTF());
        stuff.setSeller(in.readUTF());
        stuff.setCategory(in.readUTF());
        stuff.setPrice(in.readInt());
        stuffList.add(stuff);
      }
      System.out.printf("%d 개의 물품 데이터를 로딩했습니다.\n", stuffList.size());
    } catch (FileNotFoundException e) {
      System.out.println("파일을 읽는 도중 문제가 발생했습니다 : " + e.getMessage());
    }
  }

  private static void saveStuffData() throws IOException {
    DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream("./stuff.data")));
    out.writeInt(stuffList.size());
    for (Stuff stuff : stuffList) {
      out.writeInt(stuff.getNumber());
      out.writeUTF(stuff.getName());
      out.writeUTF(stuff.getState());
      out.writeUTF(stuff.getSeller());
      out.writeUTF(stuff.getCategory());
      out.writeInt(stuff.getPrice());
    }
    System.out.printf("%d 개의 물품 데이터를 저장했습니다.\n", stuffList.size());
    out.close();
  }

  private static void loadMemberData() throws IOException {
    try (DataInputStream in = new DataInputStream(
        new BufferedInputStream(new FileInputStream(new File("./member.data"))))) {
      int size = in.readInt();
      for (int i = 0; i < size; i++) {
        Member member = new Member();
        member.setNumber(in.readInt());
        member.setName(in.readUTF());
        member.setEmail(in.readUTF());
        member.setAddress(in.readUTF());
        member.setPassword(in.readUTF());
        member.setPhoto(in.readUTF());
        member.setTel(in.readUTF());
        member.setRegisteredDate(Date.valueOf(in.readUTF()));
        memberList.add(member);
      }
      System.out.printf("%d 개의 유저 데이터를 로딩했습니다.\n", memberList.size());
    } catch (FileNotFoundException e) {
      System.out.println("파일을 읽는 도중 문제가 발생했습니다 : " + e.getMessage());
    }
  }

  private static void saveMemberData() throws IOException {
    DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream("./member.data")));
    out.writeInt(memberList.size());
    for (Member member : memberList) {
      out.writeInt(member.getNumber());
      out.writeUTF(member.getName());
      out.writeUTF(member.getEmail());
      out.writeUTF(member.getAddress());
      out.writeUTF(member.getPassword());
      out.writeUTF(member.getPhoto());
      out.writeUTF(member.getTel());
      out.writeUTF(member.getRegisteredDate().toString());
    }
    System.out.printf("%d 개의 유저 데이터를 저장했습니다.\n", memberList.size());
    out.close();
  }

  private static void loadBoardData() throws IOException {
    try (DataInputStream in = new DataInputStream(
        new BufferedInputStream(new FileInputStream(new File("./board.data"))))) {
      int size = in.readInt();
      for (int i = 0; i < size; i++) {
        Board board = new Board();
        board.setNumber(in.readInt());
        board.setTitle(in.readUTF());
        board.setWriteDate(Date.valueOf(in.readUTF()));
        board.setViewCount(in.readInt());
        board.setWriter(in.readUTF());
        boardList.add(board);
      }
      System.out.printf("%d 개의 게시글 데이터를 로딩했습니다.\n", boardList.size());
    } catch (FileNotFoundException e) {
      System.out.println("파일을 읽는 도중 문제가 발생했습니다 : " + e.getMessage());
    }
  }

  private static void saveBoardData() throws IOException {
    DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream("./board.data")));
    out.writeInt(boardList.size());
    for (Board board : boardList) {
      out.writeInt(board.getNumber());
      out.writeUTF(board.getTitle());
      out.writeUTF(board.getWriteDate().toString());
      out.writeInt(board.getViewCount());
      out.writeUTF(board.getWriter() == null ? "" : board.getWriter());
    }
    System.out.printf("%d 개의 게시글 데이터를 저장했습니다.\n", boardList.size());
    out.close();
  }
}
