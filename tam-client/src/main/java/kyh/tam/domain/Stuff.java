package kyh.tam.domain;

import java.io.Serializable;

public class Stuff implements Serializable {
  private static final long serialVersionUID = 20200131L;
  private int number;
  private String name;
  private String state;
  private String seller;
  private String category;
  private int price;

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getSeller() {
    return seller;
  }

  public void setSeller(String seller) {
    this.seller = seller;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((category == null) ? 0 : category.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + number;
    result = prime * result + price;
    result = prime * result + ((seller == null) ? 0 : seller.hashCode());
    result = prime * result + ((state == null) ? 0 : state.hashCode());
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
    Stuff other = (Stuff) obj;
    if (category == null) {
      if (other.category != null)
        return false;
    } else if (!category.equals(other.category))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (number != other.number)
      return false;
    if (price != other.price)
      return false;
    if (seller == null) {
      if (other.seller != null)
        return false;
    } else if (!seller.equals(other.seller))
      return false;
    if (state == null) {
      if (other.state != null)
        return false;
    } else if (!state.equals(other.state))
      return false;
    return true;
  }

  public static Stuff valueOf(String csv) {
    String[] data = csv.split(",");
    Stuff stuff = new Stuff();
    stuff.setNumber(Integer.parseInt(data[0]));
    stuff.setName(data[1]);
    stuff.setState(data[2]);
    stuff.setSeller(data[3]);
    stuff.setCategory(data[4]);
    stuff.setPrice(Integer.parseInt(data[5]));
    return stuff;
  }

  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%d", this.getNumber(), this.getName(), this.getState(),
        this.getSeller(), this.getCategory(), this.getPrice());
  }

  @Override
  public String toString() {
    return "[" + number + ", " + name + ", " + state + ", " + seller + ", " + price + "]";
  }
}
