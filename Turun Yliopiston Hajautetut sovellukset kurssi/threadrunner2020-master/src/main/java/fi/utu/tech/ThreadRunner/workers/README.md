## Package fi.utu.tech.Threadrunner.workers

Tämän pakkauksen luokat edustavat työssä käytettäviä kuormia ts. töitä, joita säikeet suorittavat. **Tämän pakkauksen tiedostoihin ei tule/tarvitse koskea tehtävien suorittamiseksi.**

Worker.java on rajapinta, jonka kaikki worker-luokat toteuttavat. WorkerFactory on factory-luokka, 
jonka createWorker(String type)-luokkaa kutsumalla luodaan uuden worker-oliot.

## Käyttö


#### Uuden worker-olion luonti

Worker w = WorkerFactory.createWorker(workerType as a string);

#### Laskennan suoritus

w.count(data as Integer);