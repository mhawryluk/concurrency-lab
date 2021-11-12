package lab4.fatthin;

public abstract class Buffer {

    protected final int maxProductCount;

    protected Buffer(int maxProductCount) {
        this.maxProductCount = maxProductCount;
    }

    abstract public void put(int count);
    abstract public void get(int count);
}

