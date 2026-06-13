public class CustomArrayList<T> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    
    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }
    
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }
    
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }
    
    public int size() {
        return size;
    }
    
    private void ensureCapacity() {
        if (size == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }
    
    public static void main(String[] args) {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        
        System.out.println(list.get(0));  // A
        System.out.println(list.get(1));  // B
        System.out.println(list.size());  // 3
    }
}
