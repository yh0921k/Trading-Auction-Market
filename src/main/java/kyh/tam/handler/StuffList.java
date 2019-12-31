package kyh.tam.handler;

import java.util.Arrays;
import kyh.tam.domain.Stuff;

public class StuffList {
  private static final int DEFAULT_CAPACITY = 100;
  private Stuff[] list;
  private int size;
  
  public StuffList() {
    this.list = new Stuff[DEFAULT_CAPACITY];
  }
  
  public StuffList(int capacity) {
    if(capacity > DEFAULT_CAPACITY && capacity < 100000)
      this.list = new Stuff[capacity];
    else
      this.list = new Stuff[DEFAULT_CAPACITY];
  }
  
  public void add(Stuff stuff) {
    if(this.size == this.list.length) {
      Stuff[] arr = new Stuff[this.list.length + (this.list.length >> 1)];
      System.arraycopy(this.list, 0, arr, 0, list.length);
      this.list = arr;
    }
    this.list[this.size++] = stuff;
  }
  
  public Stuff[] toArray() {
    return Arrays.copyOf(this.list, this.size);
  }
}
