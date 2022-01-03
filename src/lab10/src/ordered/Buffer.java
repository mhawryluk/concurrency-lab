package ordered;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

/*
BUFFER(i:O..N-l):: p: porcja;

        *[true -> [i = 0 -> PRODUCER?p

        []i <> 0 -> BUFFER(i-l)?p];

        [i = N-l -> CONSUMER!p

        []i <> N-l -> BUFFER(i+l)!p]

        ]

 */


public class Buffer implements CSProcess { 

  private final One2OneChannelInt in, out;

  public Buffer (One2OneChannelInt in, One2OneChannelInt out){
    this.out = out;
    this.in = in;
  }

  public void run (){
      while (true){ //  *[true ->
          int p = in.in().read(); // [i = 0 -> PRODUCER?p []i <> 0 -> BUFFER(i-l)?p];
          out.out().write(p); // [i = N-1 -> CONSUMER!p []i <> N-1 -> BUFFER(i+1)!p]
      }
  }
} 
