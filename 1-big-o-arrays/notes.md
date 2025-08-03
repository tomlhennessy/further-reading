# Week 1: Arrays and Low-Level Java

## Dynamic Arrays & Memory
Arrays are stored **contiguously** in memory, and when they fill up, a new larger array must be allocated, and **existing elements copied** over.

> Even though `.add()` looks like O(1), resizing involves copying all elements - so the **worst case is O(n)**.

But because it resizes **rarely**, the average cost per **.add()** is still **amortized O(1)**.

Dynamic Arrays...
- Cannot grow in place - if full, we must:
  1. Allocate a **new array** (usually 2x current size)
  2. **copy** old elements over
  3. replace the reference

- `data.length` = total allocated capacity
- `size` = number of elements actually stored
> Always check `size == data.length` before adding

## Time Complexity
- `.add()` is **amortized O(1)**
- Resizing `O(n)` happens **rarely**, so average cost is low
- Resizing by doubling avoids O(n^2) behaviour you'd get from resizing by +1 each time.

## Array Access & Safety
- Use index-based loops: `for (int i = 0; i < size; i++)`
- Avoid enhanced `for-each` on internal array: it can access uninitialized slots
- Always do bounds checks in `get(int index)` to prevent `IndexOutOfBoundsException`

## OOP Design principles (Java)
- **Encapsulation**: keep `data` and `size` private
  -> prevents external code from corrupting internal state
- Make methods like `resize()` **private** - user doesn't need to know internals
- `add()` must be an **instance method**, not static - needs access to `this.data`

## Generics Intro (for later weeks)
- Java arrays hold only one type (`int[]`, `String[]`)
- To make a reusable `DynamicArray for any type, use **generics**:

```java
public class DynamicArray<T> {
  private T[] data;
  private int size;
}
```
- Now you can do: `new DynamicArray<String>()` or `new DynamicArray<Double>()`
- It's how Java lets you write type-safe, flexible code.
