package lab5;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String [] args) {

        Mandelbrot mandelbrot = new Mandelbrot();

        int numThreads = 10;
        int numTasks = 20;

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        Set<Future <Object>> set = new HashSet<>();

        int pixelCount = mandelbrot.height * mandelbrot.width;

        int range = pixelCount / numTasks;


        for (int i = 0; i < numTasks; i++){
            int start = i*range;

            if (i == numTasks - 1){
                range = pixelCount - start;
            }

            Task task = new Task(mandelbrot, start, range);
            Future <Object> future = executor.submit(task);
            set.add(future);
        }

        try {
            for (Future<Object> future : set) {
                future.get();
            }

        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

        mandelbrot.setVisible(true);
    }
}
