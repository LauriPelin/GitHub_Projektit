package fi.utu.tech.ThreadRunner.dispatchers;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import fi.utu.tech.ThreadRunner.tasks.Countable;
import fi.utu.tech.ThreadRunner.tasks.TaskFactory;
import fi.utu.tech.ThreadRunner.workers.Worker;
import fi.utu.tech.ThreadRunner.workers.WorkerFactory;

/*
 * Luokka, jossa toteutetaan dynaaminen tehtävien jako ts. työn tehtävä 2
 * 
* @version     1.0                 
* @since       1.0          
*/

public class DynamicDispatcher implements Dispatcher {

	/**
	 * Metodi, jossa on toteutetaan dynaamisen tehtäväjaon toiminnallisuus.
	 *
	 *
	 * @param ControlSet Käyttöliittymässä asetettu arvot välittyvät tässä oliossa
	 * @return void
	 * 
	 */
	// LAURI PELIN YKSIN RYHMÄSTÄ 28, sposti lähetetty Jari Lehdolle.
	// 1) osion tehtyäni voin jälleen kierrättää tuttua runkoa tähänkin tehtävään.
	// Dynaamisessa tehtävänjaossa tarvitaan ymmärtääkseni apukeinoa listoihin, esim. executorservice?
	// Jaettava kaikille säikeille "riittävästi", eli nopeat eivät odota hitaita. Työlistoja oltava n.10x enemmän kuin säikeitä??
	// WithOutThreads+StaticDisp+DynamicDisp suoritusajoissa heittelyitä, jokin saattaa vielä vaatia hiomista mutta palautusaika ei riitä siihen enää
	
	//Aloitetaan kopioimalla runko StaticDispatcherista
	public void dispatch(ControlSet controlSet) {
		try {
			Countable co = TaskFactory.createTask(controlSet.getTaskType()); // generoi työtyypin?
			ArrayList<Integer> ilist = co.generate(controlSet.getAmountTasks(), controlSet.getMaxTime()); // luodaan ilist missä tehtävämäärä ja aika;
			Worker worker = WorkerFactory.createWorker(controlSet.getWorkerType()); // luodaan worker joka saanut tyyppinsä getWorkerType metodista
			int säieMäärä = controlSet.getThreadCount(); // saadaan haluttu säiemäärä
			ArrayList<ArrayList<Integer>>työLista = new ArrayList<ArrayList<Integer>>(); // jaetaan työLista
			int työListaPituus = (int) Math.ceil(ilist.size() / (double)(säieMäärä * 10)); // työListoja oltava n.10x enemmän kuin säikeitä!!!!
			for (int x=0; x< ilist.size(); x= x + työListaPituus){ // luodaan työlistat säikeille
				työLista.add(new ArrayList<Integer>(ilist.subList(x, Math.min(x + työListaPituus, ilist.size())))); // pitkän kaavan mukainen listalisäys
			}
			// Tästä eteenpäin suoritettava executorservice avulla?
			ExecutorService vaderinavaruusalus = Executors.newFixedThreadPool(säieMäärä); // annetaan executorservice säieMäärää vastaavalle FixedThreadPool, tästä eteenpäin luotava uusi luokka dynaamiselle säikeelle HarkkaDynaThread
			for (ArrayList<Integer>sLista : työLista) { // käytännössä ei mitään uutta 1) osion toteutukseen
				HarkkaDynaThread x = new HarkkaDynaThread(worker, sLista);
				vaderinavaruusalus.execute(x); // executorservicessä omat callit
			}
			// Nyt vielä executorservice pysäyttävä komento, tai se jää pyörimään turhaan https://www.baeldung.com/java-executor-service-tutorial mukaan
			vaderinavaruusalus.shutdown();
			if (!vaderinavaruusalus.awaitTermination(900, TimeUnit.MILLISECONDS)) {
				vaderinavaruusalus.shutdownNow();
			}
		}
			//ArrayList<Thread> säieLista = new ArrayList<Thread>(); // luodaan listat säikeille, käytännössä sama toteutus läpikäynnissä kuin dispatcher rungossa muutamalla lisäyksellä
			//for (ArrayList<Integer> sLista : työLista) {
			//	HarkkaThread x = new HarkkaThread(worker, sLista);
			//	säieLista.add(x);
			//	x.start(); // ajetaan säie, lisätiedot HarkkaThread luokassa
			//}
		//}
		//
		// Rungosta tuttu exception handlaaja
		catch (Exception ex) {
			ex.printStackTrace();
			}
		}
}