package kyh.tam.handler;

import kyh.tam.domain.Stuff;
import kyh.util.ArrayList;
import kyh.util.Prompt;

public class StuffHandler {
  private ArrayList<Stuff> stuffList;
  private Prompt prompt;
  
  public StuffHandler(Prompt prompt) {
    this.prompt = prompt;
    this.stuffList = new ArrayList<>();
  }
  
  public StuffHandler(Prompt prompt, int capacity) {
    this.prompt = prompt;
    this.stuffList = new ArrayList<>(capacity);
  }
  
  public void addStuff() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    Stuff stuff = new Stuff();
    
    stuff.setNumber(prompt.inputInt("번호 : "));
    stuff.setName(prompt.inputString("물품명 : "));
    stuff.setCategory(prompt.inputString("분류 : "));
    stuff.setState(prompt.inputString("상태 : "));
    stuff.setPrice(prompt.inputInt("가격 : "));
    stuff.setSeller(prompt.inputString("판매자 : "));

    this.stuffList.add(stuff);
    System.out.println("저장하였습니다.");
  }
  
  public void listStuff() {
    System.out.printf("-----------------------------------------------------------------------------\n");
    Stuff[] stuffs = stuffList.toArray(new Stuff[stuffList.size()]);
    for(Stuff stuff : stuffs) {
      System.out.printf("%s, %s, %s, %s, %s, %s\n", 
          stuff.getNumber(), stuff.getName(), stuff.getCategory(), 
          stuff.getState(), stuff.getPrice(), stuff.getSeller());
    }
  }
  
  public void detailStuff() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    int index = indexOfStuff(prompt.inputInt("번호 : "));
    if(index == -1) {
      System.out.println("물품이 존재하지 않습니다");
      return;
    }
    
    Stuff stuff = this.stuffList.get(index);    
    System.out.printf("번호 : %s\n", stuff.getNumber());
    System.out.printf("물품명 : %s\n", stuff.getName());
    System.out.printf("분류 : %s\n", stuff.getCategory());
    System.out.printf("상태 : %s\n", stuff.getState());
    System.out.printf("가격 : %d\n", stuff.getPrice());
    System.out.printf("판매자 : %s\n", stuff.getSeller());
  }
  
  public void updateStuff() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    int index = indexOfStuff(prompt.inputInt("번호 : "));
    if(index == -1) {
      System.out.println("물품이 존재하지 않습니다");
      return;
    }
    
    Stuff oldStuff = stuffList.get(index);    
    Stuff newStuff = new Stuff();
    
    newStuff.setNumber(oldStuff.getNumber());
    
    newStuff.setName(prompt.inputString(
        String.format("물품명(%s) : ", oldStuff.getName()),
        oldStuff.getName()));
    
    newStuff.setCategory(prompt.inputString(
        String.format("분류(%s) : ", oldStuff.getCategory()),
        oldStuff.getCategory()));        
    
    newStuff.setState(prompt.inputString(
        String.format("상태(%s) : ", oldStuff.getState()),
        oldStuff.getState()));    
    
    newStuff.setPrice(prompt.inputInt(
        String.format("가격(%d) : ", oldStuff.getPrice()),
        oldStuff.getPrice()));    

    newStuff.setSeller(prompt.inputString(
        String.format("판매자(%s) : ", oldStuff.getSeller()),
        oldStuff.getSeller()));  

    if(newStuff.equals(oldStuff)) {
      System.out.println("물품 변경 취소");
      return;
    }
    this.stuffList.set(index, newStuff);
    System.out.println("물품 변경 완료");        
  }
  
  public void deleteStuff() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    int index = indexOfStuff(prompt.inputInt("번호 : "));
    if(index == -1) {
      System.out.println("물품이 존재하지 않습니다");
      return;
    }
    this.stuffList.remove(index);
    System.out.println("물품 삭제 완료");
  }
  
  private int indexOfStuff(int number) {
    for(int i = 0; i < this.stuffList.size(); i++)
      if(this.stuffList.get(i).getNumber() == number)
        return i;
    return -1;
  }
}
