package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface Servlet {
  default void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {}

  default void service(BufferedReader in, BufferedWriter out) throws Exception {}
}
