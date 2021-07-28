import java.io.*;

public class EventSource extends Observable implements Runnable {
  public void run() {
    try {   
      final InputStreamReader isr = new InputStreamReader(System.in);
      final BufferedReader br = new BufferedReader(isr);
      while(true) {
        System.out.println("Enter Text >");
        final String str = br.readLine();
        notifyObservers(str);
      }
    }
    catch (IOException e) {}
  }
}

