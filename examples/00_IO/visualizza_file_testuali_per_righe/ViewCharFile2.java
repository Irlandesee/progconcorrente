import java.io.*;
public class ViewCharFile2 {
  public static void main(String[] args) throws IOException {
    FileReader frd = new FileReader(args[0]);
    BufferedReader bfr = new BufferedReader(frd);
    String str;
    while ((str = bfr.readLine()) != null)
      System.out.println(str);
    bfr.close();
    frd.close();
  }
}

