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
}
