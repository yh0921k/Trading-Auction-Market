package kyh.tam.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import kyh.tam.domain.Member;
import kyh.util.ArrayList;

public class MemberHandler {  
  private ArrayList<Member> memberList;
  private BufferedReader br;
  
  public MemberHandler(BufferedReader br) {
    this.br = br;
    this.memberList = new ArrayList<>();
  }
  
  public MemberHandler(BufferedReader br, int capacity) {
    this.br = br;
    this.memberList = new ArrayList<>(capacity);
  }
  
  public void addMember() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    Member member = new Member();
    System.out.printf("번호 : ");
    member.setNumber(Integer.parseInt(br.readLine()));
    System.out.printf("이름 : ");
    member.setName(br.readLine());
    System.out.printf("Email : ");
    member.setEmail(br.readLine());
    System.out.printf("주소 : ");
    member.setAddress(br.readLine());
    System.out.printf("암호 : ");
    member.setPassword(br.readLine());
    System.out.printf("사진 : ");
    member.setPhoto(br.readLine());
    System.out.printf("전화 : ");
    member.setTel(br.readLine());
    member.setRegisteredDate(new Date());
    memberList.add(member);
    System.out.println("저장하였습니다.");
  }
  public void listMember() {
    Member[] members = memberList.toArray(new Member[memberList.size()]);
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(Member member : members) {
      System.out.printf("%s, %s, %s, %s, %s\n", 
          member.getNumber(), member.getName(), 
          member.getAddress(), member.getTel(),
          new SimpleDateFormat("yyyy-MM-dd").format(member.getRegisteredDate()));
    }
  }
  
  public void detailMember() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("회원 번호 : ");
    int number = Integer.parseInt(br.readLine());

    int index = indexOfMember(number);
    if(index == -1) {
      System.out.println("번호가 존재하지 않습니다");
      return;
    }
    
    Member member = this.memberList.get(index);    
    System.out.printf("번호 : %d\n", member.getNumber());
    System.out.printf("이름 : %s\n", member.getName());
    System.out.printf("메일 : %s\n", member.getEmail());
    System.out.printf("주소 : %s\n", member.getAddress());
    System.out.printf("암호 : %s\n", member.getPassword());
    System.out.printf("사진 : %s\n", member.getPhoto());
    System.out.printf("연락처 : %s\n", member.getTel());
    System.out.printf("가입일 : %s\n", new SimpleDateFormat("yyyy-MM-dd").format(member.getRegisteredDate()));
  }
  
  public void updateMember() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("회원 번호 : ");
    int number = Integer.parseInt(br.readLine());

    int index = indexOfMember(number);
    if(index == -1) {
      System.out.println("번호가 존재하지 않습니다");
      return;
    }
    
    Member oldMember = memberList.get(index);
    Member newMember = new Member();
    String tmp;;
    boolean changed = false;
    
    newMember.setNumber(oldMember.getNumber());
    System.out.printf("이름(%s) : ", oldMember.getName());
    tmp = br.readLine();
    if(tmp.length() != 0)
      newMember.setName(tmp);
    else {
      newMember.setName(oldMember.getName());
      changed = true;
    }
        
    System.out.printf("Email(%s) : ", oldMember.getEmail());
    tmp = br.readLine();
    if(tmp.length() == 0)
      newMember.setEmail(oldMember.getEmail());
    else {
      newMember.setEmail(tmp);
      changed = true;
    }    
    
    System.out.printf("주소(%s) : ", oldMember.getAddress());
    tmp = br.readLine();
    if(tmp.length() == 0)
      newMember.setAddress(oldMember.getAddress());
    else {
      newMember.setAddress(tmp);
      changed = true;
    }    
    
    System.out.printf("비밀번호(%s) : ", oldMember.getPassword());
    tmp = br.readLine();
    if(tmp.length() == 0)
      newMember.setPassword(oldMember.getPassword());
    else {
      newMember.setPassword(tmp);
      changed = true;
    }    
    
    System.out.printf("사진(%s) : ", oldMember.getPhoto());
    tmp = br.readLine();
    if(tmp.length() == 0)
      newMember.setPhoto(oldMember.getPhoto());
    else {
      newMember.setPhoto(tmp);
      changed = true;
    }
    
    
    System.out.printf("연락처(%s) : ", oldMember.getTel());
    tmp = br.readLine();
    if(tmp.length() == 0)
      newMember.setTel(oldMember.getTel());
    else {
      newMember.setTel(tmp);
      changed = true;
    }
    
    newMember.setRegisteredDate(oldMember.getRegisteredDate());

    if(changed) {
      this.memberList.set(index, newMember);
      System.out.println("회원 변경 완료");
    } else {
      System.out.println("회원 변경 취소");
    }
  }
  
  public void deleteMember() throws Exception {
    System.out.printf("회원 번호 : ");
    int number = Integer.parseInt(br.readLine());

    int index = indexOfMember(number);
    if(index == -1) {
      System.out.println("번호가 존재하지 않습니다");
      return;
    }    
    this.memberList.remove(index);
    System.out.println("회원 삭제 완료");
  }
  
  private int indexOfMember(int number) {
    for(int i = 0; i < this.memberList.size(); i++)
      if(this.memberList.get(i).getNumber() == number)
        return i;
    return -1;
  }
}

