package nonordered;
import org.jcsp.lang.*;

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
    int count = 100;

    StandardChannelIntFactory fact = new StandardChannelIntFactory();

    One2OneChannelInt channelsProd[] = fact.createOne2One(N);
    One2OneChannelInt channelsNotDone[] = fact.createOne2One(N);
    One2OneChannelInt channelsCons[] = fact.createOne2One(N);

    CSProcess[] procList = new CSProcess[N+2];
    procList[0] = new Producer(channelsProd, channelsNotDone, count);
    procList[1] = new Consumer(channelsCons, count);

    for (int i = 0; i < N; i++)
      procList[i+2] = new Buffer(channelsProd[i], channelsCons[i], channelsNotDone[i]);
    
    Parallel par = new Parallel(procList);
    par.run();

  } 
}
