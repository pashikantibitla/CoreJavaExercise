public class ProducerConsumer {
    private int data;
    private boolean produced = false;
    
    public synchronized void produce(int value) throws InterruptedException {
        while (produced) {
            wait();
        }
        data = value;
        produced = true;
        System.out.println("Produced: " + value);
        notify();
    }
    
    public synchronized int consume() throws InterruptedException {
        while (!produced) {
            wait();
        }
        produced = false;
        System.out.println("Consumed: " + data);
        notify();
        return data;
    }
    
    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    pc.produce(i);
                    Thread.sleep(100);
                } catch (InterruptedException e) { }
            }
        });
        
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    pc.consume();
                    Thread.sleep(100);
                } catch (InterruptedException e) { }
            }
        });
        
        producer.start();
        consumer.start();
    }
}
