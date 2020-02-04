package kyh.tam;

import java.util.Map;
import kyh.tam.context.ApplicationContextListener;

public class GreetingListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) throws Exception {
    System.out.println("--------------------------------------------------");
    System.out.println("|              TAM이 시작되었습니다              |");
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) throws Exception {
    System.out.println("|              TAM이 종료되었습니다              |");
    System.out.println("--------------------------------------------------");
  }

}
