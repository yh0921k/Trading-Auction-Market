package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.StuffDao;

public class StuffDeleteServlet implements Servlet {
  StuffDao stuffDao;

  public StuffDeleteServlet(StuffDao stuffDao) {
    this.stuffDao = stuffDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    out.write("번호 : " + System.lineSeparator());
    out.write("!{}!" + System.lineSeparator());
    out.flush();

    int number = Integer.parseInt(in.readLine());

    if (stuffDao.delete(number) > 0) {
      out.write("Delete complete" + System.lineSeparator());
    } else {
      out.write("Delete failed" + System.lineSeparator());
    }
    out.flush();
  }
}
