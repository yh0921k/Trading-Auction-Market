package kyh.tam.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Date;

public class Prompt {
  public static int getInt(BufferedReader in, BufferedWriter out, String label)
      throws NumberFormatException, IOException {
    return Integer.parseInt(getString(in, out, label));
  }

  public static int getInt(BufferedReader in, BufferedWriter out, String label, String defaultValue)
      throws NumberFormatException, IOException {
    return Integer.parseInt(getString(in, out, label, defaultValue));
  }

  public static String getString(BufferedReader in, BufferedWriter out, String label)
      throws IOException {
    out.write(label + System.lineSeparator());
    out.write("!{}!" + System.lineSeparator());
    out.flush();
    return in.readLine();
  }

  public static String getString(BufferedReader in, BufferedWriter out, String label,
      String defaultValue) throws IOException {
    out.write(label + System.lineSeparator());
    out.write("!{}!" + System.lineSeparator());
    out.flush();
    String value = in.readLine();
    if (value.length() > 0)
      return value;
    return defaultValue;
  }

  public static Date getDate(BufferedReader in, BufferedWriter out, String label)
      throws IOException {
    return Date.valueOf(getString(in, out, label));
  }

  public static Date getDate(BufferedReader in, BufferedWriter out, String label,
      String defaultValue) throws IOException {
    return Date.valueOf(getString(in, out, label, defaultValue));
  }
}
