package kyh.tam.handler;

import java.util.List;
import kyh.tam.domain.Stuff;
import kyh.util.Prompt;

public class StuffDeleteCommand implements Command {
  private List<Stuff> stuffList;
  private Prompt prompt;

  public StuffDeleteCommand(Prompt prompt, List<Stuff> list) {
    this.prompt = prompt;
    this.stuffList = list;
  }

  @Override
  public void execute() throws Exception {
    System.out
        .printf("-----------------------------------------------------------------------------\n");
    int index = indexOfStuff(prompt.inputInt("번호 : "));
    if (index == -1) {
      System.out.println("물품이 존재하지 않습니다");
      return;
    }
    this.stuffList.remove(index);
    System.out.println("물품 삭제 완료");
  }

  private int indexOfStuff(int number) {
    for (int i = 0; i < this.stuffList.size(); i++)
      if (this.stuffList.get(i).getNumber() == number)
        return i;
    return -1;
  }
}
