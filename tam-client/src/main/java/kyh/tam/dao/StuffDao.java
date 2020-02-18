package kyh.tam.dao;

import java.util.List;
import kyh.tam.domain.Stuff;

public interface StuffDao {
  public int insert(Stuff stuff) throws Exception;

  public List<Stuff> findAll() throws Exception;

  public Stuff findByNumber(int number) throws Exception;

  public int update(Stuff stuff) throws Exception;

  public int delete(int number) throws Exception;
}

