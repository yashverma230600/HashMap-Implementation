package Github.HashMapimpl;

public class MyHashMap<K,V> {

    private static final int INITIAL_SIZE = 1<<4; // 16
    private static final int MAXIMUM_CAPACOTY = 1<<30;

    public Entry[] hashTable;

    // 2 Constructors of MyHashMap
    // 1st -> When no input is given in the object creation like 
    // MyHashMap<Integer, String> map = new MyHashMap<>(); then this constructor will be called
    // and it will create a hashTable of INITIAL_SIZE i.e. 16
    @SuppressWarnings("unchecked")
    public MyHashMap(){
        hashTable = (Entry[]) new MyHashMap.Entry[INITIAL_SIZE];
    }
    // If user provides initial capacity like
    // MyHashMap<Integer, String> map = new MyHashMap<>(50); then this constructor will be called
    // and it will create a hashTable of size nearest power of 2 greater than or equal to 50 i.e. 64
    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity){
        int tableSize = tableSizeFor(capacity);
        hashTable = (Entry[]) new MyHashMap.Entry[tableSize];

    }
    // Function to calculate nearest power of 2 greater than or equal to given capacity
    final int tableSizeFor(int capacity){
        int n = capacity - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACOTY) ? MAXIMUM_CAPACOTY : n + 1;
    }

    class Entry{
        public K key;
        public V value;
        public Entry next;

        Entry(K k, V v){
            key=k;
            value=v;
        }
    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>(7);
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");     
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "?");

        String value = map.get(8);
        System.out.println("Value : " + value); // Output: Value
    }
    private V get(K key) {
        int hashCode = key.hashCode()%hashTable.length;
        Entry node = hashTable[hashCode];

        while(node!=null){
            if(node.key==key){
                return (V)node.value;
            }
            node = node.next;
        }
        return null;
        
    }
    private void put(K key, V value) {
        // hashCode because we have to map the key to the index of hashTable
        int hashCode = key.hashCode()%hashTable.length;
        Entry node = hashTable[hashCode];

        if(node==null){
            Entry newNode = new Entry(key, value);
            hashTable[hashCode] = newNode;
        }else{
            Entry prev = node;
            while(node!=null){
                if(node.key==key){
                    node.value = value;
                    return;
                }
                prev = node;
                node = node.next;
            }
            Entry newNode = new Entry(key,value);
            prev.next=newNode;


        }
    }
    
}
