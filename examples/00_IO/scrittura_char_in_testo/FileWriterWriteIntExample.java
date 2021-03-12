import java.io.*;
public class FileWriterWriteIntExample {
  public static void main(String[] args) {
    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter("file.txt");
      fileWriter.write('C');
      fileWriter.write('i');
      fileWriter.write('a');
      fileWriter.write('o');
    } catch (Exception e) { e.printStackTrace(); }
      finally {
        try {
          if(fileWriter != null) {
            fileWriter.flush();
            fileWriter.close();					
          }
        } catch (IOException e) { e.printStackTrace(); }
}}}

