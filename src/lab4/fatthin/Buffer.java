package lab4.fatthin;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

class TimeRecord {
    public int count = 0;
    public long timeSum = 0;
}

public abstract class Buffer {

    protected final int maxProductCount;

    static public boolean log = false;

    static public final int PRODUCER = 0;
    static public final int CONSUMER = 1;

    private final HashMap<Integer, TimeRecord> producerTimes = new HashMap<>();
    private final HashMap<Integer, TimeRecord> consumerTimes = new HashMap<>();

    protected Buffer(int maxProductCount) {
        this.maxProductCount = maxProductCount;
    }

    abstract public void put(int count) throws InterruptedException;
    abstract public void get(int count) throws InterruptedException;

    protected void saveTime(int threadType, int count, long time){

        var timesMap = switch (threadType) {
            case PRODUCER -> producerTimes;
            case CONSUMER -> consumerTimes;
            default -> throw new IllegalStateException("Unexpected threadType: " + threadType);
        };

        if (!timesMap.containsKey(count)) {
            timesMap.put(count, new TimeRecord());
        }

        TimeRecord timeRecord = timesMap.get(count);

        if (timeRecord != null) {
            timeRecord.timeSum += time;
            timeRecord.count++;
        }
    }

    public void saveTimes(String fileName, int maxCount) throws IOException {
        TimeRecord timeRecord;

        FileWriter fileWriter = new FileWriter(fileName + "-producers.csv");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int count = 0; count <= maxCount; count++) {
            timeRecord = producerTimes.get(count);
            if (timeRecord != null)
                printWriter.print(count + "," + (timeRecord.timeSum / timeRecord.count) / 1e9 + "\n");
        }

        printWriter.close();

        fileWriter = new FileWriter(fileName + "-consumers.csv");
        printWriter = new PrintWriter(fileWriter);

        for (int count = 0; count <= maxCount; count++) {
            timeRecord = consumerTimes.get(count);
            if (timeRecord != null)
                printWriter.print(count +"," + (timeRecord.timeSum / timeRecord.count) / 1e9 + "\n");
        }

        printWriter.close();
    }
}

