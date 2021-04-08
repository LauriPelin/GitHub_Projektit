package fi.utu.tech.ThreadRunner.tasks;

import java.util.ArrayList;

/*
 * Luokka, joka generoi nelijakoisen kuorman sisältävän työjonon. Tuloksena saatavan Arraylistin arvoista 
 * kolme neljäsosaa on kuormaa kuvaavan muuttujan kokoisia ja yksi neljäsosa sen monikertoja
 * 
* @author      Jari Lehto <jaanle@utu.fi>
* @version     1.0                 
* @since       1.0          
*/

public class OneFourth extends AbstractTask implements Countable {

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
		int part = size / 4;
		for (int i = 1; i < part * 3; i++) {
			list.add(Integer.valueOf(maxtime));
		}
		for (int i = part * 3; i <= size; i++) {
			list.add(Integer.valueOf(maxtime * multiplier));
		}
		return list;
	}
}
