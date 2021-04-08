package fi.utu.tech.ThreadRunner.workers;

import java.util.concurrent.TimeUnit;

/*
 * Keinotekoinen kuorma, jolla tuotetaan määränajan kestävä työtehtävä säikeelle.
 * 
 * Tämä tehtävä toissijaisesti käytettävä kuorma.
 * 
 * Tämän kuorman ideana on demontroida sisäkkäisten silmukoiden kuormittavuutta. Sisin silmukka odottaa vain yhden
 * nanosekunnin, mutta kaksi sisäkkäistä silmukkaa helposti moninkertaistavat viiveen. Huom. iso-Ordo n^2
 * 
 * 
* @author      Jari Lehto <jaanle@utu.fi>
* @version     1.0                 
* @since       1.0          
*/

public class ForWorker implements Worker {

	/**
	 * Metodi, jota kutsutaan säikeessä.
	 *
	 *
	 * @param data Käytetään kasvattamaan viivettä.
	 * @return void
	 * 
	 */
	public void count(Integer data) throws Exception {
		for (int i = 0; i < data.intValue(); i++) {
			for (int j = 0; j < data.intValue(); j++) {
				TimeUnit.NANOSECONDS.sleep(1);
			}
		}
	}
}
