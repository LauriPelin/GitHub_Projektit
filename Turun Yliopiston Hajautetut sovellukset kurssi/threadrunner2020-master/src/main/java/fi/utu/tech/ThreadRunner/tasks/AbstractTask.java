package fi.utu.tech.ThreadRunner.tasks;

import java.util.ArrayList;

/*
 * Kaikille Task-luokille yhteinen abstraksi yläluokka. Luokkaan on kirjoitettuna ainoastaan oletustotetutus 
 * generate-metodille, joka ylikirjoitetaan varsinaisissa Task-luokissa.
 * 
* @author      Jari Lehto <jaanle@utu.fi>
* @version     1.0                 
* @since       1.0          
*/

public abstract class AbstractTask {

	protected int multiplier = 100;

	public ArrayList<Integer> generate(int size, int maxtime) {
		ArrayList<Integer> list = new ArrayList<Integer>(size);
		list.forEach(n -> list.add(n, Integer.valueOf(1)));
		return list;
	}

}
