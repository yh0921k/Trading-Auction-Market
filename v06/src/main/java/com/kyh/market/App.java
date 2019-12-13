package com.kyh.market;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {   
  public static void main(String[] args) throws Exception {
    /* 변수 정의 영역 */ 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    final int arraySize = 100;
    int index = 0;       

    String[] stuffName     = new String[arraySize];   
    String[] stuffContents = new String[arraySize];
    String[] seller        = new String[arraySize];
    String[] category      = new String[arraySize];
    
    int[] stuffNum  = new int[arraySize];  
    int[] price     = new int[arraySize];


    /* 입력부 */
    while(true) { 
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("번호 : ");
      stuffNum[index] = Integer.parseInt(br.readLine());
      System.out.printf("물품명 : ");
      stuffName[index] = br.readLine();
      System.out.printf("판매자 : ");
      seller[index] = br.readLine();
      System.out.printf("분류 : ");
      category[index] = br.readLine();
      System.out.printf("설명 : ");
      stuffContents[index] = br.readLine();
      System.out.printf("가격 : ");
      price[index] = Integer.parseInt(br.readLine());            

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

    /* 출력부 */    
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(int j=0; j<index; j++)      
      System.out.printf("%s, %s, %s, %s, %s\n", stuffNum[j], stuffName[j], category[j], seller[j], price[j]);
    System.out.printf("-----------------------------------------------------------------------------\n");
  }
}
