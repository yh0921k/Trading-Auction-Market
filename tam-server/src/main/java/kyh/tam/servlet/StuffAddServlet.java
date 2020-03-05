package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.StuffDao;
import kyh.tam.domain.Stuff;

public class StuffAddServlet implements Servlet {
  StuffDao stuffDao;

  public StuffAddServlet(StuffDao stuffDao) {
    this.stuffDao = stuffDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    Stuff stuff = new Stuff();

    out.write("물품명 : \n!{}!\n");
    out.flush();
    stuff.setName(in.readLine());
    out.write("분류 : \n!{}!\n");
    out.flush();
    stuff.setCategory(in.readLine());
    out.write("상태 : \n!{}!\n");
    out.flush();
    stuff.setState(in.readLine());
    out.write("가격 : \n!{}!\n");
    out.flush();
    stuff.setPrice(Integer.parseInt(in.readLine()));
    out.write("판매자 : \n!{}!\n");
    out.flush();
    stuff.setSeller(in.readLine());

    if (stuffDao.insert(stuff) > 0)
      out.write("Save complete" + System.lineSeparator());
    else
      out.write("Save failed" + System.lineSeparator());
    out.flush();
  }
}
