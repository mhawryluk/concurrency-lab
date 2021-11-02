package lab3.tablefortwo;

public class Guest extends Thread{

    /*

    forever{
      wlasne sprawy;
      Kelner.chce_stolik(j);
      jedzenie;
      Kelner.zwalniam();
    }

    */

    private final int pairID;
    private final Waiter waiter;

    public Guest(Waiter waiter, int pairID){
        this.waiter = waiter;
        this.pairID = pairID;
    }

    public void run(){

        try {
            for (int i = 0; i < 3; i++){
                doSomething();
                waiter.reserve(pairID);
                eat();
                waiter.finished();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void doSomething() throws InterruptedException{
        Thread.sleep(1000);
    }

    private void eat() throws InterruptedException{
        Thread.sleep(1000);
    }
}
