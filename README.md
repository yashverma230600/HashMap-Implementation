## Custom HashMap Implementation in Java

A generic HashMap implementation from scratch using separate chaining for collision resolution.

## Features##

- **Generic Implementation**: Supports any key-value types `<K,V>`
- **Dynamic Sizing**: Automatically calculates optimal table size (nearest power of 2)
- **Collision Handling**: Uses linked list chaining for hash collisions
- **Custom Hash Function**: Uses Java's built-in `hashCode()` with modulo operation

## Implementation Details

### Data Structure
- **Array of Linked Lists**: Each index in the hash table can store multiple entries
- **Entry Class**: Inner class storing key, value, and reference to next entry

### Key Methods

#### `put(K key, V value)`
Inserts or updates a key-value pair in the HashMap.

**Algorithm:**
1. Calculate hash index: `key.hashCode() % hashTable.length`
2. If slot is empty → Insert new entry directly
3. If slot is occupied (collision):
   - Traverse the linked list
   - If key exists → Update value
   - If key doesn't exist → Append to end of list

**Time Complexity:** O(1) average, O(n) worst case (if all keys collide)

#### `get(K key)`
Retrieves the value associated with a key.

**Algorithm:**
1. Calculate hash index
2. Traverse linked list at that index
3. Return value if key matches, null otherwise

**Time Complexity:** O(1) average, O(n) worst case

### Constructors

```java
// Default: Creates hash table of size 16
MyHashMap<Integer, String> map = new MyHashMap<>();

// Custom capacity: Rounds up to nearest power of 2
MyHashMap<Integer, String> map = new MyHashMap<>(50);  // Creates size 64
```

### Table Sizing Algorithm

The `tableSizeFor(int capacity)` method calculates the nearest power of 2 greater than or equal to the given capacity using bit manipulation:

```java
n |= n >>> 1;  // Propagate highest bit right
n |= n >>> 2;
n |= n >>> 4;
n |= n >>> 8;
n |= n >>> 16;
return n + 1;   // Result is next power of 2
```

**Example:** Input `50` → Output `64` (2⁶)

## Usage Example

```java
MyHashMap<Integer, String> map = new MyHashMap<>(7);

// Insert key-value pairs
map.put(1, "One");
map.put(2, "Two");
map.put(3, "Three");

// Retrieve values
String value = map.get(2);  // Returns "Two"

// Update existing key
map.put(2, "TWO");  // Updates value for key 2
```

## Collision Resolution

When multiple keys hash to the same index (collision), entries are chained using a linked list:

```
Index 5: [Entry(5,"Five")] → [Entry(13,"Thirteen")] → null
         (5 % 8 = 5)          (13 % 8 = 5)
```

## Limitations

- No automatic resizing/rehashing when load factor exceeds threshold
- No support for `null` keys
- Basic implementation without advanced optimizations (tree conversion, etc.)

## Time Complexity Summary

| Operation | Average | Worst Case |
|-----------|---------|------------|
| put()     | O(1)    | O(n)       |
| get()     | O(1)    | O(n)       |
| Space     | O(n)    | O(n)       |

## Improvements Possible

- [ ] Implement dynamic resizing (rehashing)
- [ ] Add `remove()` method
- [ ] Add `containsKey()` and `size()` methods
- [ ] Convert long chains to balanced trees (like Java 8 HashMap)
- [ ] Support null keys

---

**Author**: Custom implementation for learning purposes  
**Java Version**: 8+
