package kyh.tam.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import kyh.tam.domain.Member;

public class MemberHandler {  
  int memberCnt = 0;      
  Member[] members;
  private BufferedReader br;
  
  static final int MEMBER_SIZE = 100;
  
  public MemberHandler(BufferedReader br) {
    this.br = br;
    this.members = new Member[MEMBER_SIZE];
  }
  public void addMember() throws Exception {
    Member m = new Member();
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("번호 : ");
    m.setPersonNum(Integer.parseInt(br.readLine()));
    System.out.printf("이름 : ");
    m.setPersonName(br.readLine());
    System.out.printf("Email : ");
    m.setEmail(br.readLine());
    System.out.printf("주소 : ");
    m.setAddress(br.readLine());
    System.out.printf("암호 : ");
    m.setPassword(br.readLine());
    System.out.printf("사진 : ");
    m.setPicture(br.readLine());
    System.out.printf("전화 : ");
    m.setPhoneNum(br.readLine());
    m.setRegisteredDate(new Date());
    this.members[this.memberCnt++] = m;
  }
  public void printMemberList() {
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(int i=0; i<this.memberCnt; i++)      
      System.out.printf("%s, %s, %s, %s, %s\n", 
          this.members[i].getPersonNum(), this.members[i].getPersonName(), 
          this.members[i].getAddress(), this.members[i].getPhoneNum(),
          new SimpleDateFormat("yyyy-MM-dd").format(this.members[i].getRegisteredDate()));
  }
}
