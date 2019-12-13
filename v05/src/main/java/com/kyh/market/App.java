package com.kyh.market;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {   
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
    // 입력부
    System.out.printf("번호 : ");
    int stuffNum = Integer.parseInt(br.readLine());
    System.out.printf("물품명 : ");
    String stuffName = br.readLine();
    System.out.printf("판매자 : ");
    String seller = br.readLine();
    System.out.printf("분류 : ");
    String category = br.readLine();
    System.out.printf("설명 : ");
    String detail = br.readLine();
    System.out.printf("가격 : ");
    int price = Integer.parseInt(br.readLine());
    
    // 출력부
    System.out.printf("----------------------------------------\n");
    System.out.printf("물품번호 : %d\n" , stuffNum);
    System.out.printf("물품명    : %s\n" , stuffName);
    System.out.printf("판매자    : %s\n" , seller);
    System.out.printf("분류       : %s\n" , category);
    System.out.printf("설명       : %s\n" , detail);
    System.out.printf("가격       : %d\n" , price);
  }
}
