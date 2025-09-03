

public class HashMapLite<K, V> {
  private static final int DEFAULT_CAPACITY = 16;

  private static final class Node<K, V> {
    final K key;
    V value;
    Node<K, V> next;

    Node(K key, V value, Node<K, V> next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }

  private Node<K, V>[] buckets;
  private int size;

  public HashMapLite() {
    this.buckets = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
    this.size = 0;
  }

  private int indexFor(Object key) {
    int h = key.hashCode();
    int nonNeg = h & 0x7fffffff;
    return nonNeg % buckets.length;
  }

  public V get(K key) {
    if (key == null) {
      throw new IllegalArgumentException("key == null");
    }
    int i = indexFor(key);
    for (Node<K, V> x = buckets[i]; x != null; x = x.next) {
      if (key.equals(x.key)) {
        return x.value;
      }
    }
    return null;
  }

  public V put(K key, V value) {
    if (key == null) {
      throw new IllegalArgumentException("key == null");
    }
    int i = indexFor(key);

    for (Node<K, V> x = buckets[i]; x != null; x = x.next) {
      if (key.equals(x.key)) {
        V old = x.value;
        x.value = value;
        return old;
      }
    }

    buckets[i] = new Node<>(key, value, buckets[i]); // insert at head
    size++;

    if (size >= (int) (buckets.length * 0.75)) {
      resize(buckets.length * 2);
    }
    return null;
  }

  private void resize(int newCap) {
    Node<K, V>[] old = buckets;
    Node<K, V>[] fresh = (Node<K, V>[]) new Node[newCap];
    buckets = fresh;
    int oldSize = size;
    size = 0;

    for (Node<K, V> head : old) {
      for (Node<K, V> x = head; x != null; x = x.next) {
        put(x.key, x.value); // rehash into new buckets
      }
    }
    size = oldSize; // preserve count
  }

  public V remove(K key) {
    if (key == null) {
      throw new IllegalArgumentException("key == null");
    }
    int i = indexFor(key);
    Node<K, V> prev = null;
    Node<K, V> cur = buckets[i];

    while (cur != null) {
      if (key.equals(cur.key)) {
        V old = cur.value;
        if (prev == null) {
          buckets[i] = cur.next;
        } else {
          prev.next = cur.next;
        }
        size--;
        return old;
      }
      prev = cur;
      cur = cur.next;
    }
    return null;
  }
}
