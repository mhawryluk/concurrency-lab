package lab4.fatthin;

public class Buffer {
    private int productCount = 0;
    public final int maxProductCount;

    public Buffer(int maxProductCount){
        this.maxProductCount = maxProductCount;
    }

    synchronized public void add(int count){
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

    synchronized public void remove(int count){
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
