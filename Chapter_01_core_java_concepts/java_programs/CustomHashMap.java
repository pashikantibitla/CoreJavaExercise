public class CustomHashMap<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private Entry<K, V>[] buckets;
    private int capacity = 16;
    private int size = 0;
    
    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        buckets = new Entry[capacity];
    }
    
    private int getBucketIndex(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }
    
    public void put(K key, V value) {
        int index = getBucketIndex(key);
        Entry<K, V> head = buckets[index];
        
        // Check if key exists
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        
        // Insert at head
        size++;
        head = buckets[index];
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = head;
        buckets[index] = newEntry;
    }
    
    public V get(K key) {
        int index = getBucketIndex(key);
        Entry<K, V> head = buckets[index];
        
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }
    
    public int size() {
        return size;
    }
    
    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        
        System.out.println(map.get("two"));   // 2
        System.out.println(map.get("four"));  // null
        System.out.println(map.size());       // 3
    }
}
