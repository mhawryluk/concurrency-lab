package ordered;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

/*

PRODUCER:: p: porcja;
*[true -> produkuj(p); BUFFER(0)!p]

 */


public class Producer implements CSProcess { 
    private final One2OneChannelInt bufferChannel;

    public Producer (One2OneChannelInt bufferChannel){
        this.bufferChannel = bufferChannel;
    } 

    public void run (){
        int p = 0;
        while (true){ // *[true ->
            p++; // produkuj(p);
            bufferChannel.out().write(p); // BUFFER(0)!p
        }
    }
}
