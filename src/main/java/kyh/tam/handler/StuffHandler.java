package kyh.tam.handler;

import java.io.BufferedReader;
import kyh.tam.domain.Stuff;
import kyh.util.ArrayList;

public class StuffHandler {
  private ArrayList<Stuff> stuffList;
  private BufferedReader br;
  
  public StuffHandler(BufferedReader br) {
    this.br = br;
    this.stuffList = new ArrayList<>();
  }
  
  public StuffHandler(BufferedReader br, int capacity) {
    this.br = br;
    this.stuffList = new ArrayList<>(capacity);
  }
  
  public void addStuff() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    Stuff stuff = new Stuff();
    System.out.printf("번호 : ");
    stuff.setNumber(Integer.parseInt(br.readLine()));
    System.out.printf("물품명 : ");
    stuff.setName(br.readLine());
    System.out.printf("분류 : ");
    stuff.setCategory(br.readLine());
    System.out.printf("상태 : ");
    stuff.setState(br.readLine());
    System.out.printf("가격 : ");
    stuff.setPrice(Integer.parseInt(br.readLine()));            
    System.out.printf("판매자 : ");
    stuff.setSeller(br.readLine());
    stuffList.add(stuff);
    System.out.println("저장하였습니다.");
  }
  public void listStuff() {
    Stuff[] stuffs = stuffList.toArray(new Stuff[stuffList.size()]);
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(Stuff stuff : stuffs) {
      System.out.printf("%s, %s, %s, %s, %s, %s\n", 
          stuff.getNumber(), stuff.getName(), stuff.getCategory(), 
          stuff.getState(), stuff.getPrice(), stuff.getSeller());
    }
  }
  
  public void detailStuff() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("물품 인덱스 : ");
    int index = Integer.parseInt(br.readLine());
    
    Stuff stuff = this.stuffList.get(index);
    if(stuff == null) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }
    
    System.out.printf("번호 : %s\n", stuff.getNumber());
    System.out.printf("물품명 : %s\n", stuff.getName());
    System.out.printf("분류 : %s\n", stuff.getCategory());
    System.out.printf("상태 : %s\n", stuff.getState());
    System.out.printf("가격 : %d\n", stuff.getPrice());
    System.out.printf("판매자 : %s\n", stuff.getSeller());
  }
  
  public void updateStuff() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("물품 인덱스 : ");
    int index = Integer.parseInt(br.readLine());
    
    Stuff oldStuff = stuffList.get(index);
    if(oldStuff == null) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }
    
    Stuff newStuff = new Stuff();
    String tmp;
    boolean changed = false;
    
    newStuff.setNumber(oldStuff.getNumber());
    System.out.printf("물품명(%s) : ", oldStuff.getName());
    tmp = br.readLine();
    if(tmp.length() != 0)
      newStuff.setName(tmp);
    else {
      newStuff.setName(oldStuff.getName());
      changed = true;
    }
        
    System.out.printf("분류(%s) : ", oldStuff.getCategory());
    tmp = br.readLine();
    if(tmp.length() == 0)
      newStuff.setCategory(oldStuff.getCategory());
    else {
      newStuff.setCategory(tmp);
      changed = true;
    }

    System.out.printf("상태(%s) : ", oldStuff.getState());
    tmp = br.readLine();
    if(tmp.length() == 0)
      newStuff.setState(oldStuff.getState());
    else {
      newStuff.setState(tmp);
      changed = true;
    }

    System.out.printf("가격(%d) : ", oldStuff.getPrice());
    tmp = br.readLine();
    if(tmp.length() == 0)
      newStuff.setPrice(oldStuff.getPrice());
    else {
      newStuff.setPrice(Integer.parseInt(tmp));
      changed = true;
    }

    System.out.printf("판매자(%s) : ", oldStuff.getSeller());
    tmp = br.readLine();
    if(tmp.length() == 0)
      newStuff.setSeller(oldStuff.getSeller());
    else {
      newStuff.setSeller(tmp);
      changed = true;
    }
    
    if(changed) {
      this.stuffList.set(index, newStuff);
      System.out.println("물품 변경 완료");      
    }
    else
      System.out.println("물품 변경 취소");
  }
  
  public void deleteStuff() throws Exception {
    System.out.printf("물품 인덱스 : ");
    int index = Integer.parseInt(br.readLine());

    
    Stuff stuff = stuffList.get(index);
    if(stuff == null) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }
    this.stuffList.remove(index);
    System.out.println("물품 삭제 완료");
  }
}
