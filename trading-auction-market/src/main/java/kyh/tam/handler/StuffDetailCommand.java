package kyh.tam.handler;

import java.util.List;
import kyh.tam.domain.Stuff;
import kyh.util.Prompt;

public class StuffDetailCommand implements Command {
  private List<Stuff> stuffList;
  private Prompt prompt;

  public StuffDetailCommand(Prompt prompt, List<Stuff> list) {
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

    Stuff stuff = this.stuffList.get(index);
    System.out.printf("번호 : %s\n", stuff.getNumber());
    System.out.printf("물품명 : %s\n", stuff.getName());
    System.out.printf("분류 : %s\n", stuff.getCategory());
    System.out.printf("상태 : %s\n", stuff.getState());
    System.out.printf("가격 : %d\n", stuff.getPrice());
    System.out.printf("판매자 : %s\n", stuff.getSeller());
  }

  private int indexOfStuff(int number) {
    for (int i = 0; i < this.stuffList.size(); i++)
      if (this.stuffList.get(i).getNumber() == number)
        return i;
    return -1;
  }
}
