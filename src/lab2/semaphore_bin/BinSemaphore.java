package lab2.semaphore_bin;

public class BinSemaphore {
    byte value = 1;

    synchronized public void p(){
        try {
            while (value == 0){
                wait();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        value = 0;
    }

    synchronized public void v() {
        value = 1;
        notifyAll();
    }
}
