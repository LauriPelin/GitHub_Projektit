package fi.utu.tech.ThreadRunner.tasks;

import java.util.ArrayList;

/*
 * Luokka, joka generoi vakioaikaisen kuorman sisältävän työjonon. Tuloksena saatavan Arraylistin arvot kaikki samoja.
 * 
* @author      Jari Lehto <jaanle@utu.fi>
* @version     1.0                 
* @since       1.0          
*/

public class ConstantTask extends AbstractTask implements Countable {

	/**
	 * Metodi, joka generoi työjonon
	 *
	 *
	 * @param size    tehtävien määrä
	 * @param maxtime kuormaa kuvaava muuttuja
	 * @return ArrayList<Integer> työjono
	 * 
	 */
	public ArrayList<Integer> generate(int size, int maxtime) {
		ArrayList<Integer> list = new ArrayList<Integer>(size);
		for (int i = 1; i <= size; i++) {
			list.add(Integer.valueOf(maxtime));
		}
		return list;
	}

}
