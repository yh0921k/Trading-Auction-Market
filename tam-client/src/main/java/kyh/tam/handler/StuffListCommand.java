package kyh.tam.handler;

import java.util.List;
import kyh.tam.dao.StuffDao;
import kyh.tam.domain.Stuff;

public class StuffListCommand implements Command {
  StuffDao stuffDao;

  public StuffListCommand(StuffDao stuffDao) {
    this.stuffDao = stuffDao;
  }

  @Override
  public void execute() {
    System.out.println("--------------------------------------------------");
    try {
      List<Stuff> stuffs = stuffDao.findAll();
      for (Stuff s : stuffs)
        System.out.println(s);

    } catch (Exception e) {
      System.out.println("[StuffListCommand.java] : Read failed");
      e.printStackTrace();
    }
  }
}
