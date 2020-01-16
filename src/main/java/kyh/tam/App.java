package kyh.tam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import kyh.tam.domain.Board;
import kyh.tam.domain.Member;
import kyh.tam.domain.Stuff;
import kyh.tam.handler.BoardHandler;
import kyh.tam.handler.MemberHandler;
import kyh.tam.handler.StuffHandler;
import kyh.util.Prompt;

public class App { 
  
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static Deque<String> commandStack = new ArrayDeque<>();
  static Queue<String> commandQueue = new LinkedList<>();
  
  public static void main(String[] args) throws Exception {
    Prompt prompt = new Prompt(br);
    StuffHandler stuffHandler = new StuffHandler(prompt, new ArrayList<Stuff>());
    MemberHandler memberHandler = new MemberHandler(prompt, new ArrayList<Member>());
    BoardHandler boardHandler = new BoardHandler(prompt, new LinkedList<Board>());
    String command;
    do {
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("\n$ ");
      command = br.readLine();
      if(command.length() == 0)
        continue;
      
      commandStack.push(command);
      commandQueue.offer(command);
      
      switch (command) {
        case "/stuff/add":
          stuffHandler.addStuff();
          break;
        case "/stuff/list":
          stuffHandler.listStuff();
          break;
        case "/stuff/detail":
          stuffHandler.detailStuff();
          break;  
        case "/stuff/update":
          stuffHandler.updateStuff();
          break;
        case "/stuff/delete":
          stuffHandler.deleteStuff();
          break;
        case "/member/add":
          memberHandler.addMember();
          break;
        case "/member/list":
          memberHandler.listMember();
          break;
        case "/member/detail":
          memberHandler.detailMember();
          break;  
        case "/member/update":
          memberHandler.updateMember();
          break;
        case "/member/delete":
          memberHandler.deleteMember();
          break;
        case "/board/add":
          boardHandler.addBoard();
          break;
        case "/board/list":
          boardHandler.listBoard();
          break;
        case "/board/detail":
          boardHandler.detailBoard();
          break;  
        case "/board/update":
          boardHandler.updateBoard();
          break;
        case "/board/delete":
          boardHandler.deleteBoard();
          break;  
        case "history":
          printCommandHistory(commandStack.iterator());
          break;
        case "history2":
          printCommandHistory(commandQueue.iterator());
          break;
        default:
          if (!command.equalsIgnoreCase("quit"))
            System.out.printf("Incorrect Command\n");
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("Bye");
    br.close();
  }
  
  private static void printCommandHistory(Iterator<String> it) throws Exception {    
    int count = 0;
    while(it.hasNext()) {
      System.out.println(it.next());
      if(++count % 5 == 0) {
        System.out.printf(": ");
        if(br.readLine().equalsIgnoreCase("q"))
          break;
      }      
    }
  }
}
