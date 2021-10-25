package lab1.producerconsumer;

public class Buffer {

    private String message = null;

    public boolean isEmpty() {
        return message == null;
    }

    synchronized public void put(String message) {
        try {
            while (!isEmpty()) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.message = message;
        notifyAll();
    }

    synchronized public String take() {
        try {
            while (isEmpty()) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String message = this.message;
        this.message = null;
        notifyAll();
        return message;
    }
}