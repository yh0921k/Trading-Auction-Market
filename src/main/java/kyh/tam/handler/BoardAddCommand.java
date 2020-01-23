package kyh.tam.handler;

import java.sql.Date;
import java.util.List;
import kyh.tam.domain.Board;
import kyh.util.Prompt;

public class BoardAddCommand implements Command {
  private List<Board> boardList;
  private Prompt prompt;

  public BoardAddCommand(Prompt prompt, List<Board> list) {
    this.prompt = prompt;
    this.boardList = list;
  }

  @Override
  public void execute() throws Exception {
    System.out
        .printf("-----------------------------------------------------------------------------\n");

    Board board = new Board();
    board.setNumber(prompt.inputInt("번호 : "));
    board.setTitle(prompt.inputString("내용 : "));
    board.setWriteDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);

    // 작성자 처리부(Writer)

    this.boardList.add(board);
    System.out.println("게시글 추가 완료");
  }
}
