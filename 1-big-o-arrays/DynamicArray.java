
public class DynamicArray {
  private int[] data; // declares a new local variable
  private int size; // same here

  public DynamicArray() {
    this.data = new int[4];
    this.size = 0;
  }

  public void add(int value) {
    if (size == data.length) {
      resize();
    }

    data[size] = value; // puts new value into array at next available spot
    size++; // size is a pointer to the next empty slot
  }

  private void resize() {
    int[] newData = new int[data.length * 2];

    for (int i = 0; i < size; i++) {
      newData[i] = data[i];
    }

    this.data = newData;
  }

  public int get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    return data[index];
  }

    public static void main(String[] args) {
    DynamicArray arr = new DynamicArray();

    for (int i = 0; i < 10; i++) {
      arr.add(i);
    }

    System.out.println(arr);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < size; i++) {
      sb.append(data[i]);
      if (i < size - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }

}
