# problem description
Diversi lettori e almeno uno scrittore a un insieme di dati condiviso
* i lettori si limitano a leggere i dati
* Gli scrittori modificano i dati
Si vuole che in ogni momento una sola di queste situazioni sia verificata:
* Uno scrittore sta accedendo ai dati: uno scrittore, nessun lettore
* Un insieme di lettori sta accedendo ai dati: nessuno scrittore, un numero qualunque di lettori
* Nessun Thread sta accedendo ai dati: nessun lettore e nessuno scrittore

-----