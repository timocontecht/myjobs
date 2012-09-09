package org.cloud4fun.myjobs.client;



import java.util.List;
import org.cloud4fun.myjobs.shared.ProjectDTO;
import org.cloud4fun.myjobs.shared.TaskDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ScrollPanel;


public class TaskPanel extends ScrollPanel {
	
	
	
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
		this.add(theGrid);
		theGrid.clear();
		theGrid.resize(row_size, 5	);
		
		
		// add header row
		theGrid.setText(0, 0,  "Task");
		theGrid.setText(0, 1, "Project");
		theGrid.setText(0, 2,  "Due Date");
		theGrid.setText(0, 3,  "Est. Duration");
		
		// add task rows
		for(int i=1; i<=tasks.size(); i++)
		{
			TaskDTO t = tasks.get(i-1);
			
			if (t.getFinished() == false)
			{
				theGrid.setText(i, 0, t.getTask());
				//TODO program project with tasks
				theGrid.setText(i, 1, "Project xx");
				if (t.getDue() != null)
					theGrid.setText(i, 2, DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).format(t.getDue()));
				else
					theGrid.setText(i, 2, "N/A");
				
				if (t.getDur() != null)
					theGrid.setText(i, 3, t.getDur().toString());
				else
					theGrid.setText(i, 3, "N/A");
				
				TaskBtnsComposite tc = new TaskBtnsComposite(t);
				theGrid.setWidget(i, 4, tc);
			}
		}
	}

	
	List<ProjectDTO> projects = null;
	List<TaskDTO> tasks = null;
	private static Grid theGrid = new Grid();
}
