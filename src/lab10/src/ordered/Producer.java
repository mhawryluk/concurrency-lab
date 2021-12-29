package ordered;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannelInt;

/** Producer class: produces one random integer and sends on
  * output channel, then terminates.
  */
public class Producer implements CSProcess { 
    private final One2OneChannelInt out;
    private final int count;

    public Producer (One2OneChannelInt out, int count){
        this.out = out;
        this.count = count;
    } 

    public void run (){
        for (int i = 0; i < count; i++){
            out.out().write(i);
        }
    }
}
