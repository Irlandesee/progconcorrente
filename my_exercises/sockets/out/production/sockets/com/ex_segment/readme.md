# Esercizio 1
* Si vuole creare un server che sia in grado di accettare connessioni via socket e che implementi le seguenti richieste da parte dei client:
    1. creazione di segmento:
       1. In questo caso un client scrive sul socket il comando "NewSegment" seguito da 2 oggetti di classe POint che rappresentano gli estremi del segmento
       2. Il server risponde "OK" o "KO" a seconda che l'operazione sia riuscita o meno
    2. trovare il punto simmeetrico, rispetto al segmento, di un punto dato.
       1. Il client scrive sul socket il comando "Simmetrico" seguito dall'oggetto di classe Point di cui si vuole trovare il simmetrico
       2. Il server risponde scrivendo sul socket l'oggetto di classe Point richiesto

* Si implementi il server descritto
* Si vuole che molti client possano accedere contemporaneamente al server, ma ciascuno per creare e usare un proprio segmento (diverso da quello degli altri client).