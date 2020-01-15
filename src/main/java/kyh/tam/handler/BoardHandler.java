package kyh.tam.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import kyh.tam.domain.Board;
import kyh.util.Iterator;
import kyh.util.List;
import kyh.util.Prompt;

public class BoardHandler {    
  private List<Board> boardList;
  private Prompt prompt;
  
  public BoardHandler(Prompt prompt, List<Board> list) {
    this.prompt = prompt;
    this.boardList = list;
  }

  public void addBoard() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    
    Board board = new Board();
    board.setNumber(prompt.inputInt("번호 : "));
    board.setTitle(prompt.inputString("내용 : "));
    board.setWriteDate(new Date());
    board.setViewCount(0);
    
    // 작성자 처리부(Writer)
    
    this.boardList.add(board);
    System.out.println("게시글 추가 완료");
  }
  
  public void listBoard() {
    System.out.printf("-----------------------------------------------------------------------------\n");
    
    Iterator<Board> it = boardList.iterator();
    while(it.hasNext()) { 
      Board board = it.next();
      String wDate = new SimpleDateFormat("yyyy-MM-dd").format(board.getWriteDate());
      System.out.printf("%s, %s, %s, %s\n", board.getNumber(), 
          board.getTitle(), wDate, board.getViewCount());
    }
  }
  public void detailBoard() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    int index = indexOfBoard(prompt.inputInt("번호 : "));    
    if(index == -1) {
      System.out.println("게시글이 존재하지 않습니다.");
      return;
    }
    
    Board board = this.boardList.get(index);    
    System.out.printf("번호: %d\n", board.getNumber());
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("등록일: %s\n", new SimpleDateFormat("yyyy-MM-dd").format(board.getWriteDate()));
    System.out.printf("조회수: %d\n", board.getViewCount());
  }
  
  public void updateBoard() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    int index = indexOfBoard(prompt.inputInt("번호 : "));
    if(index == -1) {
      System.out.println("게시글이 존재하지 않습니다.");
      return;
    }
    
    Board oldBoard = boardList.get(index);
    Board newBoard = new Board();   
    
    newBoard.setNumber(oldBoard.getNumber());
    newBoard.setViewCount(oldBoard.getViewCount());
    newBoard.setWriteDate(new Date());
    newBoard.setTitle(prompt.inputString(
        String.format("제목(%s) : ", oldBoard.getTitle()), 
        oldBoard.getTitle()));
    
    if (newBoard.equals(oldBoard)) {
      System.out.println("게시글 변경 취소");
      return;
    }    
    this.boardList.set(index, newBoard);
    System.out.println("\n게시글 변경 완료");
  }
  
  public void deleteBoard() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    int index = indexOfBoard(prompt.inputInt("번호? "));
    
    if(index == -1) {
      System.out.println("게시글이 존재하지 않습니다.");
      return;
    }

    this.boardList.remove(index);
    System.out.println("게시글 삭제 완료");
  }
  
  private int indexOfBoard(int number) {
    for(int i = 0; i < this.boardList.size(); i++)
      if(this.boardList.get(i).getNumber() == number)
        return i;
    return -1;
  }
}
