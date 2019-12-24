package kyh.tam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import kyh.tam.handler.BoardHandler;
import kyh.tam.handler.BoardHandler2;
import kyh.tam.handler.MemberHandler;
import kyh.tam.handler.StuffHandler;

public class App { 
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StuffHandler.br = br;
    MemberHandler.br = br;
    BoardHandler.br = br;
    BoardHandler2.br = br;
    String command;
    do {
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("\n$ ");
      command = br.readLine();

      switch (command) {
        case "/stuff/add":
          StuffHandler.addStuff();
          break;
        case "/stuff/list":
          StuffHandler.printStuffList();
          break;
        case "/member/add":
          MemberHandler.addMember();
          break;
        case "/member/list":
          MemberHandler.printMemberList();
          break;
        case "/board/add":
          BoardHandler.addBoard();
          break;
        case "/board/list":
          BoardHandler.printBoardList();
          break;
        case "/board2/add":
          BoardHandler2.addBoard();
          break;
        case "/board2/list":
          BoardHandler2.printBoardList();
          break;
        default:
          if (!command.equalsIgnoreCase("quit"))
            System.out.printf("Incorrect Command\n");
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("Bye");
  }
  
  
  
}
