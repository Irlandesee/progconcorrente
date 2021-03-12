import java.io.*;
public class CopiaBin {
  public static void main (String[] arg) throws IOException {
    int c = 0;
    FileInputStream in = new FileInputStream(arg[0]);
    FileOutputStream out = new FileOutputStream(arg[1]);
    while ((c = in.read()) != -1) {
      out.write(c);
    }
    out.close();
    in.close();
  }
}

