package kyh.tam.context;

import java.util.Map;

public interface ApplicationContextListener {
  void contextInitialized(Map<String, Object> context) throws Exception;

  void contextDestroyed(Map<String, Object> context) throws Exception;
}
