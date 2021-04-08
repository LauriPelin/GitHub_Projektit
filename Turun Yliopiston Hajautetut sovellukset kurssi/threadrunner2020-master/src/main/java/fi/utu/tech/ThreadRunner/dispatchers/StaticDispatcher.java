package fi.utu.tech.ThreadRunner.dispatchers;

import java.util.ArrayList;


import fi.utu.tech.ThreadRunner.tasks.Countable;
import fi.utu.tech.ThreadRunner.tasks.TaskFactory;
import fi.utu.tech.ThreadRunner.workers.Worker;
import fi.utu.tech.ThreadRunner.workers.WorkerFactory;

/*
 * Luokka, jossa toteutetaan staattinen tehtävien jako ts. työn tehtävä 1
 * 
* @version     1.0                 
* @since       1.0          
*/

public class StaticDispatcher implements Dispatcher {

	/**
	 * Metodi, jossa on toteutettu staattinen tehtäväjako toiminnallisuus.
	 *
	 *
	 * @param ControlSet Käyttöliittymässä asetettu arvot välittyvät tässä oliossa
	 * @return void
	 * 
	 */
	// LAURI PELIN YKSIN RYHMÄSTÄ 28, sposti lähetetty Jari Lehdolle. Sovittu palautuspäivä oli vuoden 2020 loppuun mennessä.
	// Ymmärtääkseni tehtävässä tulee tehdä oma extended Thread/Runnable, sitten luoda x määrä uusia säikeitä, jakaa niille tehtävämäärät/työlistat sekä worker olio. 
	// OG työlista pitää myös niputtaa osiin aiemmin mainituille säikeille.
	// Säiemäärän saan getThreadCount metodista, tehtävämäärän getAmountTasks metodista, tehtävätyypin getTaskType metodista ja workertyypin getWorkerType metodista.
	// Koodissa aika paljon Finglish ja molempia sekaisin, mutta olen yrittänyt lisätä ymmärrettävyyttä kommenteilla
	

	// Aloitan kopioimalla WithOutThreadsDispatcher luokasta rungon, sillä saan sieltä kätevästi apua pohjan luomiseksi. Jatkan muokkaamalla sitä tehtävässä vaaditun mukaiseksi
	public void dispatch(ControlSet controlSet) {
		try {
			Countable co = TaskFactory.createTask(controlSet.getTaskType()); // generoi työtyypin?
			ArrayList<Integer> ilist = co.generate(controlSet.getAmountTasks(), controlSet.getMaxTime()); // luodaan ilist missä tehtävämäärä ja aika;
			Worker worker = WorkerFactory.createWorker(controlSet.getWorkerType()); // luodaan worker joka saanut tyyppinsä getWorkerType metodista
			int säieMäärä = controlSet.getThreadCount(); // saadaan haluttu säiemäärä
			ArrayList<ArrayList<Integer>>työLista = new ArrayList<ArrayList<Integer>>(); // jaetaan työLista
			int työListaPituus = (int) Math.ceil(ilist.size() / (double)(säieMäärä)); // ceil palauttaa pienimmän mahdollisen double arvon listan maksimikoosta
			for (int x=0; x< ilist.size(); x = x + työListaPituus){ // luodaan työlistat säikeille
				työLista.add(new ArrayList<Integer>(ilist.subList(x, Math.min(x + työListaPituus, ilist.size())))); // pitkän kaavan mukainen listalisäys
			}
			ArrayList<Thread> säieLista = new ArrayList<Thread>(); // luodaan listat säikeille, käytännössä sama toteutus läpikäynnissä kuin dispatcher rungossa muutamalla lisäyksellä
			for (ArrayList<Integer> sLista : työLista) {
				HarkkaThread x = new HarkkaThread(worker, sLista);
				säieLista.add(x);
				x.start(); // ajetaan säie, lisätiedot HarkkaThread luokassa
			}
			for (Thread x : säieLista) { // täydennetty pyydetty toiminnallisuus 
				x.join(0);
			}
		}
		// Rungosta tuttu exception handlaaja
		catch (Exception ex) {
			ex.printStackTrace();
			}
		}
}

		
		


