package fi.utu.tech.ThreadRunner.workers;

/*
 * Rajapintaluokka erityyppisten Worker-luokkien käsittelyyn.
 * 
* @author      Jari Lehto <jaanle@utu.fi>
* @version     1.0                 
* @since       1.0          
*/

public interface Worker {

	public void count(Integer data) throws Exception;
}
