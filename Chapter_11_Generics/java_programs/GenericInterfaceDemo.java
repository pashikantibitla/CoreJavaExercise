interface Container<T> {
    void add(T item);
    T get(int index);
    int size();
}

public class GenericInterfaceDemo implements Container<String> {
    private String[] items = new String[10];
    private int count = 0;
    
    @Override
    public void add(String item) {
        items[count++] = item;
    }
    
    @Override
    public String get(int index) {
        return items[index];
    }
    
    @Override
    public int size() {
        return count;
    }
    
    public static void main(String[] args) {
        GenericInterfaceDemo container = new GenericInterfaceDemo();
        container.add("Hello");
        container.add("World");
        System.out.println(container.get(0));
        System.out.println("Size: " + container.size());
    }
}
