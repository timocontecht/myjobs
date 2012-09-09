package org.cloud4fun.myjobs.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.cloud4fun.myjobs.shared.ProjectDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.VerticalPanel;



public class ProjectsPanel extends VerticalPanel 
{
	public ProjectsPanel()
	{
		loadProjects();
		this.setWidth("20em");
		addProject_btn = new Button("Add New Project");
		add(addProject_btn);
		addProject_btn.addClickHandler(new AddProjectListener());
	}
	
	private void loadProjects()
	{
		projects = new ArrayList<ProjectDTO>();
		
		 if (service == null)
			 service = GWT.create(MyJobsService.class);
		 
		 AsyncCallback<List<ProjectDTO>> callback = new AsyncCallback<List<ProjectDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<ProjectDTO> result) {
				Iterator<ProjectDTO> it = result.iterator();
				while (it.hasNext())
				{
					ProjectDTO p = it.next();
					projects.add(p);
					ProjectCheckBox cb = new ProjectCheckBox(p.getProject());
					cb.setProject(p);
					project_cbs.add(cb);
					insert(cb ,0);
					cb.addClickHandler(new ProjectClickHandler());
				}
			}
			 		
		};
		
		service.getProjects(callback);
	}
	
	public class ProjectClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			List<ProjectDTO> selected = new ArrayList<ProjectDTO>();
			Iterator<ProjectCheckBox> it = project_cbs.iterator();
			while (it.hasNext())
			{
				ProjectCheckBox cb = it.next();
				if (cb.getValue())
					selected.add(cb.getProject());
			}
			
			TaskPanel tp = new TaskPanel(selected);
			MainDockPanel.getInstance().addTaskPanel(tp);
		}
		
	}
	public class AddProjectListener implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			// TODO: add a new project
			
			
		}
		
	}
	
	MyJobsServiceAsync service = null;
	List<ProjectDTO> projects = null;
	Button addProject_btn = null; 
	List<ProjectCheckBox> project_cbs = new ArrayList<ProjectCheckBox>();
	
	
}
