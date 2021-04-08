package fi.utu.tech.ThreadRunner.UI;

import javafx.beans.property.SimpleStringProperty;
import fi.utu.tech.ThreadRunner.dispatchers.ControlSet;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

/*
 * Tulostaulukon yhden rivin data mallintava luokka. Näihin tutustutaan Käyttöliittymät-kurssilla.
 * 
* @author      Jari Lehto <jaanle@utu.fi>
* @version     1.0                 
* @since       1.0          
*/

public class StatisticLine {
	private final SimpleStringProperty taskType;
	private final SimpleStringProperty workerType;
	private final SimpleIntegerProperty amountTasks;
	private final SimpleIntegerProperty maxTime;
	private SimpleIntegerProperty threadCount;
	private final SimpleFloatProperty time;

	public StatisticLine(ControlSet controlSet, Float time) {
		this.taskType = new SimpleStringProperty(controlSet.getTaskType());
		this.workerType = new SimpleStringProperty(controlSet.getWorkerType());
		this.amountTasks = new SimpleIntegerProperty(controlSet.getAmountTasks());
		this.maxTime = new SimpleIntegerProperty(controlSet.getMaxTime());
		this.threadCount = new SimpleIntegerProperty(controlSet.getThreadCount());
		this.time = new SimpleFloatProperty(time);
	}

	public String getTaskType() {
		return taskType.get();
	}

	public void setTaskType(String tasktype) {
		taskType.set(tasktype);
	}

	public String getWorkerType() {
		return workerType.get();
	}

	public void setWorkerType(String workertype) {
		workerType.set(workertype);
	}

	public Integer getAmountTasks() {
		return amountTasks.get();
	}

	public void setAmountTasks(Integer amount) {
		amountTasks.set(amount);
	}

	public Integer getMaxTime() {
		return maxTime.get();
	}

	public void setMaxTime(Integer itime) {
		maxTime.set(itime);
	}

	public Integer getThreadCount() {
		return threadCount.get();
	}

	public void setThreadCount(Integer count) {
		threadCount.set(count);
	}

	public Float getTime() {
		return time.get();
	}

	public void setTime(Integer itime) {
		time.set(itime);
	}
}
