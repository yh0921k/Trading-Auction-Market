package kyh.tam.dao;

import java.util.List;
import kyh.tam.domain.Stuff;

public interface StuffDao {
  public int insert(Stuff board) throws Exception;

  public List<Stuff> findAll() throws Exception;

  public Stuff findByNumber(int no) throws Exception;

  public int update(Stuff board) throws Exception;

  public int delete(int no) throws Exception;
}

