package lab4.fatthin;

import java.io.IOException;

public class Main {

    public static void main(String[] args){
//        runExample();
        runTests();
    }

    public static void runTests(){
        Buffer.log = false;
        int [] bufferSizes = {1_000, 10_000, 100_000};
        int [] threadCounts = {10, 100, 1_000};

        for (int bufferSize: bufferSizes){
            for (int threadCount : threadCounts){
                run(bufferSize, threadCount, threadCount, 'n', 30000, true);
                run(bufferSize, threadCount, threadCount, 'f', 30000, true);
            }
        }
    }

    public static void runExample(){
        Buffer.log = true;

        System.out.println("--naive--");
        run(10, 3, 3, 'n', 5000, false);

        System.out.println("--fair--");
        run(10, 3, 3, 'f', 5000, false);
    }

    public static void run(int bufferSize, int producerCount, int consumerCount, char bufferType, int timeInterval, boolean saveToFile){
        Buffer buffer = null;

        switch (bufferType) {
            case 'n' -> buffer = new NaiveBuffer(bufferSize);
            case 'f' -> buffer = new FairBuffer(bufferSize);
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

        try {
            Thread.sleep(timeInterval);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        for (Consumer consumer : consumers) consumer.interrupt();
        for (Producer producer : producers) producer.interrupt();

        if (saveToFile){
            try {
                buffer.saveTimes("src/lab4/fatthin/results/" + bufferType + "-" + bufferSize + "-" + producerCount + "-" + consumerCount, bufferSize/2);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
