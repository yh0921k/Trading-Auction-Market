package kyh.tam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import kyh.tam.context.ApplicationContextListener;
import kyh.tam.dao.mariadb.BoardDaoImpl;
import kyh.tam.dao.mariadb.MemberDaoImpl;
import kyh.tam.dao.mariadb.PhotoBoardDaoImpl;
import kyh.tam.dao.mariadb.PhotoFileDaoImpl;
import kyh.tam.dao.mariadb.StuffDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {

  public static Connection con;

  @Override
  public void contextInitialized(Map<String, Object> context) throws Exception {
    System.out.println("--------------------------------------------------");

    try {
      Class.forName("org.mariadb.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/tamdb", "kyh", "1111");

      context.put("boardDao", new BoardDaoImpl(con));
      context.put("stuffDao", new StuffDaoImpl(con));
      context.put("memberDao", new MemberDaoImpl(con));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(con));
      context.put("photoFileDao", new PhotoFileDaoImpl(con));

    } catch (Exception e) {
      System.out.printf("[contextInitialized()] : %s\n", e.getMessage());
      e.printStackTrace();
    }

    System.out.println("--------------------------------------------------");
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) throws Exception {
    try {
      con.close();
    } catch (Exception e) {
      System.out.printf("[contextDestroyed()] : %s\n", e.getMessage());
      e.printStackTrace();
    }
    System.out.println("--------------------------------------------------");
  }
}
