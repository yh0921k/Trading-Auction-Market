package kyh.tam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import kyh.tam.domain.Board;
import kyh.tam.domain.Member;
import kyh.tam.domain.Stuff;
import kyh.tam.handler.BoardHandler;
import kyh.tam.handler.MemberHandler;
import kyh.tam.handler.StuffHandler;
import kyh.util.ArrayList;
import kyh.util.LinkedList;
import kyh.util.Prompt;
import kyh.util.Queue;
import kyh.util.Stack;

public class App { 
  
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static Stack<String> commandStack = new Stack<>();
  static Queue<String> commandQueue = new Queue<>();
  
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
          printCommandHistory();
          break;
        case "history2":
          printCommandHistory2();
          break;
        default:
          if (!command.equalsIgnoreCase("quit"))
            System.out.printf("Incorrect Command\n");
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("Bye");
    br.close();
  }
  
  private static void printCommandHistory() throws Exception {
    Stack<String> historyStack = commandStack.clone();
    int count = 0;
    while(!historyStack.empty()) {
      System.out.println(historyStack.pop());
      if(++count % 5 == 0) {
        System.out.printf(": ");
        if(br.readLine().equalsIgnoreCase("q"))
          break;
      }      
    }
  }
  private static void printCommandHistory2() throws Exception {
    Queue<String> historyQueue = (Queue<String>)commandQueue.clone();
    int count = 0;
    while(historyQueue.size() > 0) {
      System.out.println(historyQueue.poll());     
      if(++count % 5 == 0) {
        System.out.printf(": ");
        
        if(br.readLine().equalsIgnoreCase("q"))
          break;
      }
    }
  }
}
