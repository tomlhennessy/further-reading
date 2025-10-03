# Stacks

## Core Definition
A stack is a collection that follows **Last In, First Out** (LIFO) order.
- `push(x)` -> insert on top
- `pop()` -> remove the most recent element
- `peek()` -> look at the top without removing

## Memory Layout
**Array-based (resizing array stack)**:
- Backed by a contiguous array on the heap
- Extra overhead when resizing (same as dynamic arrays)
- Top pointer tracks the current element count

**Linked-List Based**:
- Each element is a node on the heap (value + pointer)
- No resizing, but more overhead per element (extra pointer + object header)
- Nodes may be scattered (cache unfriendly)

## CPU Costs
- Array `push/pop` = amortized O(1)
- Linked list `push/poop` = O(1) always
- Array stack is more cache friendly than linked list

## Runtime Context
- Array stack is best when you know it will grow big, and cache locality matters (hot loops)
- Linked list stack safe when number of elements fluctuates unpredictably, but rarely faster in Java

## Failure Points
- Array stack -> resizing overhead (O(n) copy)
- Linked list stack -> GC churn, cache misses, higher per-element memory cost
- Both -> `pop()` on empty stack must be handled (`NoSuchElementException`)



# Queues

## Core Definition
A queue follows **First In, First Out** (FIFO) order.
- `enqueue(x)` -> insert at the tail (end)
- `dequeue()` -> remove from the head (front)
- `peek()` -> look at the front without removing

## Memory Layout
**Linked-List Queue**:
- Head points to front, tail points to back
- Each node = value + pointer
- Allocated individually -> scattered in memory

**Array Queue (Circular Buffer)**":
- Backed by contiguous array
- Uses modular arithmetic (`(i+1) % capacity`) to wrap around
- More cache-friendly, but needs resizing if full

## CPU Costs
- Linked-list: O(1) enqueue and dequeue (with head/tail pointers)
- Array queue: O(1) amortized, but resize is O(n)
- Array queue is more cache-friendly

## Runtime Context
- Linked-list queue works well if the size fluctuates unpredictably
- Array queue better for throughput-heavy workloads (network buffers, job schedulers)

## Failure Points
- Linked-list queue -> GC churn, cache misses
- Array queue -> resizing overhead, potential wasted space if not managed
- Both -> must handle empty queue case safely


