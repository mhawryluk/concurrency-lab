package lab4.fatthin;

public class Producer extends PCThread{

    public Producer(Buffer buffer){
        super(buffer);
    }

    public void run(){
        try {
            for (;;) {
                requestStart();

                int count = rand.nextInt(buffer.maxProductCount / 2) + 1;
                buffer.put(count);

                buffer.saveTime(Buffer.PRODUCER, count, getTime());
            }
        } catch (InterruptedException e){
            if (Buffer.log) System.out.println("producer interrupted");
        }
    }
}
