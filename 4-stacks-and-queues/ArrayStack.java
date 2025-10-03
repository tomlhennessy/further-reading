

public class ArrayStack<T> {
  private T[] data;
  private int size;

  public ArrayStack() {
    this.data = (T[]) new Object[4];
    this.size = 0;
  }

  public void push(T value) {
    if (size == data.length) {
      resize();
    }
    data[size++] = value;
  }

  public T pop() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    T value = data[--size];
    data[size] = null;    // avoid loitering
    return value;
  }

  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
      return data[size - 1];
    }
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  private void resize() {
    T[] newData = (T[]) new Object[data.length * 2];
    for (int i = 0; i < size; i++) {
      newData[i] = data[i];
    }
    data = newData;
  }
}
