package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.List;
import kyh.tam.dao.StuffDao;
import kyh.tam.domain.Stuff;

public class StuffListServlet implements Servlet {
  StuffDao stuffDao;

  public StuffListServlet(StuffDao stuffDao) {
    this.stuffDao = stuffDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    List<Stuff> stuffs = stuffDao.findAll();
    for (Stuff s : stuffs)
      out.write(String.format("%d, %s, %s, %s, %s, %d", s.getNumber(), s.getName(), s.getState(),
          s.getSeller(), s.getCategory(), s.getPrice()) + System.lineSeparator());
  }
}
