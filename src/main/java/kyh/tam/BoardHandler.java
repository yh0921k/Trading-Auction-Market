package kyh.tam;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardHandler {
  static class Board {
    int postNum;
    String detail;
    Date writeDate;
    int viewCount;
  }
  
  static final int BOARD_SIZE  = 100;
  static int boardCnt  = 0;      
  static Board[]  boards  = new Board[BOARD_SIZE];
  static BufferedReader br;
  
  static void addBoard() throws Exception {
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
  static void printBoardList() {
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(int i=0; i<boardCnt; i++) { 
      String wDate = new SimpleDateFormat("yyyy-MM-dd").format(boards[i].writeDate);
      System.out.printf("%s, %s, %s, %s\n", boards[i].postNum, boards[i].detail, wDate, boards[i].viewCount);
    }
  }
}
