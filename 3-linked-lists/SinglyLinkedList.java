
public class SinglyLinkedList {
  private static class Node {
    int value;
    Node next;

    Node(int value) {
      this.value = value;
      this.next = null;
    }
  }

  private Node head;
  private Node tail;
  private int size;

  public SinglyLinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  public void append(int value) {
    Node newNode = new Node(value);

    if (this.head == null) {
      this.head = newNode;
      this.tail = newNode;
    } else {
      tail.next = newNode;
      tail = newNode;
    }
    size++;
  }

  public void prepend(int value) {
    Node newNode = new Node(value);

    if (this.head == null) {
      this.head = newNode;
      this.tail = newNode;
    } else {
      newNode.next = this.head;
      this.head = newNode;
    }
    size++;
  }

  public Node find(int value) {
    for (Node current = this.head; current != null; current = current.next) {
      if (current.value == value) {
        return current;
      }
    }

    return null;
  }

  public boolean delete(int value) {
    if (this.head == null) {
      return false;
    }

    // case 1: deleting head
    if (head.value == value) {
      head = head.next;
      if (head == null) {
        tail = null;    // list became empty
      }
      size--;
      return true;
    }

    // case 2: general case
    Node prev = head;
    Node current = head.next;

    while (current != null) {
      if (current.value == value) {
        prev.next = current.next;
        if (current == tail) {
          tail = prev;    // update tail if we deleted last node
        }
        size--;
        return true;
      }
      prev = current;
      current = current.next;
    }

    return false;
  }

  public void reverse() {
    Node prev = null;
    Node curr = head;
    Node next = null;

    while (curr != null) {
      next = curr.next;   // save next
      curr.next = prev;   // flip pointer
      prev = curr;        // move prev
      curr = next;        // move curr
    }

    // swap head and tail
    tail = head;
    head = prev;
  }
}
