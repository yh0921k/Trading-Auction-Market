package kyh.tam.handler;

import java.text.SimpleDateFormat;
import java.util.List;
import kyh.tam.domain.Board;
import kyh.util.Prompt;

public class BoardDetailCommand implements Command {
  private List<Board> boardList;
  private Prompt prompt;

  public BoardDetailCommand(Prompt prompt, List<Board> list) {
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

    Board board = this.boardList.get(index);
    System.out.printf("번호: %d\n", board.getNumber());
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("등록일: %s\n", new SimpleDateFormat("yyyy-MM-dd").format(board.getWriteDate()));
    System.out.printf("조회수: %d\n", board.getViewCount());
  }

  private int indexOfBoard(int number) {
    for (int i = 0; i < this.boardList.size(); i++)
      if (this.boardList.get(i).getNumber() == number)
        return i;
    return -1;
  }
}
