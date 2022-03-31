# descrizione del sistema da realizzare

Si desidera realizzare un gioco online. Il sistema e' costituito da un unico server e da un client per ogni giocatore.
Il server e ciascun client risiedono generalmente su macchine diverse.

Il server consente ai client di giocare a un gioco le cui regole sono le seguenti:

* Inizialmente (prima che il server abbia accettato la connessione di un client), la situazione del gioco e' causale
* Ciascun giocatore puo' fare la sua mossa in qualunque momento (non ci sono turni da rispettare): la mossa viene comunicata al server
* Quando il server riceve una mossa da un giocatore, la valuta in base alla situazione, dopo di che puo' accettarla o rifiutarla
  * Se una mossa e' rifiutata, il giocatore che ha fatto la mossa riceve una notifica negativa.
  * Se una mossa e' accettata, il giocatore che ha fatto la mossa riceve una notifica positiva. Inoltre, il server aggiorna 
    la situazione e comunica la mossa a tutti gli altri giocatori. Una mossa accettata puo' comportare un bonus per il giocatore 
    che l'ha effettuata: in tal caso la notifica positiva contiene anche il bonus.
* Il gioco non termina mai: Se l'ultimo giocatore collegato si scollega, la situazione resta invariata, e il primo giocatore che 
    a connettersi si trovera' in tale situazione.

La gestione di una mossa e' atomica: la sequenza comprendente valutazione della mossa, aggiornamento situazione di gioco
e notifiche non e' interrompibile

Ciascun giocatore deve inizialmente connettersi al server, dopo di che fa una sequenza di mosse lunga a piacere, infine si
disconnette. La disconnessione e' opzionale: un utente puo' restare connesso indefinitamente.

La situazione del gioco sia rappresentata da una coppia di interi. Una mossa contiene due interi e una stringa.
Non sono di interesse ai fini dell'esame, possono quindi essere implementati in modo casuale:

* La situazione iniziale del gioco
* Le condizioni in cui una mossa viene respinta o accettata.
* Le condizioni in cui una mossa accettata genera un bonus. 
* L'aggiornamento della situazione di gioco in base alle mosse effettuate (Non ci interessa implementare un vero gioco, 
ma solo i meccanismi di comunicazione necessari)

Si realizzi il sistema utilizzando i socket. Le soluzioni basate su RMI non saranno prese in considerazione.

Si realizzi il sistema in modo da gestire le eccezioni piu' comuni, con particolare riferimento a quelle relative alle
comunicazione remote. 

Si realizzi il server in modo che visualizzi lo stato delle connessioni e le mosse effettuate