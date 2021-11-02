package lab3.printers;

public class P extends Thread{

    private final PrinterMonitor monitor;

    public P(PrinterMonitor monitor){
        this.monitor = monitor;
    }

    /*

    forever{
      Utworz_zadanie_do_druku();
      nr_drukarki=Monitor_Drukarek.zarezerwuj();
      drukuj(nr_drukarki);
      Monitor_Drukarek.zwolnij(nr_drukarki);
    }
     */

    public void run(){
        int printerNum = -1;

        for (int i = 0; i < 3; i++){
            try {
                prepareToPrint();
                printerNum = monitor.reserve();
                print(printerNum);
            } catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                monitor.finished(printerNum);
            }
        }
    }

    private void prepareToPrint() throws InterruptedException{
        Thread.sleep(10);
    }

    private void print(int printerNum) throws InterruptedException{
        System.out.println("printing using printer #" + printerNum + " START");
        Thread.sleep(10);
        System.out.println("printing using printer #" + printerNum + " DONE");
    }
}
