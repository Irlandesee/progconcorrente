import java.io.*;

public class ViewCharFile {
  public static void main(String[] args) throws IOException {
    FileReader frd = new FileReader(args[0]);
    int i=0;
    while ((i = frd.read()) != -1)
      System.out.print((char)i);
    frd.close();
  }
}

