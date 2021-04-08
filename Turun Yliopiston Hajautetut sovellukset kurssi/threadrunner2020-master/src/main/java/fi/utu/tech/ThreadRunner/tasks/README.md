## Package fi.utu.tech.Threadrunner.tasks

Tämän pakkauksen luokat edustavat työssä käytettäviä työjonoja ts. tehtäviä, joita annetaan worker-olioiden suoritettavaksi. **Tämän pakkauksen tiedostoihin ei tule/tarvitse koskea tehtävien suorittamiseksi.**

 

Countable.java on rajapinta, jonka kaikki task-luokat toteuttavat. WorkerFactory on factory-luokka, 
jonka createTask(Integer amount, Integer time)-luokkaa kutsumalla luodaan uuden task-oliot.

## Käyttö

### Halutun Task-olion luonti

Countable w = TaskFactory.createTask(name of task as a string);

### Työjonon generointi

ArrayList<Integer> list = co.generate(controlSet.getAmountTasks() , controlSet.getMaxTime());