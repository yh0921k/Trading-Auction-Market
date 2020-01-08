package kyh.tam.handler;

import java.io.BufferedReader;
import kyh.tam.domain.Stuff;
import kyh.util.ArrayList;

public class StuffHandler {
  private BufferedReader br;
  private ArrayList<Stuff> stuffList;
  
  public StuffHandler(BufferedReader br) {
    this.br = br;
    this.stuffList = new ArrayList<>();
  }
  
  public StuffHandler(BufferedReader br, int capacity) {
    this.br = br;
    this.stuffList = new ArrayList<>(capacity);
  }
  
  public void addStuff() throws Exception {
    Stuff s = new Stuff();
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("번호 : ");
    s.setStuffNum(Integer.parseInt(br.readLine()));
    System.out.printf("물품명 : ");
    s.setStuffName(br.readLine());
    System.out.printf("판매자 : ");
    s.setSeller(br.readLine());
    System.out.printf("분류 : ");
    s.setCategory(br.readLine());
    System.out.printf("설명 : ");
    s.setStuffContents(br.readLine());
    System.out.printf("가격 : ");
    s.setPrice(Integer.parseInt(br.readLine()));            
    stuffList.add(s);
  }
  public void listStuff() {
    Stuff[] stuffs = stuffList.toArray(new Stuff[stuffList.size()]);
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(Stuff stuff : stuffs) {
      System.out.printf("%s, %s, %s, %s, %s\n", 
          stuff.getStuffNum(), stuff.getStuffName(), stuff.getCategory(), 
          stuff.getSeller(), stuff.getPrice());
    }
  }
  
  public void updateStuff() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("Index : ");
    int index = Integer.parseInt(br.readLine());
    
    Stuff oldStuff = stuffList.get(index);
    if(oldStuff == null) {
      System.out.println("Stuff does not exists.");
      return;
    }
    
    Stuff newStuff = new Stuff();
    newStuff.setStuffNum(oldStuff.getStuffNum());
    System.out.printf("물품명(%s) : ", oldStuff.getStuffName());
    String tmp = br.readLine();
    if(tmp.length() != 0) {
      newStuff.setStuffName(tmp);
    } else {
      newStuff.setStuffName(oldStuff.getStuffName());
    }
        
    System.out.printf("판매자 : ");
    newStuff.setSeller(br.readLine());
    System.out.printf("분류 : ");
    newStuff.setCategory(br.readLine());
    System.out.printf("설명 : ");
    newStuff.setStuffContents(br.readLine());
    System.out.printf("가격 : ");
    newStuff.setPrice(Integer.parseInt(br.readLine()));   
    

    this.stuffList.set(index, newStuff);
    System.out.println("\nStuff Update Complete.");
  }
  
  public void deleteStuff() throws Exception {
    System.out.printf("Index : ");
    int index = Integer.parseInt(br.readLine());

    
    Stuff member = stuffList.get(index);
    if(member == null) {
      System.out.println("Stuff does not exists.");
      return;
    }
    this.stuffList.remove(index);
    System.out.println("Delete Complete.");
  }
}
