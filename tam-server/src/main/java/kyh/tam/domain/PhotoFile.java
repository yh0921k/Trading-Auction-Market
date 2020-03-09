package kyh.tam.domain;

import java.io.Serializable;

public class PhotoFile implements Serializable {

  private static final long serialVersionUID = 1L;

  int number;
  String filepath;
  int boardNumber;

  public PhotoFile() {

  }

  public PhotoFile(String filepath, int boardNumber) {
    this.filepath = filepath;
    this.boardNumber = boardNumber;
  }

  public PhotoFile(int number, String filepath, int boardNumber) {
    this(filepath, boardNumber);
    this.number = number;
  }

  public int getNumber() {
    return number;
  }

  public PhotoFile setNumber(int number) {
    this.number = number;
    return this;
  }

  public String getFilepath() {
    return filepath;
  }

  public PhotoFile setFilepath(String filepath) {
    this.filepath = filepath;
    return this;
  }

  public int getBoardNumber() {
    return boardNumber;
  }

  public PhotoFile setBoardNumber(int stuffNumber) {
    this.boardNumber = stuffNumber;
    return this;
  }

  @Override
  public String toString() {
    return "PhotoFile [number=" + number + ", filepath=" + filepath + ", boardNumber=" + boardNumber
        + "]";
  }
}
