package lab5;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String [] args) {

        int [] numThreadsArr = {1, 2, 4};

        String[] columnNames = {"Liczba wątków",
                "czas [s] - Tyle zadań, co wątków",
                "czas [s] - 10x więcej zadań niż wątków",
                "czas [s] - Tyle zadań, co pikseli"
        };


        Object[][] data = new Object[3][4];

        for (int i = 0; i < numThreadsArr.length; i++){

            int numThreads = numThreadsArr[i];

            String result1 = meanTime(numThreads, numThreads);
            String result2 = meanTime(numThreads, 10*numThreads);
            String result3 = meanTime(numThreads, 480_000);

            data[i] = new Object[]{numThreads, result1, result2, result3};
        }

        // tabelka
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 1000, 200);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    public static String meanTime(int numThreads, int numTasks){
        double [] times = new double[10];
        double timeSum = 0;

        for (int i = 0; i < 10; i++){
            times[i] = timeRun(numThreads, numTasks);
            timeSum += times[i];
        }

        double mean = timeSum/10.;

        double sigma = 0;

        for (int i = 0; i < 10; i++){
            sigma += (times[i] - mean)*(times[i] - mean);
        }

        sigma = Math.sqrt(sigma/10);

        return String.format("%.4f (\u03c3 = %.4f)", mean, sigma);
    }

    public static double timeRun(int numThreads, int numTasks) {

        long startTime = System.nanoTime();

        Mandelbrot mandelbrot = new Mandelbrot();

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

//        mandelbrot.setVisible(true);

        return (System.nanoTime() - startTime)/1e9;
    }
}
