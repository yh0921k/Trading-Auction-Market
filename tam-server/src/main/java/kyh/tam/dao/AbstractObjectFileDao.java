package kyh.tam.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractObjectFileDao<T> {

  protected String filename;
  protected List<T> list;

  public AbstractObjectFileDao(String filename) throws Exception {
    this.filename = filename;
    this.list = new ArrayList<>();
    loadData();
  }

  @SuppressWarnings({"unchecked"})
  protected void loadData() throws Exception {
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(filename))))) {
      list = (List<T>) in.readObject();
      System.out.printf("%d 개의 게시글 데이터를 로딩했습니다.\n", list.size());
    } catch (FileNotFoundException e) {
      System.out.println("파일을 읽는 도중 문제가 발생했습니다 : " + e.getMessage());
    }
  }

  protected void saveData() throws IOException {
    ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(filename))));
    out.reset();
    out.writeObject(list);
    System.out.printf("%d 개의 게시글 데이터를 저장했습니다.\n", list.size());
    out.close();
  }

  protected abstract <K> int indexOf(K number);
}
