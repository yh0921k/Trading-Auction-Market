package kyh.util;

import java.io.BufferedReader;
import java.sql.Date;

public class Prompt {
  
  BufferedReader input;
  
  public Prompt(BufferedReader input) {
    this.input = input;
  }
  
  public String inputString(String label) throws Exception {
    System.out.print(label);
    return input.readLine();
  }
  
  public String inputString(String label, String defaultValue) throws Exception {
    System.out.print(label);
    String value = input.readLine();
    if (value.length() == 0)
      return defaultValue;
    return value;
  }
  
  public int inputInt(String label) throws Exception {
    System.out.print(label);
    return Integer.parseInt(input.readLine());
  }
  
  public int inputInt(String label, int defaultValue) throws Exception {
    System.out.print(label);
    String value = input.readLine();
    if (value.length() == 0)
      return defaultValue;
    return Integer.parseInt(value);
  }
  
//  public Date inputDate(String label) throws Exception {
//    System.out.print(label);
//    return Date.valueOf(input.readLine());
//  }
//  
//  public Date inputDate(String label, Date defaultValue) throws Exception {
//    System.out.print(label);
//    String value = input.readLine();
//    if (value.length() == 0) {
//      return defaultValue;
//    }
//    return Date.valueOf(value);
//  }
}
