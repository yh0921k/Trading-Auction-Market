package kyh.tam.handler;

import java.util.List;
import kyh.tam.domain.Board;
import kyh.util.Prompt;

public class BoardDeleteCommand implements Command {
  private List<Board> boardList;
  private Prompt prompt;

  public BoardDeleteCommand(Prompt prompt, List<Board> list) {
    this.prompt = prompt;
    this.boardList = list;
  }

  @Override
  public void execute() throws Exception {
    System.out
        .printf("-----------------------------------------------------------------------------\n");
    int index = indexOfBoard(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("게시글이 존재하지 않습니다.");
      return;
    }

    this.boardList.remove(index);
    System.out.println("게시글 삭제 완료");
  }

  private int indexOfBoard(int number) {
    for (int i = 0; i < this.boardList.size(); i++)
      if (this.boardList.get(i).getNumber() == number)
        return i;
    return -1;
  }
}
