package lab4.pipebuffer;

public class Main {

    public static void main(String[] args){

        int bufferSize = 10;
        int threadCount = 5;

        Buffer buffer = new Buffer(bufferSize, threadCount);

        PipeThread [] threads = new PipeThread[threadCount];

        for (int i = 0; i < threadCount; i++){
            threads[i] = new PipeThread(buffer, i-1);
            threads[i].start();
        }

        try {
            for (PipeThread thread : threads){
                thread.join();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
