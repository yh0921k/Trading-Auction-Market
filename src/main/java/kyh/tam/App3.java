package kyh.tam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App3 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
    final int SIZE = 100;
    int tmpCnt = 0;
    
    class Board {
      int postNum;
      String detail;
      Date writeDate;
      int viewCount;
    }
    Board[] boards = new Board[SIZE];
 // 입력부
    for(int i = 0; i < SIZE; i++) {
      Board b = new Board();
      tmpCnt++;
      
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("번호 : ");
      b.postNum = Integer.parseInt(br.readLine());
      System.out.printf("내용 : ");
      b.detail = br.readLine();
      b.writeDate = new Date();
      b.viewCount = 0;
      boards[i] = b;

      System.out.printf("계속 입력하시겠습니까? (Y/n) : ");
      String tmp = br.readLine();
      if(tmp.equalsIgnoreCase("y"))
        continue;
      else if(tmp.equalsIgnoreCase("n")) 
        break;
      else {
        System.out.println("잘못 입력하셨습니다.\n프로그램을 종료합니다.\n");
        return;
      }
    }
    
    // 출력부    
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(int i=0; i<tmpCnt; i++) { 
      String wDate = new SimpleDateFormat("yyyy-MM-dd").format(boards[i].writeDate);
      System.out.printf("%s, %s, %s, %s\n", boards[i].postNum, boards[i].detail, wDate, boards[i].viewCount);
    }
    System.out.printf("-----------------------------------------------------------------------------\n");
  }
}
