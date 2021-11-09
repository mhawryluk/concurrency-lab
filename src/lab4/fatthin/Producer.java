package lab4.fatthin;

import java.util.Random;

public class Producer extends Thread{

    private final Buffer buffer;
    private Random rand = new Random();

    public Producer(Buffer buffer){
        this.buffer = buffer;
    }

    public void run(){
        for (int i = 0; i < 3; i++){
            buffer.remove(rand.nextInt(buffer.maxProductCount/2));
        }
    }
}
