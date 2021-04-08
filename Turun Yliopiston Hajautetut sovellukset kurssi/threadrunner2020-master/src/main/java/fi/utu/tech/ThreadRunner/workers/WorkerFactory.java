package fi.utu.tech.ThreadRunner.workers;

/*
 * Factory-luokka, jota käyttämällä luodaan worker-oliot
 * 
* @author      Jari Lehto <jaanle@utu.fi>
* @version     1.0                 
* @since       1.0          
*/

public class WorkerFactory {

	/**
	 * Metodi, joka generoi työjonon
	 *
	 *
	 * @param String halutun worker-olion tyyppi
	 * @return Countable palautuva worker-olio
	 * 
	 */
	public static Worker createWorker(String type) throws Exception {
		switch (type) {
		case "ForWorker":
			return new ForWorker();
		case "SleepWorker":
			return new SleepWorker();
		case "TestWorker":
			return TestWorker.getTestWorker();
		default:
			throw new Exception("Unknown worker type " + type);
		}
	}

	/**
	 * Käyttöliittymään liittyvä metodi. Ei tarvita työn toteutuksessa.
	 *
	 */
	public static String[] getWorkerTypes() {
		return new String[] { "ForWorker", "SleepWorker" };
	}
}
