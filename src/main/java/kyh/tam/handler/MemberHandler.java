package kyh.tam.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import kyh.tam.domain.Member;
import kyh.util.Iterator;
import kyh.util.List;
import kyh.util.Prompt;

public class MemberHandler {  
  private List<Member> memberList;
  private Prompt prompt;
  
  public MemberHandler(Prompt prompt, List<Member> list) {
    this.prompt = prompt;
    this.memberList = list;
  }
  
  public void addMember() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    Member member = new Member();
    
    member.setNumber(prompt.inputInt("번호 : "));
    member.setName(prompt.inputString("이름 : "));
    member.setEmail(prompt.inputString("메일 : "));
    member.setAddress(prompt.inputString("주소 : "));
    member.setPassword(prompt.inputString("암호 : "));
    member.setPhoto(prompt.inputString("사진 : "));
    member.setTel(prompt.inputString("연락처 : "));
    member.setRegisteredDate(new Date());
    
    this.memberList.add(member);
    System.out.println("저장하였습니다.");
  }
  public void listMember() {
    System.out.printf("-----------------------------------------------------------------------------\n");
    Iterator<Member> it = memberList.iterator();
    while(it.hasNext()) {
      Member member = it.next();
      System.out.printf("%s, %s, %s, %s, %s\n", 
          member.getNumber(), member.getName(), 
          member.getAddress(), member.getTel(),
          new SimpleDateFormat("yyyy-MM-dd").format(member.getRegisteredDate()));
    }
  }
  
  public void detailMember() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    int index = indexOfMember(prompt.inputInt("번호 : "));
    if(index == -1) {
      System.out.println("회원이 존재하지 않습니다");
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
    int index = indexOfMember(prompt.inputInt("번호 : "));
    if(index == -1) {
      System.out.println("회원이 존재하지 않습니다");
      return;
    }
    
    Member oldMember = memberList.get(index);
    Member newMember = new Member();
    
    newMember.setNumber(oldMember.getNumber());
    newMember.setName(prompt.inputString(
        String.format("이름(%s) : ", oldMember.getName()),
        oldMember.getName()));
    
    newMember.setEmail(prompt.inputString(
        String.format("메일(%s) : ", oldMember.getEmail()),
        oldMember.getEmail()));
    
    newMember.setAddress(prompt.inputString(
        String.format("주소(%s) : ", oldMember.getAddress()),
        oldMember.getAddress()));
    
    newMember.setPassword(prompt.inputString(
        String.format("암호(%s) : ", oldMember.getPassword()),
        oldMember.getPassword()));
    
    newMember.setPhoto(prompt.inputString(
        String.format("사진(%s) : ", oldMember.getPhoto()),
        oldMember.getPhoto()));
    
    newMember.setTel(prompt.inputString(
        String.format("연락처(%s) : ", oldMember.getTel()),
        oldMember.getTel()));
    
    newMember.setRegisteredDate(oldMember.getRegisteredDate());

    if (oldMember.equals(newMember)) {
      System.out.println("회원 변경 취소");
      return;
    }
    this.memberList.set(index, newMember);
    System.out.println("회원 변경 완료");
  }
  
  public void deleteMember() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    int index = indexOfMember(prompt.inputInt("번호 : "));
    if(index == -1) {
      System.out.println("회원이 존재하지 않습니다");
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

