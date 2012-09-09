package org.cloud4fun.myjobs.shared;

import java.io.Serializable;
import java.util.Date;



public class TaskDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -882345927726089029L;
	
	// database fields
	private Integer id;
	private String task;
	private Integer dur;
	private Boolean finished;
	private Date due;
	
	// supporting fields
	private Integer units_worked;
	

	
	public Integer getWorkedUnits() {
		return units_worked;
	}

	public void setWorkedUnits(Integer workedUnits) {
		this.units_worked = workedUnits;
	}

	public TaskDTO ()
	{
		
	}
	
	public TaskDTO (Integer id, String task, Integer dur, Boolean finished, Date due)
	{
		this.id = id;
		this.task = task;
		this.dur = dur;
		this.finished = finished;
		this.due = due;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Integer getDur() {
		return dur;
	}
	public void setDur(Integer dur) {
		this.dur = dur;
	}
	public Boolean getFinished() {
		return finished;
	}
	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
	public Date getDue() {
		return due;
	}
	public void setDue(Date due) {
		this.due = due;
	}
	
	

}
