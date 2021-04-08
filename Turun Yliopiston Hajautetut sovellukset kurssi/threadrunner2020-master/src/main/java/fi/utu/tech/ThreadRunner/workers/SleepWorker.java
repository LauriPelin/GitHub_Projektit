package fi.utu.tech.ThreadRunner.workers;

import java.util.concurrent.TimeUnit;

/*
 * Keinotekoinen kuorma, jolla tuotetaan määränajan kestävä työtehtävä säikeelle.
 * 
 * Tämä tehtävä ensisijaisesti käytettävä kuorma.
 * 
* @author      Jari Lehto <jaanle@utu.fi>
* @version     1.0                 
* @since       1.0          
*/

public class SleepWorker implements Worker {

	/**
	 * Metodi, jota kutsutaan säikeessä.
	 *
	 *
	 * @param data Viive, jonka yksittäinen tehtävä odottaa.
	 * @return void
	 * 
	 */
	public void count(Integer data) throws Exception {
		TimeUnit.MILLISECONDS.sleep(data.intValue());
	}

}
