package lab4.pipebuffer;


public class PipeThread extends Thread{
    private final Buffer buffer;
    private final int value;


    public PipeThread(Buffer buffer, int value){
        this.buffer = buffer;
        this.value = value;
    }

    public void run(){
        try {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < buffer.size; j++) {
                    buffer.swap(j, value);
                    Thread.sleep(buffer.rand.nextInt(100));
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
