package kyh.tam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import kyh.tam.handler.BoardHandler;
import kyh.tam.handler.MemberHandler;
import kyh.tam.handler.StuffHandler;

public class App { 
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StuffHandler stuffHandler = new StuffHandler(br);
    MemberHandler memberHandler = new MemberHandler(br);
    BoardHandler boardHandler = new BoardHandler(br);
    
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
        default:
          if (!command.equalsIgnoreCase("quit"))
            System.out.printf("Incorrect Command\n");
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("Bye");
    br.close();
  }
}
