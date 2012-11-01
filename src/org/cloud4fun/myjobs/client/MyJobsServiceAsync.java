package org.cloud4fun.myjobs.client;

import java.util.Date;
import java.util.List;

import org.cloud4fun.myjobs.shared.ProjectDTO;
import org.cloud4fun.myjobs.shared.ReportDTO;
import org.cloud4fun.myjobs.shared.TaskDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface MyJobsServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	 void getProjects(AsyncCallback<List<ProjectDTO>> callback) throws IllegalArgumentException;
	 void getTasks(List<ProjectDTO> projects, AsyncCallback<List<TaskDTO>> callback);
	 void addWorkUnit(TaskDTO task, AsyncCallback<String> callback) throws IllegalArgumentException;
	 
	// reports
	void getReport(ReportDTO.ReportType rt, AsyncCallback<ReportDTO> callback) throws IllegalArgumentException;
	void getPeriodReport(ReportDTO.ReportType rt, Date start, Date end, AsyncCallback<ReportDTO> callback) throws IllegalArgumentException;
	
}
