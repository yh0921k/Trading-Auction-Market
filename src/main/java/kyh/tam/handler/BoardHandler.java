package kyh.tam.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import kyh.tam.domain.Board;
import kyh.util.ArrayList;

public class BoardHandler {    
  private ArrayList<Board> boardList;
  private BufferedReader br;
  
  public BoardHandler(BufferedReader br) {
    this.br = br;
    boardList = new ArrayList<>();
  }
  
  public BoardHandler(BufferedReader br, int capacity) {
    this.br = br;
    boardList = new ArrayList<>(capacity);
  }  

  public void addBoard() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    
    Board board = new Board();
    System.out.printf("번호 : ");
    board.setNumber(Integer.parseInt(br.readLine()));
    
    System.out.printf("내용 : ");
    board.setTitle(br.readLine());
    
    board.setWriteDate(new Date());
    board.setViewCount(0);
    
    // 작성자 처리부
    
    this.boardList.add(board);
    System.out.println("게시글 추가 완료");

  }
  
  public void listBoard() {
    System.out.printf("-----------------------------------------------------------------------------\n");
    
    Board[] boards = boardList.toArray(new Board[boardList.size()]);
    for(Board board : boards) { 
      String wDate = new SimpleDateFormat("yyyy-MM-dd").format(board.getWriteDate());
      System.out.printf("%s, %s, %s, %s\n", board.getNumber(), 
          board.getTitle(), wDate, board.getViewCount());
    }
  }
  public void detailBoard() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("게시글 인덱스 : ");
    int index = Integer.parseInt(br.readLine());

    Board board = this.boardList.get(index);
    if(board == null) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }
    System.out.printf("번호: %d\n", board.getNumber());
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("등록일: %s\n", new SimpleDateFormat("yyyy-MM-dd").format(board.getWriteDate()));
    System.out.printf("조회수: %d\n", board.getViewCount());
  }
  
  public void updateBoard() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("게시글 인덱스 : ");
    int index = Integer.parseInt(br.readLine());
    
    Board oldBoard = boardList.get(index);    
    if(oldBoard == null) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }
    
    System.out.printf("게시글 제목(%s) : ", oldBoard.getTitle());
    String tmp = br.readLine();
    if(tmp.length() == 0) {
      System.out.println("변경 취소");
      return;
    }
    
    Board newBoard = new Board();    
    newBoard.setNumber(oldBoard.getNumber());
    newBoard.setTitle(tmp);
    newBoard.setWriteDate(new Date());
    newBoard.setViewCount(oldBoard.getViewCount());
    this.boardList.set(index, newBoard);
    System.out.println("\n게시글 변경 완료");
  }
  
  public void deleteBoard() throws Exception {
    System.out.printf("게시글 인덱스 : ");
    int index = Integer.parseInt(br.readLine());
    
    Board board = boardList.get(index);
    if(board == null) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }
    this.boardList.remove(index);
    System.out.println("게시글 삭제 완료");
  }
}
