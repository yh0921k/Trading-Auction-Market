package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.StuffDao;
import kyh.tam.domain.Stuff;

public class StuffUpdateServlet implements Servlet {
  StuffDao stuffDao;

  public StuffUpdateServlet(StuffDao stuffDao) {
    this.stuffDao = stuffDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    out.write("번호 : " + System.lineSeparator());
    out.write("!{}!" + System.lineSeparator());
    out.flush();

    int number = Integer.parseInt(in.readLine());

    Stuff oldStuff = stuffDao.findByNumber(number);
    if (oldStuff == null) {
      out.write("Update failed : invalid number" + System.lineSeparator());
      out.flush();
      return;
    }

    Stuff newStuff = new Stuff();
    newStuff.setNumber(oldStuff.getNumber());
    out.write(String.format("물품명(%s) : \n!{}!\n", oldStuff.getName()));
    out.flush();
    newStuff.setName(in.readLine());
    out.write(String.format("상태(%s) : \n!{}!\n", oldStuff.getState()));
    out.flush();
    newStuff.setState(in.readLine());
    out.write(String.format("판매자(%s) : \n!{}!\n", oldStuff.getSeller()));
    out.flush();
    newStuff.setSeller(in.readLine());
    out.write(String.format("분류(%s) : \n!{}!\n", oldStuff.getCategory()));
    out.flush();
    newStuff.setCategory(in.readLine());
    out.write(String.format("가격(%s) : \n!{}!\n", oldStuff.getPrice()));
    out.flush();
    newStuff.setPrice(Integer.parseInt(in.readLine()));

    if (stuffDao.update(newStuff) > 0) {
      out.write("Update complete" + System.lineSeparator());
    } else {
      out.write("Update failed" + System.lineSeparator());
    }
    out.flush();
  }
}
