package org.cloud4fun.myjobs.client;



import java.util.List;
import org.cloud4fun.myjobs.shared.ProjectDTO;
import org.cloud4fun.myjobs.shared.TaskDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;


public class TaskPanel extends Grid {
	
	public TaskPanel(List<ProjectDTO> projects)
	{
		this.projects = projects;
		MyJobsServiceAsync service = null;
		
		// load the tasks from the database
		if (service == null)
			 service = GWT.create(MyJobsService.class);
		 
		 AsyncCallback<List<TaskDTO>> callback = new AsyncCallback<List<TaskDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<TaskDTO> result) {
				tasks = result;
				buildGrid();
			}
			 		
		};
		if (projects.size() > 0)
			service.getTasks(projects, callback);
		
	}
	
	private void buildGrid()
	{
		int row_size = tasks.size() + 1;
		this.clear();
		this.resize(row_size, 5	);
		
		// add header row
		this.setText(0, 0,  "Task");
		this.setText(0, 1, "Project");
		this.setText(0, 2,  "Due Date");
		this.setText(0, 3,  "Est. Duration");
		
		// add task rows
		for(int i=1; i<=tasks.size(); i++)
		{
			TaskDTO t = tasks.get(i-1);
			
			this.setText(i, 0, t.getTask());
			//TODO program project with tasks
			this.setText(i, 1, "Project xx");
			if (t.getDue() != null)
				this.setText(i, 2, DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).format(t.getDue()));
			else
				this.setText(i, 2, "N/A");
			
			if (t.getDur() != null)
				this.setText(i, 3, t.getDur().toString());
			else
				this.setText(i, 3, "N/A");
			
			TaskBtnsComposite tc = new TaskBtnsComposite(t);
			this.setWidget(i, 4, tc);
		}
	}

	
	List<ProjectDTO> projects = null;
	List<TaskDTO> tasks = null;
}
