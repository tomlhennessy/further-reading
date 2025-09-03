# Linked Lists

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
