package kyh.tam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App {   
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    final int STUFF_SIZE  = 100;
    final int MEMBER_SIZE = 100;
    final int BOARD_SIZE  = 100;
    int stuffCnt  = 0;
    int memberCnt = 0;      
    int boardCnt  = 0;      

    class Stuff {
      int stuffNum;
      String stuffName;
      String stuffContents;
      String seller;
      String category;
      int price;
    }
    class Member {
      int personNum;
      String personName;
      String email;
      String address;
      String password;
      String picture;
      String phoneNum;
      Date registeredDate;
    }
    class Board {
      int postNum;
      String detail;
      Date writeDate;
      int viewCount;
    }
    Board[]  boards  = new Board[BOARD_SIZE];
    Member[] members = new Member[MEMBER_SIZE]; 
    Stuff[]  stuffs  = new Stuff[STUFF_SIZE];
    String   command;
    
    do {
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("\n$ ");
      command = br.readLine();

      switch (command) {
        case "/stuff/add":
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
          break;
        case "/stuff/list":
          System.out.printf("-----------------------------------------------------------------------------\n");
          for(int i=0; i<stuffCnt; i++)      
            System.out.printf("%s, %s, %s, %s, %s\n", 
                stuffs[i].stuffNum, stuffs[i].stuffName, stuffs[i].category, stuffs[i].seller, stuffs[i].price);
          break;
        case "/member/add":
          Member m = new Member();
          System.out.printf("-----------------------------------------------------------------------------\n");
          System.out.printf("번호 : ");
          m.personNum = Integer.parseInt(br.readLine());
          System.out.printf("이름 : ");
          m.personName = br.readLine();
          System.out.printf("Email : ");
          m.email = br.readLine();
          System.out.printf("주소 : ");
          m.address = br.readLine();
          System.out.printf("암호 : ");
          m.password = br.readLine();
          System.out.printf("사진 : ");
          m.picture = br.readLine();
          System.out.printf("전화 : ");
          m.phoneNum = br.readLine();
          m.registeredDate = new Date();
          members[memberCnt++] = m;
          break;
        case "/member/list":
          System.out.printf("-----------------------------------------------------------------------------\n");
          for(int i=0; i<memberCnt; i++)      
            System.out.printf("%s, %s, %s, %s, %s\n", 
                members[i].personNum, members[i].personName, members[i].address, members[i].phoneNum,
                new SimpleDateFormat("yyyy-MM-dd").format(members[i].registeredDate));
          break;
        case "/board/add":
          Board b = new Board();
          System.out.printf("-----------------------------------------------------------------------------\n");
          System.out.printf("번호 : ");
          b.postNum = Integer.parseInt(br.readLine());
          System.out.printf("내용 : ");
          b.detail = br.readLine();
          b.writeDate = new Date();
          b.viewCount = 0;
          boards[boardCnt++] = b;
          break;
        case "/board/list":
          System.out.printf("-----------------------------------------------------------------------------\n");
          for(int i=0; i<boardCnt; i++) { 
            String wDate = new SimpleDateFormat("yyyy-MM-dd").format(boards[i].writeDate);
            System.out.printf("%s, %s, %s, %s\n", boards[i].postNum, boards[i].detail, wDate, boards[i].viewCount);
          }
          break;
        default:
          if (!command.equalsIgnoreCase("quit"))
            System.out.printf("Incorrect Command\n");
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("Bye");
  }
}
