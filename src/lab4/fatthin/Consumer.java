package lab4.fatthin;


public class Consumer extends PCThread{

    public Consumer(Buffer buffer){
        super(buffer);
    }

    public void run(){
        try {
            for (;;) {
                requestStart();

                int count = rand.nextInt(buffer.maxProductCount / 2) + 1;
                buffer.get(count);

                buffer.saveTime(Buffer.CONSUMER, count, getTime());
            }
        } catch (InterruptedException e){
            if (Buffer.log) System.out.println("consumer interrupted");
        }
    }
}
