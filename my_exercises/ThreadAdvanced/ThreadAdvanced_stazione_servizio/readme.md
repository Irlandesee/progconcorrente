# Problem description

# Stazione di Servizio
- Si vuole modellare una stazione di servizio per calcolare il tempo medio necessario ad un automobilista per ottenere il carburante. 
- Il tempo viene calcolato da quando il coducente comincia l'attesa ad una pompa di distribuzione e termina quando l'automobile lascia la stazione di servizio.
- La simulazione inizia con 50 vetture in arrivo alla stazione di servizio in modo casuale con ritardi che vanno da 0 a 30 UT (UT -> unita di tempo).

--- 
# Comportamento dei conducenti

- il conducente verifica se la pompa è libera
	* Se non è libera, aspetta
	* Se è libera avvicina la macchina fino alla pompa, spegne il motore,
		scende, seleziona il carburante desiderato e inserisce l'erogatore.
		Queste operazioni richiedono 1 UT.
- Riempie il serbatoio
	* Questa operazione richiede un tempo compreso tra 1 e 3 UT (dipende 		dalla) quantità di carburante prelevata.
- Ripone l'erogatore, sale in macchina e si allontana dalla pompa lasciandola libera.
	* Questa operazione richiede 1 UT.

# misc
-> una volta che la pompa si libera, resta occupata per un tempo complessivo compreso tra 3 e 5 UT.
