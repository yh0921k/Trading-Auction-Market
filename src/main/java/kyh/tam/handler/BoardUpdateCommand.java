package kyh.tam.handler;

import java.sql.Date;
import java.util.List;
import kyh.tam.domain.Board;
import kyh.util.Prompt;

public class BoardUpdateCommand implements Command {
  private List<Board> boardList;
  private Prompt prompt;

  public BoardUpdateCommand(Prompt prompt, List<Board> list) {
    this.prompt = prompt;
    this.boardList = list;
  }

  @Override
  public void execute() throws Exception {
    System.out
        .printf("-----------------------------------------------------------------------------\n");
    int index = indexOfBoard(prompt.inputInt("번호 : "));
    if (index == -1) {
      System.out.println("게시글이 존재하지 않습니다.");
      return;
    }

    Board oldBoard = boardList.get(index);
    Board newBoard = new Board();

    newBoard.setNumber(oldBoard.getNumber());
    newBoard.setViewCount(oldBoard.getViewCount());
    newBoard.setWriteDate(new Date(System.currentTimeMillis()));
    newBoard.setTitle(
        prompt.inputString(String.format("제목(%s) : ", oldBoard.getTitle()), oldBoard.getTitle()));

    if (newBoard.equals(oldBoard)) {
      System.out.println("게시글 변경 취소");
      return;
    }
    this.boardList.set(index, newBoard);
    System.out.println("\n게시글 변경 완료");
  }

  private int indexOfBoard(int number) {
    for (int i = 0; i < this.boardList.size(); i++)
      if (this.boardList.get(i).getNumber() == number)
        return i;
    return -1;
  }
}
