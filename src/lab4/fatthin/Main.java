package lab4.fatthin;

import java.io.IOException;

public class Main {

    public static void main(String[] args){
        int [] bufferSizes = {1_000, 10_000, 100_000};
        int [] threadCounts = {10, 100, 1_000};

        for (int bufferSize: bufferSizes){
            for (int threadCount : threadCounts){
                run(bufferSize, threadCount, threadCount, 'n');
                run(bufferSize, threadCount, threadCount, 'f');
            }
        }
    }

    public static void run(int bufferSize, int producerCount, int consumerCount, char bufferType){

        Buffer buffer = null;

        switch (bufferType) {
            case 'n' -> buffer = new FairBuffer(bufferSize);
            case 'f' -> buffer = new NaiveBuffer(bufferSize);
        }

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

        try{
            Thread.sleep(30000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        for (Consumer consumer : consumers) consumer.interrupt();
        for (Producer producer : producers) producer.interrupt();

        try {
            buffer.saveTimes("results/" + bufferType + "-" + bufferSize + "-" + producerCount + "-" + consumerCount, bufferSize/2);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
