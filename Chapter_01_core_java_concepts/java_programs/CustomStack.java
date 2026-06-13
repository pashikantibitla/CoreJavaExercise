public class CustomStack<T> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    
    public CustomStack() {
        elements = new Object[DEFAULT_CAPACITY];
    }
    
    public void push(T element) {
        ensureCapacity();
        elements[size++] = element;
    }
    
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        T element = (T) elements[--size];
        elements[size] = null;  // Prevent memory leak
        return element;
    }
    
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return (T) elements[size - 1];
    }
    
    public boolean isEmpty() {
        return size == 0;
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
        CustomStack<Integer> stack = new CustomStack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        
        System.out.println(stack.peek());  // 30
        System.out.println(stack.pop());   // 30
        System.out.println(stack.pop());   // 20
        System.out.println(stack.size());  // 1
    }
}
