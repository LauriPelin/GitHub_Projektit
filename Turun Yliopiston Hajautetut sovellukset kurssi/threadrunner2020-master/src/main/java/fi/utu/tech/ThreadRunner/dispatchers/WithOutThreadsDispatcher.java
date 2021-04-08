package fi.utu.tech.ThreadRunner.dispatchers;

/*
 * Ilman säikeiden luontia toteutettu esimerkki laskennasta. 
 * 
 * 
 * 
* @author      Jari Lehto <jaanle@utu.fi>
* @version     1.0                 
* @since       1.0          
*/

import java.util.ArrayList;
import fi.utu.tech.ThreadRunner.tasks.Countable;
import fi.utu.tech.ThreadRunner.tasks.TaskFactory;
import fi.utu.tech.ThreadRunner.workers.Worker;
import fi.utu.tech.ThreadRunner.workers.WorkerFactory;

public class WithOutThreadsDispatcher implements Dispatcher {

	/**
	 * Metodi, jossa on toteutettu laskennan toiminnallisuus.
	 *
	 *
	 * @param ControlSet Käyttöliittymässä asetettu arvot välittyvät tässä oliossa
	 * @return void
	 * 
	 */
	public void dispatch(ControlSet controlSet) {

		try {
			Countable co = TaskFactory.createTask(controlSet.getTaskType());
			ArrayList<Integer> ilist = co.generate(controlSet.getAmountTasks(), controlSet.getMaxTime());

			Worker worker = WorkerFactory.createWorker(controlSet.getWorkerType());

			for (int time : ilist) {
				worker.count(time);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
