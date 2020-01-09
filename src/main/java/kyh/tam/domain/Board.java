package kyh.tam.domain;

import java.util.Date;

public class Board {
  private int    number;
  private String title;
  private Date   writeDate;
  private int    viewCount;
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
}