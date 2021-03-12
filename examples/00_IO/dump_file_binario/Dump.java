import java.io.*;

public class Dump {
  public static void main (String[] arg) throws IOException {
    FileInputStream in = new FileInputStream(arg[0]);
    int n = Integer.parseInt(arg[1]);
    int c = 0;
    int i = 0;
    String str = "    ";
    System.out.print(str);
    String car = "    ";
		
    while (((c = in.read()) != -1) && (i < n)) {
      str = Integer.toString(c);
      while (str.length() < 4) { str = " " + str; }
      System.out.print(str);
      i++;
      if (c < 31)
        car += '.'; // i car. di controllo diventano un punto
      else if (c < 128)
        car += (char)c; // caratteri ASCII
      else
        car += '*';     // caratteri > 127 diventano *
      if (i % 10 == 0) {    // ogni 10 caratteri un fineriga
        System.out.println(car);
        car = "    ";
        str = Integer.toString(i);
        while (str.length() < 4) { str = " " + str; }
        System.out.print(str);
      }
    }
    in.close();
  }
}



