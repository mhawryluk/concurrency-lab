package nonordered;
import org.jcsp.lang.*;

/** Main program class for Producer/Consumer example.
  * Sets up channel, creates one of each process then
  * executes them in parallel, using JCSP.
  */
public final class PCMain{

  public static void main (String[] args) {
    new PCMain();
  }

  public PCMain (){
    int N = 10;

    StandardChannelIntFactory factory = new StandardChannelIntFactory();

    One2OneChannelInt [] producerBufferChannels = factory.createOne2One(N);
    One2OneChannelInt [] consumerBufferChannels = factory.createOne2One(N);
    One2OneChannelInt [] jeszczeChannels = factory.createOne2One(N);

    CSProcess[] processes = new CSProcess[N+2];

    processes[0] = new Producer(producerBufferChannels, jeszczeChannels);
    processes[1] = new Consumer(consumerBufferChannels);

    for (int i = 0; i < N; i++) {
      processes[i+2] = new Buffer(producerBufferChannels[i], consumerBufferChannels[i], jeszczeChannels[i]);
    }

    Parallel parallel = new Parallel(processes);
    parallel.run();
  } 
}
