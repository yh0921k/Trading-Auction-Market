package kyh.tam.handler;

import java.util.List;
import kyh.tam.domain.Stuff;
import kyh.util.Prompt;

public class StuffAddCommand implements Command {
  private List<Stuff> stuffList;
  private Prompt prompt;

  public StuffAddCommand(Prompt prompt, List<Stuff> list) {
    this.prompt = prompt;
    this.stuffList = list;
  }

  @Override
  public void execute() throws Exception {
    System.out
        .printf("-----------------------------------------------------------------------------\n");
    Stuff stuff = new Stuff();

    stuff.setNumber(prompt.inputInt("번호 : "));
    stuff.setName(prompt.inputString("물품명 : "));
    stuff.setCategory(prompt.inputString("분류 : "));
    stuff.setState(prompt.inputString("상태 : "));
    stuff.setPrice(prompt.inputInt("가격 : "));
    stuff.setSeller(prompt.inputString("판매자 : "));

    this.stuffList.add(stuff);
    System.out.println("저장하였습니다.");
  }
}
