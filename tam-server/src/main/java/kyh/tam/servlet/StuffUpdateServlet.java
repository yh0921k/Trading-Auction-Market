package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.StuffDao;
import kyh.tam.domain.Stuff;
import kyh.tam.util.Prompt;

public class StuffUpdateServlet implements Servlet {
  StuffDao stuffDao;

  public StuffUpdateServlet(StuffDao stuffDao) {
    this.stuffDao = stuffDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    int number = Prompt.getInt(in, out, "번호 : ");
    Stuff oldStuff = stuffDao.findByNumber(number);
    if (oldStuff == null) {
      out.write("Update failed : invalid number" + System.lineSeparator());
      out.flush();
      return;
    }

    Stuff newStuff = new Stuff();
    newStuff.setNumber(oldStuff.getNumber());
    newStuff.setName(Prompt.getString(in, out, String.format("물품명(%s) : ", oldStuff.getName()),
        oldStuff.getName()));
    newStuff.setState(Prompt.getString(in, out, String.format("상태(%s) : ", oldStuff.getState()),
        oldStuff.getState()));
    newStuff.setSeller(Prompt.getString(in, out, String.format("판매자(%s) : ", oldStuff.getSeller()),
        oldStuff.getSeller()));
    newStuff.setCategory(Prompt.getString(in, out,
        String.format("분류(%s) : ", oldStuff.getCategory()), oldStuff.getCategory()));
    newStuff.setPrice(Prompt.getInt(in, out, String.format("가격(%d) : ", oldStuff.getPrice()),
        String.valueOf(oldStuff.getPrice())));

    if (stuffDao.update(newStuff) > 0) {
      out.write("Update complete" + System.lineSeparator());
    } else {
      out.write("Update failed" + System.lineSeparator());
    }
    out.flush();
  }
}
