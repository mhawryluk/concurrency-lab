package ordered;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannelInt;

/** Consumer class: reads one int from input channel, displays it, then
  * terminates.
  */
public class Consumer implements CSProcess { 
  private final One2OneChannelInt in;
  private final int count;

  public Consumer (One2OneChannelInt in, int count){
    this.in = in;
    this.count = count;
  } 

  public void run (){
    for (int i = 0; i < count; i++){
      int item = in.in().read();
      System.out.println(item);
    }
  }
}
