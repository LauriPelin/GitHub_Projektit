package fi.utu.tech.ThreadRunner.tasks;

/*
 * Factory-luokka, jota käyttämällä luodaan task-oliot
 * 
* @author      Jari Lehto <jaanle@utu.fi>
* @version     1.0                 
* @since       1.0          
*/

public class TaskFactory {

	/**
	 * Metodi, joka generoi työjonon
	 *
	 *
	 * @param String halutun task-olion tyyppi
	 * @return Countable palautuva task-olio
	 * 
	 */
	public static Countable createTask(String type) throws Exception {
		switch (type) {
		case "ConstantTask":
			return new ConstantTask();
		case "LinearTask":
			return new LinearTask();
		case "HalfAndHalf":
			return new HalfAndHalf();
		case "OneFourth":
			return new OneFourth();
		case "OneEigth":
			return new OneEigth();
		default:
			throw new Exception("Unknown task type " + type);
		}
	}

	/**
	 * Käyttöliittymään liittyvä metodi. Ei tarvita työn toteutuksessa.
	 *
	 */
	public static String[] getTaskTypes() {
		return new String[] { "ConstantTask", "LinearTask", "HalfAndHalf", "OneFourth", "OneEigth" };
	}
}
