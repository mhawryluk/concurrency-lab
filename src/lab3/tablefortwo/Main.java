package lab3.tablefortwo;

import lab3.printers.P;
import lab3.printers.PrinterMonitor;

public class Main {
    public static void main(String[] args) {
        int threadCount = 10;
        int printerCount = 3;

        Thread [] threads = new Thread[threadCount];
        PrinterMonitor printerMonitor = new PrinterMonitor(printerCount);

        for (int i = 0; i < threadCount; i++){
            threads[i] = new P(printerMonitor);;
            threads[i].start();
        }

        try {
            for (Thread thread : threads){
                thread.join();
            }

        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }
}