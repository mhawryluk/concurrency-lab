package lab4.fatthin;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairBuffer extends Buffer{
    private int productCount = 0;

    private final Lock lock = new ReentrantLock();

    private final Condition producersCond = lock.newCondition();
    private final Condition firstProducerCond = lock.newCondition();
    private boolean producerWaiting = false;

    private final Condition consumersCond = lock.newCondition();
    private final Condition firstConsumerCond = lock.newCondition();
    private boolean consumerWaiting = false;

    public FairBuffer(int maxProductCount){
        super(maxProductCount);
    }

    public void put(int count) throws InterruptedException{
        lock.lock();
        try {
            while (producerWaiting){
              producersCond.await();
            }

            producerWaiting = true;
//            System.out.println("first producer, count: " + count + " productCount: " + productCount);

            while (maxProductCount - productCount < count){
                firstProducerCond.await();
            }

            productCount += count;
            producerWaiting = false;

//            System.out.println("added " + count + " items, product count = " + productCount);

            producersCond.signalAll();
            firstConsumerCond.signal();

        } finally {
            lock.unlock();
        }
    }

    public void get(int count) throws InterruptedException{
        lock.lock();
        try {
            while (consumerWaiting){
                consumersCond.await();
            }

            consumerWaiting = true;
//            System.out.println("first consumer, count: " + count + " productCount: " + productCount);

            while (productCount < count){
                firstConsumerCond.await();
            }

            productCount -= count;
            consumerWaiting = false;

//            System.out.println("removed " + count + " items, product count = " + productCount);

            consumersCond.signalAll();
            firstProducerCond.signal();
        } finally {
            lock.unlock();
        }
    }
}
