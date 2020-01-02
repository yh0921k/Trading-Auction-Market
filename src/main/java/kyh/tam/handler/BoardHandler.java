package kyh.tam.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import kyh.tam.domain.Board;

public class BoardHandler {    
  private BufferedReader br;
  private ArrayList boardList;
  
  public BoardHandler(BufferedReader br) {
    this.br = br;
    boardList = new ArrayList();
  }
  
  public BoardHandler(BufferedReader br, int capacity) {
    this.br = br;
    boardList = new ArrayList(capacity);
  }  

  public void addBoard() throws Exception {
    Board b = new Board();
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("번호 : ");
    b.setPostNum(Integer.parseInt(br.readLine()));
    System.out.printf("내용 : ");
    b.setDetail(br.readLine());
    b.setWriteDate(new Date());
    b.setViewCount(0);
    boardList.add(b);
  }
  public void printBoardList() {
    Object[] boards = boardList.toArray();
    System.out.printf("-----------------------------------------------------------------------------\n");
    for(Object obj : boards) { 
      Board board = (Board)obj;
      String wDate = new SimpleDateFormat("yyyy-MM-dd").format(board.getWriteDate());
      System.out.printf("%s, %s, %s, %s\n", board.getPostNum(), 
          board.getDetail(), wDate, board.getViewCount());
    }
  }
  public void printDetailBoard() throws Exception {
    System.out.printf("번호 : ");
    int boardNum = Integer.parseInt(br.readLine());

    Board board = (Board)boardList.get(boardNum);
    if(board == null) {
      System.out.println("Board Number does not exists.");
      return;
    }

    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%36s%4s%26s%25s%12s%6s\n", "번호", " ", "내용", " ", "작성일", " ", "조회수");
    System.out.printf("%4d%4s%20s%10s%9s%8s\n", 
        board.getPostNum(), " ", board.getDetail(), " ", 
        new SimpleDateFormat("yyyy-MM-dd").format(board.getWriteDate()), board.getViewCount());

  }
}
