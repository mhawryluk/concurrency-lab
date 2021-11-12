package lab4.fatthin;

public class NaiveBuffer extends Buffer{
    private int productCount = 0;

    public NaiveBuffer(int maxProductCount){
        super(maxProductCount);
    }

    synchronized public void put(int count){
        try {
            while (this.maxProductCount - productCount < count){
                wait();
            }

            productCount += count;
            System.out.println("added " + count + " items, product count = " + productCount);
            notifyAll();

        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    synchronized public void get(int count){
        try {
            while (productCount < count){
                wait();
            }

            productCount -= count;
            System.out.println("removed " + count + " items, product count = " + productCount);
            notifyAll();

        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
