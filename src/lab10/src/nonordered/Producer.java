package nonordered;
import org.jcsp.lang.*;

/** Producer class: produces one random integer and sends on
  * output channel, then terminates.
  */
public class Producer implements CSProcess { 
    private final One2OneChannelInt [] out;
    private final One2OneChannelInt [] notDone;
    private final int count;

    public Producer (One2OneChannelInt [] out, One2OneChannelInt [] notDone, int count){ 
        this.out = out;
        this.notDone = notDone;
        this.count = count;
    } 

    public void run (){
        Guard[] guards = new Guard[notDone.length];
        for (int i = 0; i < out.length; i++)
            guards[i] = notDone[i].in();

        Alternative alt = new Alternative(guards);

        for (int i = 0; i < count; i++){
            int index = alt.select();
            notDone[index].in().read();
            out[index].out().write(i);
        }
    }
}
