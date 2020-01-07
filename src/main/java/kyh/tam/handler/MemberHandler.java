package kyh.tam.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import kyh.tam.domain.Member;
import kyh.util.ArrayList;

public class MemberHandler {  
  private BufferedReader br;
  private ArrayList<Member> memberList;
  
  public MemberHandler(BufferedReader br) {
    this.br = br;
    this.memberList = new ArrayList<>();
  }
  
  public MemberHandler(BufferedReader br, int capacity) {
    this.br = br;
    this.memberList = new ArrayList<>(capacity);
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
    memberList.add(m);
  }
  public void printMemberList() {
    Member[] members = memberList.toArray(Member[].class);
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(Member member : members) {
      System.out.printf("%s, %s, %s, %s, %s\n", 
          member.getPersonNum(), member.getPersonName(), 
          member.getAddress(), member.getPhoneNum(),
          new SimpleDateFormat("yyyy-MM-dd").format(member.getRegisteredDate()));
    }
  }
}
