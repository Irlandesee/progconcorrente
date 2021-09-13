# descrizione del sistema
+ Si desidera realizzare il sistema distribuito costituito da un programma server e 2 client. Il serer e ciascun client possono risiedere su macchine diverse, come in ogni sistema distribuito.

+ Il server consente ai client di giocare a Tris
+ I due client sono i giocatori. Il sistema deve consentire ai giocatori di giocare una partita.

+ una tipica sequenza di eventi gestita dal sistema è la seguente:

	+ I due giocatori si connettono.  L'ordine non è rilevante
	
	+ Uno dei 2 giocatori A crea una nuova partita. L'altro giocatore B ne riceve notifica. La prima mossa tocca al giocatore ceh non ha iniziato la partita B
	
	+ I giocatori A e B fanno le loro mosse alternativamente. Ovviamente il sistema non deve consentire allo stesso giocatore di fare 2 mosse consecutive.

	+ Ad ogni mossa di un giocatore, l'altro giocatore riceve notifica della mossa.

	+ Una mossa può comportare la terminazione della partita (vittoria di 1 dei giocatori, parità in caso non sia più possibile nessuna mossa). In tal caso, entrambi i giocatori ne ricevono notifica.

+ Qunado un giocatore fa una mossa, l'altro ha un certo tempo per muovere. Se il giocatore di turno non esegue una mosa valida entro il tempo stabilito, la partita si conclude con la sua sconfitta. In tal caso entrambi i giocatori ne ricevono notifica.

+ Il server gestisce una sola coppia di client alla volta.

+ Se un client si disconnette, o non risulta comunque raggiungibile, perde la partita.

+ NB: per quanto non specificato, si facciano ipotesi ragionevoli.

+ si realizzi il sistema con il meccanismo RMI o Socket che si preferisce.

+ Si realizzi il sistema in modo da gestire le eccezioni più comuni, conm particolare riferimento a quelle relative alle comunicazione remote.

+ Si realizzi il server in modo che visualizzi lo stato delle connessioni e della partita. I client devono essere forniti di un minimo di interfaccia utente che consenta di giocare la partita.

# consigli e suggerimenti

+ server, Registry e client devono essere processi distinti, in grado di girare su macchine distinte, quindi non possono essere thread di un unico processo.

+ Nel caso ci sia qualche elemento di importanza marginale non perfettamente specificato sopra, qualunque implementazione ragionevole va bene.

# nota importante

+ Viene fornito del codice contenente la logica del gioco del Tris e anche una rudimentale visualizzazione. Si consiglia di riusare il codice dato, concentrandosi sugli aspetti di distribuzione, comunicazione e sincronizzazione.

# cosa occorre consegnare

Bisogna obbligatoriamente consegnare il codice sorgente (file .java) e le indicazioni necessarie per compilare ed eseguire il programma.
Regole:

+ Non utilizzare librerie diverse da quelle standard di Java.

+ Non usare versioni non standard di Java. Considerate che per la correzione si usa openjdk 11, quindi fare in modo che il programma consegnato funzioni con tale versione di Java. NB: i programmi verranno compilati con javac (versione 11.0) e attivati direttamente da terminale. Il fatto che funzionino correttamente in qualche IDE è irrilevante agli effetti dell’esame.
+ Nel codice sorgente non utilizzare caratteri diversi da quelli ASCII. Ad es., non usare le lettere accentate. Pertanto, nelle stringhe e anche nei commenti, evitare accuratamente l’uso di lettere accentate.
+ Allegare istruzioni di compilazione ed esecuzione chiare e sintetiche. Si veda come esempio il file "readme" nella soluzione proposta per gli appelli del 9/6/20 e seguenti.
+ È severamente vietato mettere diverse classi nello stesso file (i file sono gratis: non c'è nessun bisogno di fare economia).
+ Tutti i programmi che contravverranno a queste regole saranno valutati negativamente (cioè prenderanno un voto insufficiente).

# come consegnare

+ Bisogna caricare un unico file .zip sul sito dell’e-learning nello spazio previsto per la risposta alla domanda. Il file deve avere un nome che comprende i cognomi di tutti i membri del gruppo di progetto.

+ La consegna va effettuata entro la data e ora di chiusura, inderogabilmente. Non verranno presi in considerazione progetti non consegnati come prescritto. Si consiglia pertanto di provvedere con un certo anticipo sulla scadenza, per evitare possibili problemi.

+ Tutti i membri di un team di progetto devono effettuare la consegna del medesimo file.

# criteri di valutazione

+ I programmi che presentano errori di compilazione (o che risultano altrimenti impossibili o difficili da compilare per mancata o cattiva documentazione) avranno una valutazione insufficiente.

+ I programmi che vanno in deadlock, in crash o che siano affetti da gravi problemi di altro tipo avranno una valutazione insufficiente.

+ I programmi inutilmente complessi verranno penalizzati. Le implementazioni semplici e pulite saranno premiate.

+ Pratiche di programmazione discutibili verranno penalizzate, anche se non strettamente
