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
import kyh.tam.util.ConnectionFactory;

public class DataLoaderListener implements ApplicationContextListener {

  public static Connection con;

  @Override
  public void contextInitialized(Map<String, Object> context) throws Exception {
    System.out.println("--------------------------------------------------");

    try {
      String jdbcUrl = "jdbc:mariadb://localhost:3306/tamdb";
      String username = "kyh";
      String password = "1111";

      ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, username, password);
      context.put("connectionFactory", connectionFactory);

      PlatformTransactionManager txManager = new PlatformTransactionManager(connectionFactory);
      context.put("txManager", txManager);

      context.put("boardDao", new BoardDaoImpl(connectionFactory));
      context.put("stuffDao", new StuffDaoImpl(connectionFactory));
      context.put("memberDao", new MemberDaoImpl(connectionFactory));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(connectionFactory));
      context.put("photoFileDao", new PhotoFileDaoImpl(connectionFactory));

    } catch (Exception e) {
      System.out.printf("[contextInitialized()] : %s\n", e.getMessage());
      e.printStackTrace();
    }

    System.out.println("--------------------------------------------------");
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) throws Exception {
    try {
      if (con != null)
        con.close();
    } catch (Exception e) {
      System.out.printf("[contextDestroyed()] : %s\n", e.getMessage());
      e.printStackTrace();
    }
    System.out.println("--------------------------------------------------");
  }
}
