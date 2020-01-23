package kyh.tam.domain;

import java.sql.Date;

public class Board {
  private int number;
  private String title;
  private Date writeDate;
  private int viewCount;
  private String writer;

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String contents) {
    this.title = contents;
  }

  public Date getWriteDate() {
    return writeDate;
  }

  public void setWriteDate(Date writeDate) {
    this.writeDate = writeDate;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + number;
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + viewCount;
    result = prime * result + ((writeDate == null) ? 0 : writeDate.hashCode());
    result = prime * result + ((writer == null) ? 0 : writer.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Board other = (Board) obj;
    if (number != other.number)
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (viewCount != other.viewCount)
      return false;
    if (writeDate == null) {
      if (other.writeDate != null)
        return false;
    } else if (!writeDate.equals(other.writeDate))
      return false;
    if (writer == null) {
      if (other.writer != null)
        return false;
    } else if (!writer.equals(other.writer))
      return false;
    return true;
  }

  public static Board valueOf(String csv) {
    String[] data = csv.split(",");
    Board board = new Board();
    board.setNumber(Integer.parseInt(data[0]));
    board.setTitle(data[1]);
    board.setWriteDate(Date.valueOf(data[2]));
    board.setViewCount(Integer.parseInt(data[3]));
    board.setWriter(data[4]);

    return board;
  }

  public String toCsvString() {
    return String.format("%d,%s,%s,%d,%s", this.getNumber(), this.getTitle(), this.getWriteDate(),
        this.getViewCount(), this.getWriter());
  }
}
