package nonordered;
import org.jcsp.lang.*;


public class Buffer implements CSProcess { 

  private One2OneChannelInt in, out, notDone;

  public Buffer (One2OneChannelInt in, One2OneChannelInt out, One2OneChannelInt notDone){
    this.out = out;
    this.in = in;
    this.notDone = notDone;
  }

  public void run (){
      while (true){
        notDone.out().write(0);
        out.out().write(in.in().read());
      }
  }

} 
