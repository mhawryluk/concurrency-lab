package lab2.semaphore_gen;

public class Semaphore {
    private int value;

    public int getValue(){
        return value;
    }

    public Semaphore(int maxValue) {
        value = maxValue;
    }

    synchronized public void p(){
        try {
            while (value == 0){
                wait();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        value -= 1;
    }

    synchronized public void v() {
        value += 1;
        notifyAll();
    }
}
