public class CustomQueue<T> {
    private Object[] elements;
    private int front = 0;
    private int rear = 0;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    
    public CustomQueue() {
        elements = new Object[DEFAULT_CAPACITY];
    }
    
    public void enqueue(T element) {
        ensureCapacity();
        elements[rear] = element;
        rear = (rear + 1) % elements.length;
        size++;
    }
    
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        T element = (T) elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;
        return element;
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
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[(front + i) % elements.length];
            }
            front = 0;
            rear = size;
            elements = newElements;
        }
    }
    
    public static void main(String[] args) {
        CustomQueue<String> queue = new CustomQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        
        System.out.println(queue.dequeue());  // A
        System.out.println(queue.dequeue());  // B
        System.out.println(queue.size());     // 1
    }
}
