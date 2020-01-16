package kyh.util;

public abstract class AbstractList<E> implements List<E> {
  protected int size;

  public int size() {
    return size;
  }
  
  public Iterator<E> iterator() {
    return new ListIterator<E>(this);
  }
  
  static class ListIterator<E> implements Iterator<E> {
    List<E> list;
    int cursor;
    
    public ListIterator(List<E> list) {
      this.list = list;
    }
    
    @Override
    public boolean hasNext() {
      return cursor < list.size();
    }
    
    @Override
    public E next() {
      return list.get(cursor++);
    }
  }
}
