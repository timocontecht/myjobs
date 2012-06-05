package org.cloud4fun.myjobs.client;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.cloud4fun.myjobs.client.ProjectsPanel.ProjectClickHandler;
import org.cloud4fun.myjobs.shared.ProjectDTO;
import org.cloud4fun.myjobs.shared.TaskDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TaskBtnsComposite extends Composite {
	
	TaskDTO task = null;
	Button workUnit_btn;
	Button addUnit_btn;
	
	MyJobsServiceAsync service = null;
	
	public TaskBtnsComposite(TaskDTO task)
	{
		this.task = task;
		
		HorizontalPanel p = new HorizontalPanel();
		
		workUnit_btn = new Button("Work a Unit");
		addUnit_btn = new Button("Add a worked Unit");
		
		workUnit_btn.addClickHandler(new clickHandlerWork());
		addUnit_btn.addClickHandler(new clickHandlerAdd());
		
		p.add(workUnit_btn);
		p.add(addUnit_btn);
		
		initWidget(p);
	}

	public class clickHandlerWork implements ClickHandler
	{
		
		@Override
		public void onClick(ClickEvent event) {
			TimerDlg dlg = new TimerDlg(task);
			dlg.show();
		}
	}
	
	public class clickHandlerAdd implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			 if (service == null)
				 service = GWT.create(MyJobsService.class);
			 
			AsyncCallback<String> callback = new AsyncCallback<String>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(String result) {
				
					Window.alert(result);
				}
				
			};
			
			service.addWorkUnit(task, callback);
			
		}
		
	}
	

	
}
