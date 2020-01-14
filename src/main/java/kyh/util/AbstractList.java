package kyh.util;

public abstract class AbstractList<E> {
  protected int size;

  public int size() {
    return size;
  }
  
  public abstract void add(E e);
  public abstract void add(int index, E e);
  public abstract E get(int index);
  public abstract E set(int index, E e);
  public abstract E remove(int index);
  public abstract Object[] toArray();
  public abstract E[] toArray(E[] arr);
}
