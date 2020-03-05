package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.StuffDao;
import kyh.tam.domain.Stuff;

public class StuffDetailServlet implements Servlet {
  StuffDao stuffDao;

  public StuffDetailServlet(StuffDao stuffDao) {
    this.stuffDao = stuffDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    out.write("번호 : " + System.lineSeparator());
    out.write("!{}!" + System.lineSeparator());
    out.flush();

    int number = Integer.parseInt(in.readLine());

    Stuff stuff = stuffDao.findByNumber(number);
    if (stuff != null) {
      out.write("번호 : " + stuff.getNumber() + System.lineSeparator());
      out.write("물품명 : " + stuff.getName() + System.lineSeparator());
      out.write("상태 : " + stuff.getState() + System.lineSeparator());
      out.write("판매자 : " + stuff.getSeller() + System.lineSeparator());
      out.write("분류 : " + stuff.getCategory() + System.lineSeparator());
      out.write("가격 : " + stuff.getPrice() + System.lineSeparator());
    } else {
      out.write("Read failed : invalid number" + System.lineSeparator());
    }
    out.flush();
  }
}
