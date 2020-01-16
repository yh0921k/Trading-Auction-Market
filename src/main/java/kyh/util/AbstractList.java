package kyh.util;

public abstract class AbstractList<E> implements List<E> {
  protected int size;

  public int size() {
    return size;
  }
  
  public Iterator<E> iterator() {
    return new ListIterator<E>();
  }
  
  class ListIterator<T> implements Iterator<T> {
    List<T> list;
    int cursor;
    
    @SuppressWarnings("unchecked")
    public ListIterator() {
      this.list = (List<T>)AbstractList.this;
    }
    
    @Override
    public boolean hasNext() {
      return cursor < list.size();
    }
    
    @Override
    public T next() {
      return list.get(cursor++);
    }
  }
}
