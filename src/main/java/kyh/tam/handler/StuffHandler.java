package kyh.tam.handler;

import java.io.BufferedReader;

public class StuffHandler {
  static class Stuff {
    int stuffNum;
    String stuffName;
    String stuffContents;
    String seller;
    String category;
    int price;
  }
  
  static final int STUFF_SIZE  = 100;
  static int stuffCnt  = 0;
  static Stuff[]  stuffs  = new Stuff[STUFF_SIZE];
  public static BufferedReader br;
  
  public static void addStuff() throws Exception {
    Stuff s = new Stuff();
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("번호 : ");
    s.stuffNum = Integer.parseInt(br.readLine());
    System.out.printf("물품명 : ");
    s.stuffName = br.readLine();
    System.out.printf("판매자 : ");
    s.seller = br.readLine();
    System.out.printf("분류 : ");
    s.category = br.readLine();
    System.out.printf("설명 : ");
    s.stuffContents = br.readLine();
    System.out.printf("가격 : ");
    s.price = Integer.parseInt(br.readLine());            
    stuffs[stuffCnt++] = s;
  }
  public static void printStuffList() {
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(int i=0; i<stuffCnt; i++)      
      System.out.printf("%s, %s, %s, %s, %s\n", 
          stuffs[i].stuffNum, stuffs[i].stuffName, stuffs[i].category, stuffs[i].seller, stuffs[i].price);
  }
}
