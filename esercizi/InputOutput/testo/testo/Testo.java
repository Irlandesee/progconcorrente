package testo;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;

public class Testo{

	private File in;
	private LinkedList<String> testo;
	private LinkedList<String> testoFormatted;
	private LinkedList<String> paroleDistinteOdinate;
	private LinkedList<String> paroleIncontrate;

	public Testo(File in){
		this.in = in;
		readTesto();
		testoFormatted = prepareText(testo);
		paroleIncontrate = new LinkedList<String>();
	}

	public File getInputFile(){
		return this.in;
	}

	private void readTesto(){
		System.out.println("Started Reading");
		try{
			BufferedReader bReader = new BufferedReader(new FileReader(in));
			String rigaLetta;
			testo = new LinkedList<String>();
			while((rigaLetta = bReader.readLine()) != null){
				testo.add(rigaLetta);
			}

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private String[] splitRiga(String riga){
		return riga.split(" ");
	}

	private LinkedList<String> prepareText(LinkedList<String> text){
		LinkedList<String> l = new LinkedList<String>();
		String[] tmp;
		String suffixComma = ",";
		String suffixPoint = ".";
		for(String s : text){
			tmp = splitRiga(s);
			for(String temp : tmp){
				if(temp.endsWith(suffixComma) || temp.endsWith(suffixPoint)){
					temp = temp.substring(0, temp.length()-1);
					l.add(temp);
				}
				else
					l.add(temp);
			}
				}
			return l;
	}

	public LinkedList<String> getTesto(){
		return testo;
	}

	public LinkedList<String> getTestoFormatted(){
		return testoFormatted;
	}

	public int numeroParole(){
		return testoFormatted.size();
	}

	public int numeroParoleDistinte(){
		int numeroParoleDistinte = 0;

		for(int i = 0; i < testoFormatted.size(); i++){
			String tmp = testoFormatted.get(i);
			if(!paroleIncontrate.contains(tmp)){
				paroleIncontrate.add(tmp);
				numeroParoleDistinte++;
			}
		}
		
		return numeroParoleDistinte;
	}
	
	public int contaOccorrenzeParola(String parola){
		int occorrenzeParola = 0;
		String tmp = "";
		for(int i = 0; i < testoFormatted.size(); i++){
			tmp = testoFormatted.get(i);
			if(tmp.equals(parola))
				occorrenzeParola++;
		}
		return occorrenzeParola;
	}

	public LinkedList<String> getParoleIncontrate(){
		return this.paroleIncontrate;
	}

	public LinkedList<String> paroleDistinteInOrdineAlfabetico(){
		String tmp = "", temp = "";
		LinkedList<String> l = new LinkedList<String>();
		l = getParoleIncontrate();

		Collections.sort(l);	
		return l;
	}

}