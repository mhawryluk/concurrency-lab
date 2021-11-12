package lab4.fatthin;

import java.util.Random;

public class Producer extends PCThread{

    public Producer(Buffer buffer){
        super(buffer, new Random());
    }

    public void run(){
        try {
            for (;;) {
                requestStart();
                int count = rand.nextInt(buffer.maxProductCount / 2) + 1;
                buffer.put(count);
                buffer.saveTime(buffer.PRODUCER, count, getTime());
            }
        } catch (InterruptedException e){

        }
    }
}
