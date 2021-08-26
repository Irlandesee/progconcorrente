# server multi thread
Un tipico server deve poter gestire diversi client in parallelo

1. si crea un unico ServerSocket nel server
2. Si attende una nuova connessione attraverso accept()
3. Quando accept() ritorna un Socket si crea un nuovo thread il cui compito è servire unicamente quel particolare client
-> Si passa al nuovo thread il socket connesso al client
-> Quando il thread ha finnito di servire il suo client termina

4. Appena creat il thread ci si mette di nuovo in attesa di un nuovo client con accept() -> Si torna al punto 2.

 