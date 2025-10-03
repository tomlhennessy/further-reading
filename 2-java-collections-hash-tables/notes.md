# Week 2: Hash Tables

1: Good. Hash must be deterministic and spread keys uniformly. Also mention: it should be fast (avoid slow math).

2: Load factor definition → correct. But small refinement: it’s the ratio N/M (entries ÷ buckets). At high load factors:

Separate chaining → lists grow longer (slower lookups).

Linear probing → clustering and long probe chains.

3: Perfect breakdown of memory and cache. Just remember the exact terms:

Separate chaining = extra pointer overhead, scattered.

Linear probing = contiguous, cache friendly.

4: Correct. Failure points are spot-on.

👉 Small fix: clustering doesn’t just “put stress on CPU,” it leads to O(n) probe sequences if not resized in time.

- Purpose: Provide O(1) average time for search/insert/delete by trading extra space

## Hash Functions:
- Transform a key into an array index
- Must be deterministic, fast, and distribute keys uniformly
- Common practice in Java: `(key.hashCode() & 0x7fffffff) % M`
- `M` is often chosen as a prime to reduce clustering

## Collisions are inevitable -> two main strategies:
1. Seperate chaining
  - Each array slot holds a linked list (or similar structure)
  - Time O(N/M) where N = #keys and M = #buckets
  - Load factor α = N/M, can be ≥ 1

2. Linear probing (open adressing)
  - On collision, move forward until an empty spot is found
  - Load factor α = N/M must stay < 1
  - Average probes = ½(1 + 1/(1-α)) for hits
  - Suffer from clustering

## Java Specifics
- `hashCode()` must agree with `equals()`: if `a.equals(b)`, then `a.hashCode() == b.hashCode()
- Example `String.hashCode` uses polynomial rolling with 31.
- Immutable keys are safer (mutating keys can break the table)


# Study Framework

## Core Definition
A **hash table** stores key-value pairs in an array, using a hash function to map each key to an index.
Average case **O(1)** for insert, lookup, delete.

## Memory Model
• Seperate chaining:
  - Array of buckets on the heap
  - Each bucket holds a linked list (nodes scattered in memory, not cache friendly)
  - Extra pointer overhead per node

• Linear probing:
  - Single array of key-value slots, contiguous in memory
  - Cache friendly, no extra pointers
  - But requires empty space -> load factor < 1

## CPU & Memory Costs
• Seperate chaining:
  - Hash function = a few cpu ops (multiply/add/mod)
  - Lookup: 1 array access + O(list length) pointer derefs (cache misses likely)
  - Memory overhead: per-node object headers + next-pointer

• Linear probing:
  - Lookup: potentially multiple probes, but each probe is a single array access (CPU cache friendly)
  - More sensitive to high load factor (clustering)

## Runtime Context
- **One-off lookup** -> either strategy works
- **Hot loops (millions of lookups/sec)** -> linear probing usually wins (better locality)
- **Background services with large sparce data** -> seperate chaining can be safer (predictable even at high α)

## Failure Points
• Seperate chaining:
  - Memory overhead grows with many collisions
  - Bad hash function -> long linked lists (worst case O(n))
  - GC pressure from many tiny nodes

• Linear probing:
  - Clustering -> probe sequences get long
  - High load factor (> 0.7) -> performance collapse
  - Need rehashing earlier

## Takeaway
- Always tie back to **trade-offs**: chaining = flexible but pointer-heavy, probing = cache friendly but fragile at high load
- Show you understand how the data actually sits in memory and how the CPU touches it

## One minute Explanation
“A hash table stores key–value pairs in an array, but instead of searching linearly, it uses a hash function to map each key directly to an index. That gives average O(1) lookup, insert, and delete.

Collisions happen when two keys hash to the same index. The two common strategies are: separate chaining, where each slot holds a linked list of entries, and linear probing, where you scan forward to find the next free slot.

The trade-off is that chaining tolerates higher load but has pointer overhead and cache misses, while linear probing is cache-friendly but suffers from clustering when the table gets too full. In practice, we resize when the load factor grows too high to keep performance near constant.”

Think of a mailbox outside an apartment building.
