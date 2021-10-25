package lab1.synchro;

class Counter {
    static int count = 0;

    synchronized static public void increment (){
        count++;
    }

    synchronized static public void decrement(){
        count--;
    }
}

class MyThread extends Thread {

    boolean incrementing;

    public MyThread(boolean incrementing){
        this.incrementing = incrementing;
    }

    public void run(){
        if (incrementing){
            for (int i = 0; i < 100000000; i++){
                Counter.increment();
            }
        } else {
            for (int i = 0; i < 100000000; i++){
                Counter.decrement();
            }
        }
    }
}

public class Synchro{
    public static void main(String[] args){
        MyThread thread1 = new MyThread(true);
        MyThread thread2 = new MyThread(false);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }

        System.out.println(Counter.count);
    }
}