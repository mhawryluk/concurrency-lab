package nonordered;
import org.jcsp.lang.*;

/*
 [PRODUCER:: p: porcja;
     *[true -> produkuj(p);

     [(i:0..N-1) BUFFER(i)?JESZCZE() -> BUFFER(i)!p]
 ]
  */
public class Producer implements CSProcess { 
    private final One2OneChannelInt [] bufferChannels;
    private final One2OneChannelInt [] jeszczeChannels;

    public Producer (One2OneChannelInt [] bufferChannels, One2OneChannelInt [] jeszczeChannels){
        this.bufferChannels = bufferChannels;
        this.jeszczeChannels = jeszczeChannels;
    } 

    public void run (){
        Guard[] guards = new Guard[jeszczeChannels.length];
        for (int i = 0; i < bufferChannels.length; i++)
            guards[i] = jeszczeChannels[i].in();
        Alternative alt = new Alternative(guards);

        int p = 0;

        while (true){ //*[true ->
            p++; // produkuj(p);
            int i = alt.select(); // [(i:0..N-1)
            jeszczeChannels[i].in().read(); // BUFFER(i)?JESZCZE()
            bufferChannels[i].out().write(p); // BUFFER(i)!p
        }
    }
}
