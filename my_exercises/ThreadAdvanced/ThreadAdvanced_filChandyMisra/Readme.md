# Algorithm Description
1. Soluzione adatta ad un numero qualunque di processori e risorse
2. Non richiede elementro Centrale(waiter)
3. Richiede che i filosofi si parlino (cosa esclusa nella formulazione originale del problema)

# Rules
1. Per ogni coppia di filosfi che si contende la risorsa, crea un bastoncino e dallo al filosofo con l'id più basso: Ogni filosofo avrà esattamente un bastoncino
2. I bastoncini possono essere sporchi o puliti. Inizialmente sono tutti sporchi.
3. Quando un filosofo vuole mangiare, deve ottenere le risorse che gli mancano dai vicini. A questo scopo deve mandare una richiesta esplicita.
4. Quando un filosofo che detiene un bastoncino riceve una richiesta, la ignora se il bastoncino è pulito. Se invece è sporco, lo pulisce e lo cede.
5. Quando un filosofo finisce di mangiare, i suoi bastoncini sono sporchi. Se un altro filosofo aveva richiesto un bastoncino, il filosofo che ha finito di mangiare lo pulisce e glielo cede.


# Osservazioni
- Permette un grado elevato di concorrenza ed è applicabile a problemi grandi a piacere.
- Risolve il problema della starvation. Lo stato sporco/ pulite favorisce i processi più affamati e sfavorisce quelli che hanno appena mangiato (che devono cedere i loro bastoncini sporchi).
- E' dimostrato che le attese circolari non sono disponibili, 
a meno che questa non sia crata all'inizio: se inizialmente tutti i filosofi hanno un bastoncino pulito, il sistema è in deadlock: Inizializzare il sistema in modo che i filosofi abbiano bastoncini sporchi assicura la mancanza di attesa ciclicla.

