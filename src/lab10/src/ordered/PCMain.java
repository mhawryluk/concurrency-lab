package ordered;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;
import org.jcsp.lang.StandardChannelIntFactory;

/** Main program class for Producer/Consumer example.
  * Sets up channel, creates one of each process then
  * executes them in parallel, using JCSP.
  */
public final class PCMain{
  public static void main (String[] args){ 
    new PCMain();
  }

  public PCMain (){
    int N = 10;

    StandardChannelIntFactory factory = new StandardChannelIntFactory();

    One2OneChannelInt [] channels = factory.createOne2One(N+1);

    CSProcess[] processes = new CSProcess[N+2];
    processes[0] = new Producer(channels[0]);
    processes[1] = new Consumer(channels[N]);

    for (int i = 0; i < N; i++) {
      processes[i + 2] = new Buffer(channels[i], channels[i + 1]);
    }
    
    Parallel parallel = new Parallel(processes);
    parallel.run();
  } 
}
