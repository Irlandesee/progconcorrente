package pubblicatore;

import java.util.Queue;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import notizia.*;

public class Pubblicatore{

	private Queue<Notizia> codaNotiziePolitica;
	private Queue<Notizia> codaNotizieAttualita;
	private Queue<Notizia> codaNotizieScienza;
	private Queue<Notizia> codaNotizieSport;

	private LinkedList<String> listFruitoriNotiziePolitica;
	private LinkedList<String> listFruitoriNotizieAttualita;
	private LinkedList<String> listFruitoriNotizieScienza;
	private LinkedList<String> listFruitoriNotizieSport;
	private LinkedList<ProduttoreNot> listProduttoriNotizie;

	private final int waitingTime = 5000;

	private int numeroFruitoriNotizie;

	public Pubblicatore(int _numeroFruitoriNotizie){
		numeroFruitoriNotizie = _numeroFruitoriNotizie;
		init();
		exec();
	}

	private void init(){
		listProduttoriNotizie = new LinkedList<ProduttoreNot>();
		codaNotiziePolitica = new LinkedList<Notizia>();
		codaNotizieAttualita = new LinkedList<Notizia>();
		codaNotizieScienza = new LinkedList<Notizia>();
		codaNotizieSport = new LinkedList<Notizia>();
	}

	private synchronized void exec(){
		ProduttoreNot p;
		System.out.printf("Creo %d produttori di notizie\n", numeroFruitoriNotizie);
		for(int i = 0; i < numeroFruitoriNotizie; i++){
			p = new ProduttoreNot();
			listProduttoriNotizie.add(p);
			p.start();
		}
		Iterator itProduttori = listProduttoriNotizie.iterator();
		while(itProduttori.hasNext()){
			p = (ProduttoreNot) itProduttori.next();
			itProduttori.remove();
			
			while(!p.getDone()){
				try{
					System.out.printf("Aspetto che %d finisca\n", p.getProdID());
					wait(ThreadLocalRandom.current().nextInt(100, 400));
				}catch(InterruptedException ie){}
			}
			String producerType = p.getProducerType();
			List<Notizia> notizie = new LinkedList<Notizia>();
			switch(producerType){
				case "politica":
					notizie = p.getNotizie();
					for(Notizia n : notizie)
						codaNotiziePolitica.add(n);
					break;
				case "attualita":
					notizie = p.getNotizie();
					for(Notizia n : notizie)
						codaNotizieAttualita.add(n);
					break;
				case "scienza":
					notizie = p.getNotizie();
					for(Notizia n : notizie)
						codaNotizieScienza.add(n);
					break;
				case "sport":
					notizie = p.getNotizie();
					for(Notizia n : notizie)
						codaNotizieSport.add(n);
					break;
			}try{
				System.out.printf("join di p:%d", p.getProdID());
				p.join();	
			}catch(InterruptedException ie){}
		}
		System.out.println("Notizie acquisite, ora le pubblico");
		if(codaNotiziePolitica.size() > 0){
			System.out.printf("Pubblicazione %d notizie di politica\n", codaNotiziePolitica.size());
			Notizia n;
			Iterator it = codaNotiziePolitica.iterator();
			int i = 0;
			while(it.hasNext()){
				n = (Notizia) it.next();
				it.remove();
				i++;
				System.out.printf("%d. %s\n", i, n.getContenuto());
			}

		}
		if(codaNotizieAttualita.size() > 0){
			System.out.printf("Pubblicazione %d notizie di attualita\n", codaNotizieAttualita.size());
			Notizia n;
			Iterator it = codaNotizieAttualita.iterator();
			int i = 0;
			while(it.hasNext()){
				n = (Notizia) it.next();
				it.remove();
				i++;
				System.out.printf("%d. %s\n", i, n.getContenuto());
			}
		}
		if(codaNotizieScienza.size() > 0){
			System.out.printf("Pubblicazione %d notizie di scienza\n", codaNotizieScienza.size());
			Notizia n;
			Iterator it = codaNotizieScienza.iterator();
			int i = 0;
			while(it.hasNext()){
				n = (Notizia) it.next();
				it.remove();
				i++;
				System.out.printf("%d. %s\n", i, n.getContenuto());
			}
		}
		if(codaNotizieSport.size() > 0){
			System.out.printf("Pubblicazione %d notizie di sport\n", codaNotizieSport.size());
			Notizia n;
			Iterator it = codaNotizieSport.iterator();
			int i = 0;
			while(it.hasNext()){
				n = (Notizia) it.next();
				it.remove();
				i++;
				System.out.printf("%d. %s\n", i, n.getContenuto());
			}
		}
	}

	public LinkedList<String> getFruitoriPolitica(){
		return listFruitoriNotiziePolitica;
	}

	public void setFruitoriPolitica(LinkedList<String> _fruitoriPolitica){
		listFruitoriNotiziePolitica = _fruitoriPolitica;
	}

	public LinkedList<String> getFruitoriAttualita(){
		return listFruitoriNotizieAttualita;
	}

	public void setFruitoriAttualita(LinkedList<String> _fruitoriAttualita){
		listFruitoriNotizieAttualita = _fruitoriAttualita;
	}

	public LinkedList<String> getFruitoriScienza(){
		return listFruitoriNotizieScienza;
	}

	public void setFruitoriScienza(LinkedList<String> _fruitoriScienza){
		listFruitoriNotizieScienza = _fruitoriScienza;
	}

	public LinkedList<String> getFruitoriSport(){
		return listFruitoriNotizieSport;
	}

	public void setFruitoriSport(LinkedList<String> _fruitoriSport){
		listFruitoriNotizieSport = _fruitoriSport;
	}

}