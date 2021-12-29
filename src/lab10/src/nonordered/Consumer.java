package nonordered;
import org.jcsp.lang.*;

/** Consumer class: reads one int from input channel, displays it, then
  * terminates.
  */
public class Consumer implements CSProcess { 
  private final One2OneChannelInt [] in;
  private final int count;

  public Consumer (One2OneChannelInt [] in, int count){ 
    this.in = in;
    this.count = count;
  } 

  public void run (){  
    Guard[] guards = new Guard[in.length];
    for (int i = 0; i < in.length; i++)
      guards[i] = in[i].in();
    
    Alternative alt = new Alternative(guards);

    for (int i = 0; i < count; i++){
      int index = alt.select();
      int item = in[index].in().read();
      System.out.println(index + ": " + item);
    }
  }
}
