package lab1.producerconsumer;


public class Consumer implements Runnable {
    private final Buffer buffer;
    public final int amount;

    public Consumer(Buffer buffer, int amount) {
        this.buffer = buffer;
        this.amount = amount;
    }

   public void run() {
        for (int i = 0; i < amount; i++) {
            String message = buffer.take();
            System.out.println(message);
        }
    }
}