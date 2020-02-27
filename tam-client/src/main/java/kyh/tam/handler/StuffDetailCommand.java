package kyh.tam.handler;

import kyh.tam.dao.StuffDao;
import kyh.tam.domain.Stuff;
import kyh.util.Prompt;

public class StuffDetailCommand implements Command {
  StuffDao stuffDao;
  Prompt prompt;

  public StuffDetailCommand(StuffDao stuffDao, Prompt prompt) {
    this.stuffDao = stuffDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");
    try {
      int number = prompt.inputInt("번호 : ");

      Stuff stuff = stuffDao.findByNumber(number);
      System.out.printf("번호 : %s\n", stuff.getNumber());
      System.out.printf("물품명 : %s\n", stuff.getName());
      System.out.printf("분류 : %s\n", stuff.getCategory());
      System.out.printf("상태 : %s\n", stuff.getState());
      System.out.printf("가격 : %d\n", stuff.getPrice());
      System.out.printf("판매자 : %s\n", stuff.getSeller());

    } catch (Exception e) {
      System.out.println("[StuffDetailCommand.java] : Read failed");
      e.printStackTrace();
    }
  }
}
