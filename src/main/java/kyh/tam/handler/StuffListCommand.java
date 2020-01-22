package kyh.tam.handler;

import java.util.Iterator;
import java.util.List;
import kyh.tam.domain.Stuff;

public class StuffListCommand implements Command {
  private List<Stuff> stuffList;

  public StuffListCommand(List<Stuff> list) {
    this.stuffList = list;
  }

  @Override
  public void execute() {
    System.out
        .printf("-----------------------------------------------------------------------------\n");
    Iterator<Stuff> it = stuffList.iterator();
    while (it.hasNext()) {
      Stuff stuff = it.next();
      System.out.printf("%s, %s, %s, %s, %s, %s\n", stuff.getNumber(), stuff.getName(),
          stuff.getCategory(), stuff.getState(), stuff.getPrice(), stuff.getSeller());
    }
  }
}
