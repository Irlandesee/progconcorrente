import java.io.*;
import java.net.*;

public class MultiJabberClient {
  static final int MAX_THREADS = 4;
  public static void main(String[] args) throws IOException, InterruptedException {
    InetAddress addr = InetAddress.getByName(null);
    while (JabberClientThread.threadTotalCount()<20) {
      if (JabberClientThread.threadCount() < MAX_THREADS)
        new JabberClientThread(addr);
      Thread.sleep(ThreadLocalRandom.current().nextInt(100));
    }
  }
}
