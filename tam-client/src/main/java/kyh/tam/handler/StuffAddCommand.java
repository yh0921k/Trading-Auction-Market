package kyh.tam.handler;

import kyh.tam.dao.StuffDao;
import kyh.tam.domain.Stuff;
import kyh.util.Prompt;

public class StuffAddCommand implements Command {
  StuffDao stuffDao;
  Prompt prompt;

  public StuffAddCommand(StuffDao stuffDao, Prompt prompt) {
    this.stuffDao = stuffDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");
    Stuff stuff = new Stuff();
    stuff.setName(prompt.inputString("물품명 : "));
    stuff.setCategory(prompt.inputString("분류 : "));
    stuff.setState(prompt.inputString("상태 : "));
    stuff.setPrice(prompt.inputInt("가격 : "));
    stuff.setSeller(prompt.inputString("판매자 : "));

    try {
      stuffDao.insert(stuff);
      System.out.println("Save complete");

    } catch (Exception e) {
      System.out.println("[StuffAddCommand.java] : Save failed");
      e.printStackTrace();
    }
  }
}
