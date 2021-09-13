

import notizia.Notizia;
import pubblicatore.ProduttoreNot;
import pubblicatore.Pubblicatore;

import java.util.LinkedList;
import java.util.List;

public class Main{


	public static void main(String[] args){

		ProduttoreNot[] produttoriNotizie;

		if(args.length < 1){
			System.err.println("Inserire numero produttori");
			System.exit(1);
		}
		else{
			int numeroProduttoriNotizie = Integer.parseInt(args[0]);
			Pubblicatore p = new Pubblicatore(numeroProduttoriNotizie);

		}

	}


}