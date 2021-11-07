package lab3.tablefortwo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Waiter {

    private final Lock lock = new ReentrantLock();

    private final HashMap<Integer, Condition> conditionMap = new HashMap<>();
    private final HashSet<Integer> waitingSet = new HashSet<>();

    private int seatsAvailable = 2;
    private final Condition tableCondition = lock.newCondition();


    public void reserve(int pairID){

        lock.lock();

        try {
            if (!waitingSet.contains(pairID)){
                if (!conditionMap.containsKey(pairID)){
                    conditionMap.put(pairID, lock.newCondition());
                }
                waitingSet.add(pairID);
                conditionMap.get(pairID).await();
            } else {
                while (seatsAvailable < 2){
                    tableCondition.await();
                }
                waitingSet.remove(pairID);
                seatsAvailable = 0;
                System.out.println("Eating. pair #" + pairID);
                conditionMap.get(pairID).signal();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

    public void finished(){

        lock.lock();

        seatsAvailable++;
        System.out.println("Finished");

        if (seatsAvailable == 2) {
            tableCondition.signalAll();
        }

        lock.unlock();
    }
}
