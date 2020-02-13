package kyh.tam;

import java.util.Map;
import kyh.tam.context.ApplicationContextListener;
import kyh.tam.dao.BoardObjectFileDao;
import kyh.tam.dao.MemberObjectFileDao;
import kyh.tam.dao.StuffObjectFileDao;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) throws Exception {
    System.out.println("--------------------------------------------------");

    BoardObjectFileDao boardDao = new BoardObjectFileDao("./data/board.ser2");
    MemberObjectFileDao memberDao = new MemberObjectFileDao("./data/member.ser2");
    StuffObjectFileDao stuffDao = new StuffObjectFileDao("./data/stuff.ser2");

    context.put("boardDao", boardDao);
    context.put("memberDao", memberDao);
    context.put("stuffDao", stuffDao);
    System.out.println("--------------------------------------------------");
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) throws Exception {
    System.out.println("--------------------------------------------------");
  }
}
