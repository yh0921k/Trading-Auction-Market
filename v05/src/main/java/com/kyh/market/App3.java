package com.kyh.market;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App3 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
    // 입력부
    System.out.printf("번호 : ");
    int postNum = Integer.parseInt(br.readLine());
    System.out.printf("내용 : ");
    String detail = br.readLine();
    Date writeDate = new Date();
    int viewCount = 0;
    
    // 출력부
    System.out.printf("----------------------------------------\n");
    System.out.printf("번호    : %d\n" , postNum);
    System.out.printf("이름    : %s\n" , detail);
    System.out.printf("가입일 : %s\n" , new SimpleDateFormat("yyyy-MM-dd").format(writeDate));
    System.out.printf("조회수 : %s\n" , viewCount);
  }
}
