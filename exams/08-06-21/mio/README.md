# descrizione sistema

+ Si desidera realizzare un sistema distribuito che gestisce la distribuzione di notizie.

+ Ogni notizia ha un "tipo notizia": politica, attualità, scienza, sport, e un contenuto, che per semplicità consiste in una stringa.

Il sistema è costituito dai seguenti elementi.

* Un insieme di programmi di tipo FruitoreNotizie
* Un programma Pubblicatore

---
# Programma pubblicatore

+ Il programma pubblicatore è provvisto di un insieme di thread ProduttoreNotizie in grado di produrre notizie. Le notizie sono memorizzate nel Pubblicatore, che si occupa della loro distribuzione.

# Programma fruitore

+ Ogni programma FruitoreNotizie è in grado di ricevere notizie dal Pubblicatore. A questo scopo ogni FruitoreNotizie informa il Pubblicatore del tipo di notizie che gli devono essere comunicate. I programmi FruitoreNotizie non prendono mai l'iniziativa di leggere le notizie: aspettano che vengano loro comunicate dal Pubblicatore

# Programma pubblicatore

+ Riceve dai programmi FruitoreNotizie richieste del tipo:
	*un certo FruitoreNotizie è interrassato a ricevere notizie di un dato tipo T (se un fruitoreNotizie è interressato a più di un tipo di notizie, dovrà comunicare ciascun tipo separatamente al pubblicatore).* 

	*Un certo fruitoreNotizie non è più interessato a ricevere notizie di tipo T (se un fruitoreNotizie non è più interessato a diversi tipi di notizie, dovrà comunicare ciascun tipo separatamente al Pubblicatore).*

+ Il pubblicatore e i vari programmi FruitoreNotizie sono processi che girano su macchine potenzialemnte diverse.
+ IN ogni momento esiste una unica notizia per ogni tipo.
+ Ciascun ProduttoreNotizie esegue iterativamente la seguente sequenza di operazioni:
	*Decide casualmente il tipo T della notizia a cui contribuire
	*Produce In modo casuale una Stringa X
	*Concatena X al contenuto della notizia di tipo X*

+ Il pubblicatore esegue ogni P secondi le seguenti operazioni, per ogni tipo T di notizia:
	*comunica la notizia di tipo T a tutti i FruitoreNotizie interessati a quel tipo di notizia
	*Cancella il contenuto della notizia di tipo T, in modo che il prossimo contributo da un ProdottuoreNotizie verrà aggiunto alla stringa vuota*

# note

+ Per il testing del sistema si ponga P = 5 secondi => Di conseguenza bisogna che i ProduttoreNotizie aggiornitno la notizia ocn un periodo ben inferiore a 5 secondi.

+ Si realizzi il sistema in modo da gestire le eccezioni più comuni. Ad esempio: 
	*Se un fruitoreNotizie diventa irraggiungibile(disconnessione), il Pubblicatore deve continuare a gunzionare regolarmente, anche se non riesce  a mandargli più notizie.*

+ Per la realizzazione del sistema si può utilizzare RMI o Socket
 