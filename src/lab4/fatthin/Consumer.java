package lab4.fatthin;

import java.util.Random;

public class Consumer extends PCThread{

    public Consumer(Buffer buffer){
        super(buffer, new Random());
    }

    public void run(){
        try {
            for (;;) {
                requestStart();
                int count = rand.nextInt(buffer.maxProductCount / 2) + 1;
                buffer.get(count);
                buffer.saveTime(buffer.CONSUMER, count, getTime());
            }
        } catch (InterruptedException e){

        }
    }
}
