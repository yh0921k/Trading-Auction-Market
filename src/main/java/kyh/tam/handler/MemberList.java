package kyh.tam.handler;

import java.util.Arrays;
import kyh.tam.domain.Member;

public class MemberList {
  private static final int DEFAULT_CAPACITY = 100;
  private Member[] list;
  private int size;      
  
  public MemberList() {
    this.list = new Member[DEFAULT_CAPACITY];
  }
  
  public MemberList(int capacity) {
    if(capacity > DEFAULT_CAPACITY && capacity < 100000)
      this.list = new Member[capacity];
    else
      this.list = new Member[DEFAULT_CAPACITY];
  }
  
  public void add(Member member) {
    if(this.size == this.list.length) {
      Member[] arr = new Member[this.list.length + (this.list.length >> 1)];
      System.arraycopy(this.list, 0, arr, 0, list.length);
      this.list = arr;
    }
    this.list[this.size++] = member;
  }
  
  public Member[] toArray() {
    return Arrays.copyOf(this.list, this.size);
  }

}
