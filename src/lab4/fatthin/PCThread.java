package lab4.fatthin;

import java.util.Random;

public class PCThread extends Thread{

    private long startTime;
    protected final Buffer buffer;
    protected final Random rand;

    public PCThread(Buffer buffer, Random rand) {
        this.buffer = buffer;
        this.rand = rand;
    }

    public void requestStart(){
        startTime = System.nanoTime();
    }

    public long getTime(){
        return System.nanoTime() - startTime;
    }
}
