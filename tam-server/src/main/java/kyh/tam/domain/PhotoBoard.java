package kyh.tam.domain;

import java.io.Serializable;
import java.sql.Date;

public class PhotoBoard implements Serializable {
  private static final long serialVersionUID = 1L;

  int number;
  String title;
  int viewCount;
  Date registeredDate;
  Stuff stuff;

  @Override
  public String toString() {
    return "PhotoBoard [number=" + number + ", title=" + title + ", registeredDate="
        + registeredDate + ", viewCount=" + viewCount + ", stuff=" + stuff + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + number;
    result = prime * result + ((registeredDate == null) ? 0 : registeredDate.hashCode());
    result = prime * result + ((stuff == null) ? 0 : stuff.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + viewCount;
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
    PhotoBoard other = (PhotoBoard) obj;
    if (number != other.number)
      return false;
    if (registeredDate == null) {
      if (other.registeredDate != null)
        return false;
    } else if (!registeredDate.equals(other.registeredDate))
      return false;
    if (stuff == null) {
      if (other.stuff != null)
        return false;
    } else if (!stuff.equals(other.stuff))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (viewCount != other.viewCount)
      return false;
    return true;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  public Stuff getStuff() {
    return stuff;
  }

  public void setStuff(Stuff stuff) {
    this.stuff = stuff;
  }
}
