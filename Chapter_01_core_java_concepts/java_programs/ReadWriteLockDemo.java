import java.util.concurrent.locks.*;

public class ReadWriteLockDemo {
    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static final Lock readLock = rwLock.readLock();
    private static final Lock writeLock = rwLock.writeLock();
    private static int data = 0;
    
    public static void read() {
        readLock.lock();
        try {
            System.out.println("Read: " + data + 
                " by " + Thread.currentThread().getName());
        } finally {
            readLock.unlock();
        }
    }
    
    public static void write(int value) {
        writeLock.lock();
        try {
            data = value;
            System.out.println("Write: " + data + 
                " by " + Thread.currentThread().getName());
        } finally {
            writeLock.unlock();
        }
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> read()).start();
        }
        new Thread(() -> write(100)).start();
    }
}
