package kyh.tam.handler;

import java.util.List;
import kyh.tam.domain.Stuff;
import kyh.util.Prompt;

public class StuffUpdateCommand implements Command {
  private List<Stuff> stuffList;
  private Prompt prompt;

  public StuffUpdateCommand(Prompt prompt, List<Stuff> list) {
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

    Stuff oldStuff = stuffList.get(index);
    Stuff newStuff = new Stuff();

    newStuff.setNumber(oldStuff.getNumber());

    newStuff.setName(
        prompt.inputString(String.format("물품명(%s) : ", oldStuff.getName()), oldStuff.getName()));

    newStuff.setCategory(prompt.inputString(String.format("분류(%s) : ", oldStuff.getCategory()),
        oldStuff.getCategory()));

    newStuff.setState(
        prompt.inputString(String.format("상태(%s) : ", oldStuff.getState()), oldStuff.getState()));

    newStuff.setPrice(
        prompt.inputInt(String.format("가격(%d) : ", oldStuff.getPrice()), oldStuff.getPrice()));

    newStuff.setSeller(prompt.inputString(String.format("판매자(%s) : ", oldStuff.getSeller()),
        oldStuff.getSeller()));

    if (newStuff.equals(oldStuff)) {
      System.out.println("물품 변경 취소");
      return;
    }
    this.stuffList.set(index, newStuff);
    System.out.println("물품 변경 완료");
  }

  private int indexOfStuff(int number) {
    for (int i = 0; i < this.stuffList.size(); i++)
      if (this.stuffList.get(i).getNumber() == number)
        return i;
    return -1;
  }
}
