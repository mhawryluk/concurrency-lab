package lab1.producerconsumer;


public class Producer implements Runnable {
    private final Buffer buffer;
    private final int amount;
    private final int index;

    public Producer(Buffer buffer, int amount, int index) {
        this.buffer = buffer;
        this.amount = amount;
        this.index = index;
    }

   public void run() {
        for (int i = 0; i < amount; i++) {
            buffer.put("message " + i + " from producer " + index);
        }
    }
}