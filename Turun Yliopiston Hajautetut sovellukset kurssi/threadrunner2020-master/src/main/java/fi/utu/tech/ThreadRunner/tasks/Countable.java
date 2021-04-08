package fi.utu.tech.ThreadRunner.tasks;

/*
 * Rajapintaluokka erityyppisten Task-luokkien käsittelyyn.
 * 
* @author      Jari Lehto <jaanle@utu.fi>
* @version     1.0                 
* @since       1.0          
*/

import java.util.ArrayList;

public interface Countable {

	public ArrayList<Integer> generate(int size, int maxtime);

}
