package kyh.tam.dao.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public abstract class AbstractJsonFileDao<T> {

  protected String filename;
  protected List<T> list;

  public AbstractJsonFileDao(String filename) throws Exception {
    this.filename = filename;
    this.list = new ArrayList<>();
    loadData();
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  protected void loadData() throws Exception {
    try (BufferedReader in = new BufferedReader(new FileReader(new File(filename)))) {

      Class<?> currType = this.getClass();
      Type parentType = currType.getGenericSuperclass();
      ParameterizedType parentType2 = (ParameterizedType) parentType;
      Type[] params = parentType2.getActualTypeArguments();
      Type itemType = params[0];
      T[] arr = (T[]) Array.newInstance((Class) itemType, 0);
      T[] dataArr = (T[]) new Gson().fromJson(in, arr.getClass());
      for (T b : dataArr) {
        list.add(b);
      }

      System.out.printf("%d 개의 게시글 데이터를 로딩했습니다.\n", list.size());
    } catch (FileNotFoundException e) {
      System.out.println("파일을 읽는 도중 문제가 발생했습니다 : " + e.getMessage());
    }
  }

  protected void saveData() throws IOException {
    BufferedWriter out = new BufferedWriter(new FileWriter(new File(filename)));
    out.write(new Gson().toJson(list));
    System.out.printf("%d 개의 게시글 데이터를 저장했습니다.\n", list.size());
    out.close();
  }

  protected abstract <K> int indexOf(K number);
}
