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
import kyh.tam.domain.Member;

public class MemberObjectFileDao {
  String filename;
  List<Member> list;

  public MemberObjectFileDao(String filename) throws Exception {
    this.list = new ArrayList<>();
    this.filename = filename;
    loadData();
  }

  @SuppressWarnings("unchecked")
  private void loadData() throws Exception {
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(filename))))) {
      list = (List<Member>) in.readObject();
      System.out.printf("%d 개의 유저 데이터를 로딩했습니다.\n", list.size());
    } catch (FileNotFoundException e) {
      System.out.println("파일을 읽는 도중 문제가 발생했습니다 : " + e.getMessage());
    }
  }

  private void saveData() throws IOException {
    ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(filename))));
    out.writeObject(list);
    System.out.printf("%d 개의 유저 데이터를 저장했습니다.\n", list.size());
    out.close();
  }

  public int insert(Member member) throws Exception {
    if (indexOf(member.getNumber()) > -1)
      return 0;
    list.add(member);
    saveData();
    return 1;
  }

  public List<Member> findAll() throws Exception {
    return list;
  }

  public Member findByNumber(int number) throws Exception {
    int index = indexOf(number);
    if (index == -1)
      return null;
    return list.get(index);
  }

  public int update(Member member) throws Exception {
    int index = indexOf(member.getNumber());
    if (index == -1)
      return 0;

    list.set(index, member);
    saveData();
    return 1;
  }

  public int delete(int number) throws Exception {
    int index = indexOf(number);
    if (index == -1)
      return 0;

    list.remove(index);
    saveData();
    return 1;
  }

  private int indexOf(int number) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNumber() == number) {
        return i;
      }
    }
    return -1;
  }
}
