# desc
Server multithread con interfaccia proxy, lancia una thread per ogni connessione ricevuta

1. Il server genera un thread dedicato per ciascun client
2. Il client è dotato di proxy server
3. Per testare il sistema, il main client genera tanti thread client paralleli (ciascuno con il suo proxy)