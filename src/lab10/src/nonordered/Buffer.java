package nonordered;
import org.jcsp.lang.*;

/*
BUFFER(i:0..N-1):: p: porcja;

*[true -> PRODUCER!JESZCZE() ;
  [PRODUCER?p -> CONSUMER!p]
]
 */

public class Buffer implements CSProcess { 

  private final One2OneChannelInt producerChannel, consumerChannel, jeszczeChannel;

  public Buffer (One2OneChannelInt producerChannel, One2OneChannelInt consumerChannel, One2OneChannelInt jeszczeChannel){
    this.consumerChannel = consumerChannel;
    this.producerChannel = producerChannel;
    this.jeszczeChannel = jeszczeChannel;
  }

  public void run (){
      while (true){ //*[true
        jeszczeChannel.out().write(0); // PRODUCER!JESZCZE()
        consumerChannel.out().write(producerChannel.in().read()); // [PRODUCER?p -> CONSUMER!p]
      }
  }
} 
