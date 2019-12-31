package kyh.tam.domain;

import java.util.Date;

public class Board {
  private int postNum;
  private String detail;
  private Date writeDate;
  private int viewCount;
  
  public int getPostNum() {
    return postNum;
  }
  public void setPostNum(int postNum) {
    this.postNum = postNum;
  }
  public String getDetail() {
    return detail;
  }
  public void setDetail(String detail) {
    this.detail = detail;
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
}