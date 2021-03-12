import java.io.*;
public class CopyChar {
  public static void main (String[] arg) throws IOException {
    String str = "";
    FileReader frd = new FileReader(arg[0]);
    BufferedReader bfr = new BufferedReader(frd);
    FileWriter fwr = new FileWriter(arg[1]);
    PrintWriter bwr = new PrintWriter(fwr);
    while ((str = bfr.readLine()) != null)
      bwr.println(str);
    bfr.close();
    frd.close();
    bwr.close();
    fwr.close();
  }
}

