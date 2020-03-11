package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.StuffDao;
import kyh.tam.util.Prompt;

public class StuffDeleteServlet implements Servlet {
  StuffDao stuffDao;

  public StuffDeleteServlet(StuffDao stuffDao) {
    this.stuffDao = stuffDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    int number = Prompt.getInt(in, out, "번호 : ");
    if (stuffDao.delete(number) > 0) {
      out.write("Delete complete" + System.lineSeparator());
    } else {
      out.write("Delete failed" + System.lineSeparator());
    }
    out.flush();
  }
}
