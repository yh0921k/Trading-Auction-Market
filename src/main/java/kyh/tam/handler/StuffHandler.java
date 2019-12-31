package kyh.tam.handler;

import java.io.BufferedReader;
import kyh.tam.domain.Stuff;

public class StuffHandler {
  int stuffCnt  = 0;
  Stuff[]  stuffs;
  private BufferedReader br;
  
  static final int STUFF_SIZE  = 100;
  
  public StuffHandler(BufferedReader br) {
    this.br = br;
    this.stuffs  = new Stuff[STUFF_SIZE];
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
    this.stuffs[this.stuffCnt++] = s;
  }
  public void printStuffList() {
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(int i=0; i<this.stuffCnt; i++)      
      System.out.printf("%s, %s, %s, %s, %s\n", 
          this.stuffs[i].getStuffNum(), this.stuffs[i].getStuffName(), this.stuffs[i].getCategory(), 
          this.stuffs[i].getSeller(), this.stuffs[i].getPrice());
  }
}
