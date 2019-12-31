package kyh.tam.handler;

import java.io.BufferedReader;
import kyh.tam.domain.Stuff;

public class StuffHandler {
  private BufferedReader br;
  private StuffList stuffList;
  
  public StuffHandler(BufferedReader br) {
    this.br = br;
    this.stuffList = new StuffList();
  }
  
  public StuffHandler(BufferedReader br, int capacity) {
    this.br = br;
    this.stuffList = new StuffList(capacity);
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
  public void printStuffList() {
    Stuff[] stuffs = stuffList.toArray();
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(Stuff stuff : stuffs)      
      System.out.printf("%s, %s, %s, %s, %s\n", 
          stuff.getStuffNum(), stuff.getStuffName(), stuff.getCategory(), 
          stuff.getSeller(), stuff.getPrice());
  }
}
