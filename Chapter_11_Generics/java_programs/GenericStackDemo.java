interface Stack<T> {
    void push(T item);
    T pop();
    boolean isEmpty();
}

public class GenericStackDemo<T> implements Stack<T> {
    private Object[] elements = new Object[100];
    private int top = -1;
    
    @Override
    public void push(T item) {
        elements[++top] = item;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return (T) elements[top--];
    }
    
    @Override
    public boolean isEmpty() {
        return top == -1;
    }
    
    public static void main(String[] args) {
        Stack<Integer> intStack = new GenericStackDemo<>();
        intStack.push(10);
        intStack.push(20);
        System.out.println(intStack.pop());
        System.out.println(intStack.pop());
        
        Stack<String> strStack = new GenericStackDemo<>();
        strStack.push("A");
        strStack.push("B");
        System.out.println(strStack.pop());
    }
}
