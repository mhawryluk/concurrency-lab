package lab5;

import java.util.concurrent.Callable;

public class Task implements Callable {

    private final Mandelbrot mandelbrot;
    private final int start;
    private final int range;

    public Task(Mandelbrot mandelbrot, int start, int range) {
        this.mandelbrot = mandelbrot;
        this.start = start;
        this.range = range;
    }

    @Override
    public Object call() throws Exception {
        for (int i = start; i < start + range; i++){
            int y = i % mandelbrot.height;
            int x = i / mandelbrot.height;

            mandelbrot.pointRun(x, y);
        }
        return null;
    }
}
