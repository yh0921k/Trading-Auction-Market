package kyh.tam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App2 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
    final int SIZE = 100;
    int tmpCnt = 0;
    
    class Member {
      int personNum;
      String personName;
      String email;
      String address;
      String password;
      String picture;
      String phoneNum;
      Date registeredDate;
    }
    Member[] members = new Member[SIZE]; 
    for(int i = 0; i < SIZE; i++) { 
      Member m = new Member();
      tmpCnt++;
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("번호 : ");
      m.personNum = Integer.parseInt(br.readLine());
      System.out.printf("이름 : ");
      m.personName = br.readLine();
      System.out.printf("Email : ");
      m.email = br.readLine();
      System.out.printf("주소 : ");
      m.address = br.readLine();
      System.out.printf("암호 : ");
      m.password = br.readLine();
      System.out.printf("사진 : ");
      m.picture = br.readLine();
      System.out.printf("전화 : ");
      m.phoneNum = br.readLine();
      m.registeredDate = new Date();
      members[i] = m;

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
    
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(int i=0; i<tmpCnt; i++)      
      System.out.printf("%s, %s, %s, %s, %s\n", 
          members[i].personNum, members[i].personName, members[i].address, members[i].phoneNum,
          new SimpleDateFormat("yyyy-MM-dd").format(members[i].registeredDate));
    System.out.printf("-----------------------------------------------------------------------------\n");
  }

}
