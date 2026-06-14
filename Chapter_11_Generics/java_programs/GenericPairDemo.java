public class GenericPairDemo<K, V> {
    private K key;
    private V value;
    
    public GenericPair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() { return key; }
    public V getValue() { return value; }
    
    public static void main(String[] args) {
        GenericPair<String, Integer> person = new GenericPair<>("Age", 25);
        System.out.println(person.getKey() + ": " + person.getValue());
        
        GenericPair<Integer, String> code = new GenericPair<>(404, "Not Found");
        System.out.println(code.getKey() + " — " + code.getValue());
    }
}
