package kyh.tam.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import kyh.tam.domain.Member;

public class MemberHandler {  
  static final int MEMBER_SIZE = 100;
  static int memberCnt = 0;      
  static Member[] members = new Member[MEMBER_SIZE]; 
  public static BufferedReader br;
  
  public static void addMember() throws Exception {
    Member m = new Member();
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
    members[memberCnt++] = m;
  }
  public static void printMemberList() {
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(int i=0; i<memberCnt; i++)      
      System.out.printf("%s, %s, %s, %s, %s\n", 
          members[i].personNum, members[i].personName, members[i].address, members[i].phoneNum,
          new SimpleDateFormat("yyyy-MM-dd").format(members[i].registeredDate));
  }
}
