# definizione del problema
1. Si vuole modellare una stazione di servizio per calcolare il tempo medio di rifornimento
2. il tempo viene calcolato da quando il conducente comincia l'attesa ad'una pompa di distribuzione e termina quando l'automobile lascia la stazione di servizio
3. La simulazione inizia con 50 vetture in arrivo alla stazione di servizio in modo casuale con ritardi che vanno da 0 a 30 UT 

# comportamento del conducente
1. il conducente verifica se la pompa è libera
   1. se non è libera aspetta
   2. se è libera avvicina la macchina fino alla pompa, spegne il motore, seleziona il carburante desiderato e inserisce l'erogatore. (tutte queste operazioni richiedono 1UT)
2. Riempie il serbatoio
   1. Questa operazione richiede un tempo compreso tra 1 e 3 UT(dipende da quanto carburante si preleva)
3. Ripone l'erogatore, sale in macchina e si allontana dalla pompa, lasciandola libera. 
   Quest'operazione richiede 1 UT.

# simulazione
-> Sapere cosa esattamente fa il conducente è irrilevante
-> quello che interessa è che una volta che la pompa si libera, resta occupata per un tempo complessivo tra 3 e 5 UT.