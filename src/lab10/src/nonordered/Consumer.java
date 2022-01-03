package nonordered;
import org.jcsp.lang.*;

/*
CONSUMER:: p: porcja;

*[(i:0..N-1) BUFFER(i)?p -> konsumuj(p)]

 */
public class Consumer implements CSProcess { 
  private final One2OneChannelInt [] bufferChannels;

  public Consumer (One2OneChannelInt [] bufferChannels){
    this.bufferChannels = bufferChannels;
  } 

  public void run (){  
    Guard[] guards = new Guard[bufferChannels.length];

    for (int i = 0; i < bufferChannels.length; i++)
      guards[i] = bufferChannels[i].in();
    
    Alternative alt = new Alternative(guards);

    while (true){ // *[
      int i = alt.select(); // (i:0..N-1)
      int p = bufferChannels[i].in().read(); // BUFFER(i)?p
      System.out.println("consumed #" + p); // konsumuj(p)
    }
  }
}
