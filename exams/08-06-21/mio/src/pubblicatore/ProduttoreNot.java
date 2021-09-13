package pubblicatore;

import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import notizia.Notizia;

public class ProduttoreNot extends Thread{

	private static int prodID = 0;

	private static final int MAX_NOTIZIE = 1;

	public static int getProdID(){
		return prodID;
	}

	private final String[] TIPI_NOTIZIE = {
		"politica", "attualita", "scienza", "sport"};

	private final String[] NOTIZIE_POLITICA = {
		"Arriva estensione light Green pass, Lega diserta Camera",
		"I 91 anni di Liliana Segre, custode della nostra storia",
		"Covid: Cei ai vescovi, far vaccinare operatori parrocchie",
		"Giustizia:Commissione conclude voto riforma processo civile. In aula mercoledì",
		"Via libera alla mini coltivazioni di cannabis a casa",
		"Mattarella, vaccinarsi un dovere civico e morale. Sottrarsi mette a rischio vite",
		"Green pass: Salvini apre sugli statali, Conte frena su obbligo vaccinale",
		"Salvini e Meloni si abbracciano, prove tecniche di governo"};

	private final String[] NOTIZIE_ATTUALITA = {
		"Covid: Italia tutta bianca tranne la Sicilia. Scende ancora l'Rt a 0,92 e l'incidenza passa da 74 a 64",
		"Green pass: Lamorgese, rischio estremismi, preoccupazione",
		"Scuola: Green pass genitori? I presidi, crea problemi enormi",
		"Covid: controlli Nas in terme e spa, chiuse 11 strutture",
		"Donna uccisa a colpi arma da fuoco, si sospetta il marito",
		"Blitz della Polizia contro No Vax, perquisizioni in varie città",
		"No Vax morta nel Bresciano, madre grave",
		"Violenze in carcere: chiusa inchiesta per 120 indagati",
		"Scontro tra auto e monopattino a Roma, muore un 34enne",
		"'E' nata Poesia', la bimba di una cittadina afghana fuggita da Kabul",
		"Covid: Lazio promosso, torna in giallo nella mappa Ecdc"};

	private final String[] NOTIZIE_SCIENZA = {
		"Premio Breakthrough ai vaccini a mRna e alle italiane della fisica",
		"Ecco 216-Cleopatra, l’asteroide a forma di osso",
		"Georadar e satelliti per prevenire voragini a Roma",
		"Dieci anni per trovare ET, è cominciata la corsa",
		"La variante Mu 'altamente resistente' agli anticorpi",
		"Tumore del polmone, trovate le cause nei non fumatori",
		"Rochette, la prima pietra marziana che arriverà sulla Terra",
		"Il vaccino anti Covid-19 di Takis-Rottapharm completa la fase 1",
		"Le stelle più comuni invecchiano lentamente"};

	private final String[] NOTIZIE_SPORT = {
		"Atletica: il diamante dopo l'oro, è l'anno di Tamberi",
		"Europei di Pallavolo: Italia-Repubblica Ceca 3-1",
		"F1, Leclerc: 'Monza imparagonabile, è weekend speciale",
		"F1: Sainz: 'Podio a Monza sarebbe un risultato bellissimo'",
		"Il Ct azzurro Mancini bloccato in autostrada: 'Vergognatevi'",
		"Jerome Boateng condannato per lesioni, dovrà pagare 1,8 mln",
		"Us Open: Djokovic batte Berrettini ai quarti di finale",
		"Moto: ad Aragon riecco Vinales, Dovizioso 'caldo' per Misano",
		"Europei ciclismo: Ganna argento nella crono, vince Kung",
		"Wenger: 'Il Mondiale ogni due anni è chiesto da 166 Paesi'",
		"Afghanistan, Malagò: 'Negare sport alle donne è un'assurdità'",
		"Us Open: Medvedev e Auger-Aliassime primi semifinalisti"};

	private List<Notizia> notizie; 
	private String tipoNotizia;
	private boolean done;

	public ProduttoreNot(){
		notizie = new LinkedList<Notizia>();
		tipoNotizia = generateNewsType(); //generazione causale del tipo di notizia da produrre
		this.setName("PrNot " + prodID + ": "+tipoNotizia);
		System.out.println("Nuovo produttore notizie creato: "+ getProdID());
		prodID++;
	}

	public List<Notizia> getNotizie(){
		return notizie;
	}

	/**
	 * Metodo generante il tipo di notizia da produrre
	 * */
	private String generateNewsType(){
		int length = TIPI_NOTIZIE.length;
		int randomIndex = ThreadLocalRandom.current().nextInt(length);
		return TIPI_NOTIZIE[randomIndex];
	}

	/**
	*	Metodo generante una randomica stringa salvata negli array
	* */
	private String generateContent(String tipoNotizia){
		String content = "";
		int length = 0;
		int randomIndex = 0;
		switch(tipoNotizia){
			case "politica":
				length = NOTIZIE_POLITICA.length;
				randomIndex = ThreadLocalRandom.current().nextInt(length);
				return NOTIZIE_POLITICA[randomIndex];
			case "attualita":
				length = NOTIZIE_ATTUALITA.length;
				randomIndex = ThreadLocalRandom.current().nextInt(length);
				return NOTIZIE_ATTUALITA[randomIndex];
			case "scienza":
				length = NOTIZIE_SCIENZA.length;
				randomIndex = ThreadLocalRandom.current().nextInt(length);
				return NOTIZIE_SCIENZA[randomIndex];
			case "sport":
				length = NOTIZIE_SPORT.length;
				randomIndex = ThreadLocalRandom.current().nextInt(length);
				return NOTIZIE_SPORT[randomIndex];
		}
		return content;
	}

	public void run(){
		System.out.printf("ThreadID: %d, ThreadName %s inizia a produttore notizie\n", Thread.currentThread().getId(), Thread.currentThread().getName());
		String content = "";
		Notizia n;
		for(int i  = 0; i < ProduttoreNot.MAX_NOTIZIE; i++){
			content = generateContent(tipoNotizia);
			n = new Notizia(tipoNotizia, content);
			notizie.add(n);
		}
		setDone(true);
		System.out.printf("ThreadID: %d, ThreadName %s ha finito di produrre notizie\n", Thread.currentThread().getId(), Thread.currentThread().getName());
	}

	public synchronized boolean getDone(){
		return this.done;
	}

	public synchronized void setDone(boolean _done){
		done = _done;
	}

	public String getProducerType(){
		return this.tipoNotizia;
	}

}