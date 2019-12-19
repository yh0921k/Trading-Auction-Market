package kyh.tam;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {   
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    final int SIZE = 100;
    int tmpCnt = 0;       

    class Stuff {
      int stuffNum;
      String stuffName;
      String stuffContents;
      String seller;
      String category;
      int price;
    }
    Stuff[] stuffs = new Stuff[SIZE];
    
    for(int i = 0; i < SIZE; i++) { 
      Stuff s = new Stuff();
      tmpCnt++;
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
      stuffs[i] = s;
      
      System.out.printf("계속 입력하시겠습니까? (Y/n) : ");
      String tmp = br.readLine();
      if(tmp.equalsIgnoreCase("y"))
        continue;
      else if(tmp.equalsIgnoreCase("n")) 
        break;
      else {
        System.out.println("잘못 입력하셨습니다.\n프로그램을 종료합니다.\n");
        return;
      }
    }

    System.out.printf("-----------------------------------------------------------------------------\n");
    for(int i=0; i<tmpCnt; i++)      
      System.out.printf("%s, %s, %s, %s, %s\n", 
          stuffs[i].stuffNum, stuffs[i].stuffName, stuffs[i].category, stuffs[i].seller, stuffs[i].price);
    System.out.printf("-----------------------------------------------------------------------------\n");
  }
}
