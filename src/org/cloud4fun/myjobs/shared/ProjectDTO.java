package org.cloud4fun.myjobs.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.cloud4fun.myjobs.server.hibernate.Project;

public class ProjectDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4410175317034511791L;
	
	private Integer id;
	private String project;
	
	public ProjectDTO()
	{
		
	}
	
	public ProjectDTO(Integer id, String name)
	{
		this.id = id;
		this.project = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
	
}
