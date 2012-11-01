package org.cloud4fun.myjobs.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4875770027288693300L;
	
	public ReportDTO()
	{
		projects = new ArrayList<String>();
		units = new ArrayList<Long>();
	}
	
	public ReportDTO(List queryRes)
	{
		projects = new ArrayList<String>();
		units = new ArrayList<Long>();
		
		for (int i=0; i<queryRes.size(); i++)
		{
			Object[] o = (Object[])queryRes.get(i);
			projects.add((String)o[0]);
			
			Long u;
			 if (o[1].getClass() == java.math.BigInteger.class)
				 u = ((java.math.BigInteger)o[1]).longValue();
			 else 
				 u = (Long)o[1];
			units.add(u);
		}
	}
	
	
	public ArrayList<String> getProjects() {
		return projects;
	}
	public void setProjects(ArrayList<String> projects) {
		this.projects = projects;
	}
	public ArrayList<Long> getUnits() {
		return units;
	}
	public void setUnits(ArrayList<Long> units) {
		this.units = units;
	}


	private ArrayList<String> projects;
	private ArrayList<Long> units;
	
	private Date start;
	private Date end;

	public enum ReportType
	{
		DAILY,
		WEEKLY,
		PERIOD
	}
}
