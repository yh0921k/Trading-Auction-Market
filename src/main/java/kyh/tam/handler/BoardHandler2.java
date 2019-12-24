package kyh.tam.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import kyh.tam.domain.Board;

public class BoardHandler2 {    
  static final int BOARD_SIZE  = 100;
  static int boardCnt  = 0;      
  static Board[]  boards  = new Board[BOARD_SIZE];
  public static BufferedReader br;
  
  public static void addBoard() throws Exception {
    Board b = new Board();
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("번호 : ");
    b.postNum = Integer.parseInt(br.readLine());
    System.out.printf("내용 : ");
    b.detail = br.readLine();
    b.writeDate = new Date();
    b.viewCount = 0;
    boards[boardCnt++] = b;
  }
  public static void printBoardList() {
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(int i=0; i<boardCnt; i++) { 
      String wDate = new SimpleDateFormat("yyyy-MM-dd").format(boards[i].writeDate);
      System.out.printf("%s, %s, %s, %s\n", boards[i].postNum, boards[i].detail, wDate, boards[i].viewCount);
    }
  }
  public static void printDetailBoard() throws Exception {
    System.out.printf("번호 : ");
    int boardNum = Integer.parseInt(br.readLine());
    
    Board b = null;
    for(int i = 0; i < boardCnt; i++) {
      if(boards[i].postNum == boardNum) {
        b = boards[i];
        break;
      }
    }
    
    if(b == null) {
      System.out.println("Board Number does not exists.");
      return;
    }
    
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%36s%4s%26s%25s%12s%6s\n", "번호", " ", "내용", " ", "작성일", " ", "조회수");
    for(int i=0; i<boardCnt; i++) { 
      System.out.printf("%4d%4s%20s%10s%9s%8s\n", 
          boards[i].postNum, " ", boards[i].detail, " ", 
          new SimpleDateFormat("yyyy-MM-dd").format(boards[i].writeDate), boards[i].viewCount);
    }
  }
}
