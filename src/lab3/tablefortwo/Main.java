package lab3.tablefortwo;


public class Main {
    public static void main(String[] args) {
        int pairsCount = 5;

        Guest [] guests = new Guest[pairsCount*2];
        Waiter waiter = new Waiter();

        for (int i = 0; i < pairsCount; i++){
            guests[2*i] = new Guest(waiter, i);
            guests[2*i].start();

            guests[2*i + 1] = new Guest(waiter, i);
            guests[2*i + 1].start();
        }

        try {
            for (Thread guest : guests){
                guest.join();
            }

        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }
}