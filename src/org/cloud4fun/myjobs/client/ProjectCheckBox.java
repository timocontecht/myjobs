package org.cloud4fun.myjobs.client;

import org.cloud4fun.myjobs.shared.ProjectDTO;
import com.google.gwt.user.client.ui.CheckBox;

public class ProjectCheckBox extends CheckBox
{
	private ProjectDTO project;

	public ProjectCheckBox(String label)
	{
		super(label);
	}
	public ProjectDTO getProject() {
		return project;
	}

	public void setProject(ProjectDTO project) {
		this.project = project;
	}
	
}
