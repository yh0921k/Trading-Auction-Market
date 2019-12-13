package com.kyh.market;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App3 {
  public static void main(String[] args) throws Exception {
    // 변수 선언부
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
    final int arraySize = 100;
    int index = 0; 
    
    int[] postNum    = new int[arraySize];
    String[] detail  = new String[arraySize];
    Date[] writeDate = new Date[arraySize];
    int[] viewCount  = new int[arraySize];
    
 // 입력부
    while(true) { 
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("번호 : ");
      postNum[index] = Integer.parseInt(br.readLine());
      System.out.printf("내용 : ");
      detail[index] = br.readLine();
      writeDate[index] = new Date();
      viewCount[index] = 0;

      System.out.printf("계속 입력하시겠습니까? (Y/n) : ");
      index++; 
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
    for(int j=0; j<index; j++) { 
      String tmp = new SimpleDateFormat("yyyy-MM-dd").format(writeDate[j]);
      System.out.printf("%s, %s, %s, %s\n", postNum[j], detail[j], tmp, viewCount[j]);
    }
    System.out.printf("-----------------------------------------------------------------------------\n");
  }
}
