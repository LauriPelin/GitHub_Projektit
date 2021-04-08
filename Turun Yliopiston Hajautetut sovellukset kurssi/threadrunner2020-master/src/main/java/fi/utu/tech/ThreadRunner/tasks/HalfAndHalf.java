package fi.utu.tech.ThreadRunner.tasks;

import java.util.ArrayList;

/*
 * Luokka, joka generoi kaksijakoisen kuorman sisältävän työjonon. Tuloksena saatavan Arraylistin arvoista 
 * puolet ovat kuormaa kuvaavan muuttujan kokoisia ja puolet sen monikertoja
 * 
* @author      Jari Lehto <jaanle@utu.fi>
* @version     1.0                 
* @since       1.0          
*/

public class HalfAndHalf extends AbstractTask implements Countable {

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
		int half = size / 2;
		for (int i = 1; i < half; i++) {
			list.add(Integer.valueOf(maxtime));
		}
		for (int i = half; i <= size; i++) {
			list.add(Integer.valueOf(maxtime * multiplier));
		}
		return list;
	}
}
