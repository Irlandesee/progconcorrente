import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import testo.Testo;
public class Main{


	public static void main(String[] args){

		if(args.length < 1)
			System.out.println("Inserisci input file");
		else{
			File f = new File(args[0]);
			Testo t = new Testo(f);			
			int numeroParole = t.numeroParole();
			int numeroParoleDistinte = t.numeroParoleDistinte();
			int occorrenzeParola = t.contaOccorrenzeParola("io");
			LinkedList<String> l = t.paroleDistinteInOrdineAlfabetico();
			
			System.out.println("---1. Numero Parole: "+numeroParole);
			System.out.println("---2. Numero Parole Distinte: "+numeroParoleDistinte);
			System.out.println("---3. occorrenze parola: "+occorrenzeParola);
			System.out.println("---4.Lista ordinata");	
			for(String s : l)
				System.out.println(s);

			
		}

	}


}