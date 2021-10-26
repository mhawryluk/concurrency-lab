package lab2.semaphore_gen;

class Customer extends Thread {

    private final Store store;

    public Customer(Store store){
        this.store = store;
    }

    public void run(){
        store.enter();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            store.leave();
        }
    }
}

class Store {
    private final Semaphore sem;
    private final int customerLimit;

    public Store(int customerLimit) {
        sem = new Semaphore(customerLimit);
        this.customerLimit = customerLimit;
    }

    public void enter(){
        sem.p();
        System.out.println("entered, customers count: " + (customerLimit - sem.getValue()));
    }

    public void leave(){
        sem.v();
        System.out.println("left, customers count: " + (customerLimit - sem.getValue()));
    }
}

public class Main {

    public static void main(String[] args){

        int customersCount = 10;
        int customerLimit = 3;

        Store store = new Store(customerLimit);
        Customer[] customers = new Customer[customersCount];

        for (int i = 0; i < customersCount; i++){
            customers[i] = new Customer(store);
            customers[i].start();
        }

        try {
            for (Customer customer : customers){
                customer.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
