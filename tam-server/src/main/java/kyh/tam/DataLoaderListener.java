package kyh.tam;

import java.util.Map;
import kyh.tam.context.ApplicationContextListener;
import kyh.tam.dao.json.BoardJsonFileDao;
import kyh.tam.dao.json.MemberJsonFileDao;
import kyh.tam.dao.json.StuffJsonFileDao;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) throws Exception {
    System.out.println("--------------------------------------------------");

    context.put("boardDao", new BoardJsonFileDao("./data/board.json"));
    context.put("memberDao", new MemberJsonFileDao("./data/member.json"));
    context.put("stuffDao", new StuffJsonFileDao("./data/stuff.json"));
    System.out.println("--------------------------------------------------");
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) throws Exception {
    System.out.println("--------------------------------------------------");
  }
}
