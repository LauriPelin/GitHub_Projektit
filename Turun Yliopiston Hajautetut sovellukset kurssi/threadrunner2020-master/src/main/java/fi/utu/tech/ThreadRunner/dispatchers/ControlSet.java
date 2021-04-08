package fi.utu.tech.ThreadRunner.dispatchers;

/*
 * Käyttöliittymästä saatavan kontrollitiedon mallintava luokka. Tämän luokan gettereitä käyttämällä tehtävässä saadaan 
 * käyttöliittymässä asetetut arvot.
 * 
 * DO NOT EDIT
 * 
* @author      Jari Lehto <jaanle@utu.fi>
* @version     1.0                 
* @since       1.0          
*/

public class ControlSet {

	private String taskType;
	private String workerType;
	private Integer maxtime;
	private Integer threadCount;
	private Integer amountTasks;

	public ControlSet() {
		this.taskType = "ConstantTask";
		this.workerType = "ForWorker";
		this.maxtime = Integer.valueOf(1);
	}

	public ControlSet(String type, String worker, Integer maxTime, Integer threadCount, Integer amountTasks) {
		this.taskType = type;
		this.workerType = worker;
		this.maxtime = maxTime;
		this.threadCount = threadCount;
		this.amountTasks = amountTasks;

	}

	/**
	 * Metodi, joka palauttaa valitun tehtävätyypin
	 *
	 * @return String task-luokan tyyppi
	 **/
	public String getTaskType() {
		return this.taskType;
	}

	/**
	 * Metodi, joka palauttaa valitun kuormatyypin
	 *
	 * @return String worker-luokan tyyppi
	 **/
	public String getWorkerType() {
		return this.workerType;
	}

	/**
	 * Metodi, joka palauttaa valitun kuoman
	 *
	 * @return Integer kuorman suuruus
	 **/
	public Integer getMaxTime() {
		return this.maxtime;
	}

	/**
	 * Metodi, joka palauttaa valitun säikeiden määrän
	 *
	 * @return Integer säikeiden määrä
	 **/
	public Integer getThreadCount() {
		return this.threadCount;
	}

	/**
	 * Metodi, joka palauttaa valitun tehtävien määrän
	 *
	 * @return Integer tehtävien määrä
	 **/
	public Integer getAmountTasks() {
		return this.amountTasks;
	}
}
