package kyh.tam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import kyh.tam.handler.BoardHandler;
import kyh.tam.handler.MemberHandler;
import kyh.tam.handler.StuffHandler;

public class App { 
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StuffHandler.br = br;
    MemberHandler.br = br;
    BoardHandler.br = br;
    
    StuffHandler stuffHandler = new StuffHandler();
    MemberHandler memberHandler = new MemberHandler();
    BoardHandler boardHandler1 = new BoardHandler();
    BoardHandler boardHandler2 = new BoardHandler();
    BoardHandler boardHandler3 = new BoardHandler();
    BoardHandler boardHandler4 = new BoardHandler();
    BoardHandler boardHandler5 = new BoardHandler();
    BoardHandler boardHandler6 = new BoardHandler();
    String command;
    do {
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("\n$ ");
      command = br.readLine();

      switch (command) {
        case "/stuff/add":
          stuffHandler.addStuff();
          break;
        case "/stuff/list":
          stuffHandler.printStuffList();
          break;
        case "/member/add":
          memberHandler.addMember();
          break;
        case "/member/list":
          memberHandler.printMemberList();
          break;
        case "/board/add":
          boardHandler1.addBoard();
          break;
        case "/board/list":
          boardHandler1.printBoardList();
          break;
        case "/board/detail":
          boardHandler1.printDetailBoard();
          break;
        case "/board2/add":
          boardHandler2.addBoard();
          break;
        case "/board2/list":
          boardHandler2.printBoardList();
          break;
        case "/board2/detail":
          boardHandler2.printDetailBoard();
          break;
        case "/board3/add":
          boardHandler3.addBoard();
          break;
        case "/board3/list":
          boardHandler3.printBoardList();
          break;
        case "/board3/detail":
          boardHandler3.printDetailBoard();
          break;
        case "/board4/add":
          boardHandler4.addBoard();
          break;
        case "/board4/list":
          boardHandler4.printBoardList();
          break;
        case "/board4/detail":
          boardHandler4.printDetailBoard();
          break;
        case "/board5/add":
          boardHandler5.addBoard();
          break;
        case "/board5/list":
          boardHandler5.printBoardList();
          break;
        case "/board5/detail":
          boardHandler5.printDetailBoard();
          break;
        case "/board6/add":
          boardHandler6.addBoard();
          break;
        case "/board6/list":
          boardHandler6.printBoardList();
          break;
        case "/board6/detail":
          boardHandler6.printDetailBoard();
          break;
        default:
          if (!command.equalsIgnoreCase("quit"))
            System.out.printf("Incorrect Command\n");
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("Bye");
  }
  
  
  
}
