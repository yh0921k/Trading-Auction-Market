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
  public void listMember() {
    Member[] members = memberList.toArray(new Member[memberList.size()]);
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(Member member : members) {
      System.out.printf("%s, %s, %s, %s, %s\n", 
          member.getPersonNum(), member.getPersonName(), 
          member.getAddress(), member.getPhoneNum(),
          new SimpleDateFormat("yyyy-MM-dd").format(member.getRegisteredDate()));
    }
  }
  
  public void updateMember() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("Index : ");
    int index = Integer.parseInt(br.readLine());
    
    Member oldMember = memberList.get(index);
    if(oldMember == null) {
      System.out.println("Member Number does not exists.");
      return;
    }
    
    Member newMember = new Member();
    newMember.setPersonNum(oldMember.getPersonNum());
    System.out.printf("이름(%s) : ", oldMember.getPersonName());
    String tmp = br.readLine();
    if(tmp.length() != 0) {
      newMember.setPersonName(tmp);
    } else {
      newMember.setPersonName(oldMember.getPersonName());
    }
        
    System.out.printf("Email : ");
    newMember.setEmail(br.readLine());
    System.out.printf("주소 : ");
    newMember.setAddress(br.readLine());
    System.out.printf("비밀번호 : ");
    newMember.setPassword(br.readLine());
    System.out.printf("사진 : ");
    newMember.setPicture(br.readLine());
    System.out.printf("연락처 : ");
    newMember.setPhoneNum(br.readLine());
    newMember.setRegisteredDate(oldMember.getRegisteredDate());

    this.memberList.set(index, newMember);
    System.out.println("\nMember Update Complete.");
  }
  
  public void deleteMember() throws Exception {
    System.out.printf("Index : ");
    int index = Integer.parseInt(br.readLine());

    
    Member member = memberList.get(index);
    if(member == null) {
      System.out.println("Member does not exists.");
      return;
    }
    this.memberList.remove(index);
    System.out.println("Delete Complete.");
  }
}

