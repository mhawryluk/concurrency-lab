package lab4.fatthin;

import java.util.Random;

public class PCThread extends Thread{

    protected final Buffer buffer;
    static protected final Random rand = new Random();
    private long startTime;

    public PCThread(Buffer buffer) {
        this.buffer = buffer;
    }

    public void requestStart(){
        startTime = System.nanoTime();
    }

    public long getTime(){
        return System.nanoTime() - startTime;
    }
}
