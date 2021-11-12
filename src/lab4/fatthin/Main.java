package lab4.fatthin;

public class Main {

    public static void main(String[] args) {
        int producerCount = 3;
        int consumerCount = 3;

        Buffer buffer = new FairBuffer(10);

        Producer [] producers = new Producer[producerCount];
        Consumer [] consumers = new Consumer[consumerCount];

        for (int i = 0; i < producerCount; i++){
            producers[i] = new Producer(buffer);
            producers[i].start();
        }

        for (int i = 0; i < consumerCount; i++){
            consumers[i] = new Consumer(buffer);
            consumers[i].start();
        }

        try {
            for (Consumer consumer : consumers) {
                consumer.join();
            }
            for (Producer producer : producers) {
                producer.join();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
