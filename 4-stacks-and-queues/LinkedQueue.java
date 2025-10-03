

public class LinkedQueue {
  private static class Node<T> {
    T value;
    Node<T> next;

    Node(T value) {
      this.value = value;
    }
  }

  private Node<T> head;
  private Node<T> tail;
  private int size;

  public LinkedQueue() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  public void enqueue(T value) {
    Node<T> newNode = new Node<>(value);
    if (tail != null) {
      tail.next = newNode;
    }
    tail = newNode;
    if (head == null) {
      head = tail;
    }
    size++;
  }

  public T dequeue() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    T value = head.value;
    head = head.next;
    if (head == null) {
      tail == null;       // queue is now empty
    }
    size--;
    return value;
  }

  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    return head.value;
  }

  public boolean is empty() {
    return size == 0;
  }

  public int size() {
    return size;
  }
}
