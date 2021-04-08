package fi.utu.tech.ThreadRunner.dispatchers;

/*
 * Rajapintaluokka erityyppisten Dispatcher-luokkien käsittelyyn.
 * 
 * DO NOT EDIT
 * 
* @author      Jari Lehto <jaanle@utu.fi>
* @version     1.0                 
* @since       1.0          
*/

public interface Dispatcher {

	public void dispatch(ControlSet controlSet);

}
