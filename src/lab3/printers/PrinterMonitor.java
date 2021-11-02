package lab3.printers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterMonitor {

    private final Lock lock = new ReentrantLock();
    private final Condition empty  = lock.newCondition();
    private final Set<Integer> freePrinters = new HashSet<>();


    public PrinterMonitor(int printerCount){
        for (int i = 0; i < printerCount; i++){
            freePrinters.add(i);
        }
    }

    public int reserve(){
        lock.lock();
        try {
            while (freePrinters.size() == 0)
                empty.await();

            int printerNum = -1;
            Iterator<Integer> itr = freePrinters.iterator();
            printerNum = itr.next();
            itr.remove();

            return printerNum;
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return -1;
    }

    public void finished(int printerNum){
        lock.lock();
        try {
            freePrinters.add(printerNum);
            empty.signal();
        } finally {
            lock.unlock();
        }
    }
}
