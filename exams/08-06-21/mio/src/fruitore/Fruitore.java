package fruitore;

import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import notizia.Notizie;

public class Fruitore{

	private final String[] newsTypes = {"politica", "attualita", "scienza", "sport"};
	private List<String> tipiNotizieFruitore; //tipo di notizie a cui il fruitore è interessato
	private List<Notizia> notizieRicevute;

	public Fruitore(){

	}

	private void init(){
		tipiNotizieFruitore = new LinkedList<String>();
		int numeroTipiNotizie = ThreadLocalRandom.current().nextInt(1, 5);
		if(numeroTipiNotizie == newsType.length-1){
			for(String s : newsTypes)
				tipiNotizieFruitore.add(s);
		}
		else{
			int randomIndex;
			for(int i = 0; i < numeroTipiNotizie; i++){
				randomIndex = ThreadLocalRandom.current().nextInt(newsType.length-1);
				String newsType = newsType[randomIndex];
				tipiNotizieFruitore.add(newsType);
			}
		}
		//comunica i tipi di notizie voluti
		//aspetta conferma
			//aspetta notizie -> consumale
		//altrimenti comunica di nuovo
	}


}