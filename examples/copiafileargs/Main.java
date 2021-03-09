import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Main{

	public static void main(String[] args){

		if(args.length < 2)
			System.out.println("Inserisci file di copia e di scrittura");
		else{

			String fileLettura = args[0];
			String fileScrittura = args[1];

			File fIn = new File(fileLettura);
			File fOut = new File(fileScrittura);

			String inputString = "";
			LinkedList<String> stringheLette = new LinkedList<String>();

			try{
				BufferedReader bReader = new BufferedReader(new FileReader(fIn));
				while((inputString = bReader.readLine()) != null){
					stringheLette.add(inputString);
				}	
				
				bReader.close();
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				try{
					BufferedWriter bWriter = new BufferedWriter(new FileWriter(fOut));

					for(String s : stringheLette){
						bWriter.write(s);
						bWriter.newLine();
					}

					bWriter.flush();
					bWriter.close();

					System.out.println("Done");
				}catch(IOException e){
					e.printStackTrace();
				}

			}

		}
	}

}