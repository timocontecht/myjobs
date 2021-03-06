package org.cloud4fun.myjobs.client;

import java.util.Date;
import java.util.List;

import org.cloud4fun.myjobs.shared.ProjectDTO;
import org.cloud4fun.myjobs.shared.ReportDTO;
import org.cloud4fun.myjobs.shared.TaskDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface MyJobsService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	List<ProjectDTO> getProjects() throws IllegalArgumentException;
	List<TaskDTO> getTasks(List<ProjectDTO> projects) throws IllegalArgumentException;
	String addWorkUnit(TaskDTO task) throws IllegalArgumentException;
	
	String addTask(String taskname, List<ProjectDTO> projects);
	
	// reports
	ReportDTO getReport(ReportDTO.ReportType rt) throws IllegalArgumentException;
	ReportDTO getPeriodReport(ReportDTO.ReportType rt, Date start, Date end) throws IllegalArgumentException;
	
}
