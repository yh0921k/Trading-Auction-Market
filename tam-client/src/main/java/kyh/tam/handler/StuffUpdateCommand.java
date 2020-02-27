package kyh.tam.handler;

import kyh.tam.dao.StuffDao;
import kyh.tam.domain.Stuff;
import kyh.util.Prompt;

public class StuffUpdateCommand implements Command {
  StuffDao stuffDao;
  Prompt prompt;

  public StuffUpdateCommand(StuffDao stuffDao, Prompt prompt) {
    this.stuffDao = stuffDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");
    try {
      int number = prompt.inputInt("번호 : ");

      Stuff oldStuff;
      try {
        oldStuff = stuffDao.findByNumber(number);
      } catch (Exception e) {
        System.out.println("해당 번호의 물품이 없습니다.");
        return;
      }

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

      if (newStuff.toString().equals(oldStuff.toString())) {
        System.out.println("Update cancel");
        return;
      }

      stuffDao.update(newStuff);
      System.out.println("Update complete");

    } catch (Exception e) {
      System.out.println("[StuffUpdateCommand.java] : Update failed");
      e.printStackTrace();
    }
  }
}
