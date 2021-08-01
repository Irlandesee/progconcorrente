# esercizio 7
# problem description

Scrivere un programma in cui il main crea un thread che indefinitivamente scrive "ciao" sullo stdout ogni 100 ms
-> Il main legge iterativamente da stdin, quando legge <<fine>>
	manda un segnale al thread
-> Il thread reagisce al segnale scrivendo <<termino>> e terminando
-> Dopo la terminazione del thread il main termina a sua volta./