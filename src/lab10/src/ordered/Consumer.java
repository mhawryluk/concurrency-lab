package ordered;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

/*
 CONSUMER:: p: porcja;
  *[BUFFER(N-1)?p -> konsumuj(p)]
 */

public class Consumer implements CSProcess { 
  private final One2OneChannelInt bufferChannel;

  public Consumer (One2OneChannelInt bufferChannel){
    this.bufferChannel = bufferChannel;
  } 

  public void run (){
    while (true){ // *[
      int p = bufferChannel.in().read(); // BUFFER(N-1)?p ->
      System.out.println("Consumed #" + p); // konsumuj(p)
    }
  }
}
