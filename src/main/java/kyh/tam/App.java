package kyh.tam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App {   
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
  static final int STUFF_SIZE  = 100;
  static final int MEMBER_SIZE = 100;
  static final int BOARD_SIZE  = 100;
  static int stuffCnt  = 0;
  static int memberCnt = 0;      
  static int boardCnt  = 0;      
  
  static class Stuff {
    int stuffNum;
    String stuffName;
    String stuffContents;
    String seller;
    String category;
    int price;
  }
  static class Member {
    int personNum;
    String personName;
    String email;
    String address;
    String password;
    String picture;
    String phoneNum;
    Date registeredDate;
  }
  static class Board {
    int postNum;
    String detail;
    Date writeDate;
    int viewCount;
  }
  static Board[]  boards  = new Board[BOARD_SIZE];
  static Member[] members = new Member[MEMBER_SIZE]; 
  static Stuff[]  stuffs  = new Stuff[STUFF_SIZE];
  static String   command;
  
  public static void main(String[] args) throws Exception {
    
    do {
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("\n$ ");
      command = br.readLine();

      switch (command) {
        case "/stuff/add":
          addStuff();
          break;
        case "/stuff/list":
          printStuffList();
          break;
        case "/member/add":
          addMember();
          break;
        case "/member/list":
          printMemberList();
          break;
        case "/board/add":
          addBoard();
          break;
        case "/board/list":
          printBoardList();
          break;
        default:
          if (!command.equalsIgnoreCase("quit"))
            System.out.printf("Incorrect Command\n");
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("Bye");
  }
  static void addStuff() throws Exception {
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
  static void printStuffList() {
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(int i=0; i<stuffCnt; i++)      
      System.out.printf("%s, %s, %s, %s, %s\n", 
          stuffs[i].stuffNum, stuffs[i].stuffName, stuffs[i].category, stuffs[i].seller, stuffs[i].price);
  }
  static void addMember() throws Exception {
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
  }
  static void printMemberList() {
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(int i=0; i<memberCnt; i++)      
      System.out.printf("%s, %s, %s, %s, %s\n", 
          members[i].personNum, members[i].personName, members[i].address, members[i].phoneNum,
          new SimpleDateFormat("yyyy-MM-dd").format(members[i].registeredDate));
  }
  static void addBoard() throws Exception {
    Board b = new Board();
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("번호 : ");
    b.postNum = Integer.parseInt(br.readLine());
    System.out.printf("내용 : ");
    b.detail = br.readLine();
    b.writeDate = new Date();
    b.viewCount = 0;
    boards[boardCnt++] = b;
  }
  static void printBoardList() {
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(int i=0; i<boardCnt; i++) { 
      String wDate = new SimpleDateFormat("yyyy-MM-dd").format(boards[i].writeDate);
      System.out.printf("%s, %s, %s, %s\n", boards[i].postNum, boards[i].detail, wDate, boards[i].viewCount);
    }
  }
}
