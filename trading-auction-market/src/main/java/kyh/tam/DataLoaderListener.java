package kyh.tam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import kyh.tam.context.ApplicationContextListener;
import kyh.tam.domain.Board;
import kyh.tam.domain.Member;
import kyh.tam.domain.Stuff;

public class DataLoaderListener implements ApplicationContextListener {

  List<Board> boardList = new LinkedList<>();
  List<Stuff> stuffList = new LinkedList<>();
  List<Member> memberList = new LinkedList<>();

  @Override
  public void contextInitialized(Map<String, Object> context) throws Exception {
    System.out.println("--------------------------------------------------");
    loadStuffData();
    loadMemberData();
    loadBoardData();

    context.put("boardList", boardList);
    context.put("stuffList", stuffList);
    context.put("memberList", memberList);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) throws Exception {
    saveStuffData();
    saveMemberData();
    saveBoardData();
    System.out.println("--------------------------------------------------");
  }

  @SuppressWarnings("unchecked")
  private void loadStuffData() throws Exception {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(new File("./data/stuff.ser2"))))) {
      stuffList = (List<Stuff>) in.readObject();
      System.out.printf("%d 개의 물품 데이터를 로딩했습니다.\n", stuffList.size());
    } catch (FileNotFoundException e) {
      System.out.println("파일을 읽는 도중 문제가 발생했습니다 : " + e.getMessage());
    }
  }

  private void saveStuffData() throws IOException {
    ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("./data/stuff.ser2")));
    out.writeObject(stuffList);
    System.out.printf("%d 개의 물품 데이터를 저장했습니다.\n", stuffList.size());
    out.close();
  }

  @SuppressWarnings("unchecked")
  private void loadMemberData() throws Exception {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(new File("./data/member.ser2"))))) {
      memberList = (List<Member>) in.readObject();
      System.out.printf("%d 개의 유저 데이터를 로딩했습니다.\n", memberList.size());
    } catch (FileNotFoundException e) {
      System.out.println("파일을 읽는 도중 문제가 발생했습니다 : " + e.getMessage());
    }
  }

  private void saveMemberData() throws IOException {
    ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream("./data/member.ser2")));
    out.writeObject(memberList);
    System.out.printf("%d 개의 유저 데이터를 저장했습니다.\n", memberList.size());
    out.close();
  }

  @SuppressWarnings("unchecked")
  private void loadBoardData() throws Exception {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(new File("./data/board.ser2"))))) {
      boardList = (List<Board>) in.readObject();
      System.out.printf("%d 개의 게시글 데이터를 로딩했습니다.\n", boardList.size());
    } catch (FileNotFoundException e) {
      System.out.println("파일을 읽는 도중 문제가 발생했습니다 : " + e.getMessage());
    }
  }

  private void saveBoardData() throws IOException {
    ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("./data/board.ser2")));
    out.writeObject(boardList);
    System.out.printf("%d 개의 게시글 데이터를 저장했습니다.\n", boardList.size());
    out.close();
  }

}
