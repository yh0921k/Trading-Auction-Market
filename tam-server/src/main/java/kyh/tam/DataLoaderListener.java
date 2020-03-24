package kyh.tam;

import java.sql.Connection;
import java.util.Map;
import kyh.tam.context.ApplicationContextListener;
import kyh.tam.dao.mariadb.BoardDaoImpl;
import kyh.tam.dao.mariadb.MemberDaoImpl;
import kyh.tam.dao.mariadb.PhotoBoardDaoImpl;
import kyh.tam.dao.mariadb.PhotoFileDaoImpl;
import kyh.tam.dao.mariadb.StuffDaoImpl;
import kyh.tam.sql.PlatformTransactionManager;
import kyh.tam.util.DataSource;

public class DataLoaderListener implements ApplicationContextListener {

  public static Connection con;

  @Override
  public void contextInitialized(Map<String, Object> context) throws Exception {
    System.out.println("--------------------------------------------------");

    try {
      String jdbcUrl = "jdbc:mariadb://localhost:3306/tamdb";
      String username = "kyh";
      String password = "1111";

      DataSource dataSource = new DataSource(jdbcUrl, username, password);
      context.put("dataSource", dataSource);

      PlatformTransactionManager txManager = new PlatformTransactionManager(dataSource);
      context.put("txManager", txManager);

      context.put("boardDao", new BoardDaoImpl(dataSource));
      context.put("stuffDao", new StuffDaoImpl(dataSource));
      context.put("memberDao", new MemberDaoImpl(dataSource));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(dataSource));
      context.put("photoFileDao", new PhotoFileDaoImpl(dataSource));

    } catch (Exception e) {
      System.out.printf("[contextInitialized()] : %s\n", e.getMessage());
      e.printStackTrace();
    }
    System.out.println("--------------------------------------------------");
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) throws Exception {
    DataSource dataSource = (DataSource) context.get("dataSource");
    dataSource.clean();
    System.out.println("--------------------------------------------------");
  }
}
