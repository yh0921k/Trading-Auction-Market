package com.kyh.market;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App2 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
    // 입력부
    System.out.printf("번호 : ");
    int personNum = Integer.parseInt(br.readLine());
    System.out.printf("이름 : ");
    String personName = br.readLine();
    System.out.printf("Email : ");
    String email = br.readLine();
    System.out.printf("주소 : ");
    String address = br.readLine();
    System.out.printf("암호 : ");
    String password = br.readLine();
    System.out.printf("사진 : ");
    String picture = br.readLine();
    System.out.printf("전화 : ");
    String phoneNum = br.readLine();
    Date registeredDate = new Date();
    
    // 출력부
    System.out.printf("----------------------------------------\n");
    System.out.printf("번호       : %d\n" , personNum);
    System.out.printf("이름       : %s\n" , personName);
    System.out.printf("Email : %s\n" , email);
    System.out.printf("주소       : %s\n" , address);
    System.out.printf("암호       : %s\n" , password);
    System.out.printf("사진       : %s\n" , picture);
    System.out.printf("전화       : %s\n" , phoneNum);
    System.out.printf("가입일    : %s\n" , new SimpleDateFormat("yyyy-MM-dd").format(registeredDate));
  }

}
