package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.StuffDao;
import kyh.tam.domain.Stuff;
import kyh.tam.util.Prompt;

public class StuffAddServlet implements Servlet {
  StuffDao stuffDao;

  public StuffAddServlet(StuffDao stuffDao) {
    this.stuffDao = stuffDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    Stuff stuff = new Stuff();
    stuff.setName(Prompt.getString(in, out, "물품명 : "));
    stuff.setCategory(Prompt.getString(in, out, "분류 : "));
    stuff.setState(Prompt.getString(in, out, "상태 : "));
    stuff.setPrice(Prompt.getInt(in, out, "가격 : "));
    stuff.setSeller(Prompt.getString(in, out, "판매자 : "));

    if (stuffDao.insert(stuff) > 0)
      out.write("Save complete" + System.lineSeparator());
    else
      out.write("Save failed" + System.lineSeparator());
    out.flush();
  }
}
