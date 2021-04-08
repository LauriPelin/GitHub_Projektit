package fi.utu.tech.ThreadRunner.tasks;

import java.util.ArrayList;

/*
 * Luokka, joka generoi kahdeksanjakoisen kuorman sisältävän työjonon. Tuloksena saatavan Arraylistin arvoista 
 * 7/8 on kuormaa kuvaavan muuttujan kokoisia ja 1/8 sen monikertoja
 * 
* @author      Jari Lehto <jaanle@utu.fi>
* @version     1.0                 
* @since       1.0          
*/

public class OneEigth extends AbstractTask implements Countable {

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
		int part = size / 8;
		for (int i = 1; i < part * 7; i++) {
			list.add(Integer.valueOf(maxtime));
		}
		for (int i = part * 7; i <= size; i++) {
			list.add(Integer.valueOf(maxtime * multiplier));
		}
		return list;
	}
}
