# Linked Lists

1: Correct. Each node = value + pointer.

2: Here’s where we need to adjust:

Insert at head = O(1) ✅

Access by index = ❌ O(n) (not O(1)) → because you must traverse from head each time.

Delete (given pointer to node) = O(1) ✅ But if you’re deleting by value (like your implementation), first you need a traversal = O(n).

3: Correct → poor locality = cache misses.

4: Correct → in practice, ArrayList is almost always faster.

👉 So the big correction: random access in linked lists is O(n), not O(1).

## Memory Layout
- Nodes are allocated individually on the heap
- Each node stores:
  • the value
  • a reference (pointer) to the next node
- Overhead: an extra pointer per node -> less cache-friendly than arrays (nodes may be scattered in memory)

## CPU cost
- Access by index = **O(n)** (must traverse)
- Insert/delete at head or tail (with pointer) = **O(1)**
- Insert/delete in middle = **O(1)** once you find the node, but finding is **O(n)**
- Likely to cause cache misses (poor locality) compared to arrays

## Frequency of Execution
- If appending/removing from head or tail is frequent, linked lists are fine
- If random access (get by index) is frequent, arrays are much better

## Side Effects
- Every `new Node()` allocates heap memory -> GC has to clean up eventually
- Removing nodes frees them when no references remain
- Long-lived linked structures can pressure GC if not cleared properly

## Takeaway
- In practice, `ArrayList` is almost always faster in Java; `LinkedList` is niche
