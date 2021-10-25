package lab1.producerconsumer;

public class Main {
    public static void main(String[] args){
        Buffer buffer = new Buffer();

        int producerCount = 5;
        int consumerCount = 5;

        Thread [] producerThreads = new Thread[producerCount];
        Thread [] consumerThreads = new Thread[consumerCount];

        for (int i = 0; i < producerCount; i++){
            Producer producer = new Producer(buffer, 3, i);
            producerThreads[i] = new Thread(producer);
            producerThreads[i].start();
        }

        for (int i = 0; i < consumerCount; i++){
            Consumer consumer = new Consumer(buffer, 3);
            consumerThreads[i] = new Thread(consumer);
            consumerThreads[i].start();
        }

        try {
            for (Thread consumerThread : consumerThreads){
                consumerThread.join();
            }
            for (Thread producerThread : producerThreads){
                producerThread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }
}
