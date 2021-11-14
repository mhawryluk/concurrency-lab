package lab4.fatthin;

public class NaiveBuffer extends Buffer{
    private int productCount = 0;

    public NaiveBuffer(int maxProductCount){
        super(maxProductCount);
    }

    synchronized public void put(int count) throws InterruptedException {
        while (this.maxProductCount - productCount < count){
            wait();
        }

        productCount += count;
        if (Buffer.log) System.out.println("added " + count + " items, product count = " + productCount);

        notifyAll();
    }

    synchronized public void get(int count) throws InterruptedException {
        while (productCount < count){
            wait();
        }

        productCount -= count;
        if (Buffer.log) System.out.println("removed " + count + " items, product count = " + productCount);

        notifyAll();
    }
}
