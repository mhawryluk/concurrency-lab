package lab4.pipebuffer;

import java.util.Random;

public class Buffer {
    public final int size;
    private final int[] buffer;
    private final int threadCount;
    public Random rand = new Random();


    public Buffer(int size, int threadCount) {
        this.size = size;
        this.buffer = new int[size];
        this.threadCount = threadCount;

        for (int i = 0; i < size; i++){
            buffer[i] = -1;
        }
    }

    synchronized public void swap(int index, int value){

        try {
            while (buffer[index] != (value + threadCount) % threadCount - 1) {
                wait();
            }

            buffer[index] = value;
            printBuffer();
            notifyAll();

        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private void printBuffer(){
        for (int i = 0; i < size; i++){
            System.out.print("|" + buffer[i] + "|");
        }
        System.out.println("");
    }
}
