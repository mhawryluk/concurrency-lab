package lab3.tablefortwo;

import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Waiter {

    private final Lock lock = new ReentrantLock();
    private final HashMap<Integer, Condition> pairWaiting = new HashMap();

    private final HashMap<Integer, Integer> waitingMap = new HashMap<>();
    private boolean isTableTaken = false;

    private final Condition tableTaken = lock.newCondition();

    public Waiter(){

    }

    public void reserve(int pairID){

        lock.lock();

        try {

            if (waitingMap.containsKey(pairID)){
                waitingMap.put(pairID, 2);
            } else {
                waitingMap.put(pairID, 0);
            }

            while (isTableTaken){
                tableTaken.await();
            }

            while (waitingMap.containsKey(pairID)){

            }

            waitingMap.remove(pairID);

        } catch (InterruptedException e){
            e.printStackTrace();

        } finally{
            lock.unlock();
        }

    }

    public void finished(){

        lock.lock();

        try {

        } finally {
            lock.unlock();
        }

    }
}
