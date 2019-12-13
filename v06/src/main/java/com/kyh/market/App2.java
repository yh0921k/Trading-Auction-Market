package com.kyh.market;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App2 {
  public static void main(String[] args) throws Exception {
    // 변수 선언부
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
    final int arraySize = 100;
    int index = 0; 
    
    int[] personNum       = new int[arraySize];
    String[] personName   = new String[arraySize];
    String[] email        = new String[arraySize];
    String[] address      = new String[arraySize];
    String[] password     = new String[arraySize];
    String[] picture      = new String[arraySize];
    String[] phoneNum     = new String[arraySize];
    Date[] registeredDate = new Date[arraySize];
    
    // 입력부
    while(true) { 
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("번호 : ");
      personNum[index] = Integer.parseInt(br.readLine());
      System.out.printf("이름 : ");
      personName[index] = br.readLine();
      System.out.printf("Email : ");
      email[index] = br.readLine();
      System.out.printf("주소 : ");
      address[index] = br.readLine();
      System.out.printf("암호 : ");
      password[index] = br.readLine();
      System.out.printf("사진 : ");
      picture[index] = br.readLine();
      System.out.printf("전화 : ");
      phoneNum[index] = br.readLine();
      registeredDate[index] = new Date();       

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
    for(int j=0; j<index; j++)      
      System.out.printf("%s, %s, %s, %s, %s\n", personNum[j], personName[j], address[j], phoneNum[j],
                                                new SimpleDateFormat("yyyy-MM-dd").format(registeredDate[j]));
    System.out.printf("-----------------------------------------------------------------------------\n");
  }

}
