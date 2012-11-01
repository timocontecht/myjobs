package org.cloud4fun.myjobs.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.cloud4fun.myjobs.client.MyJobsService;
import org.cloud4fun.myjobs.server.hibernate.Project;
import org.cloud4fun.myjobs.server.hibernate.Task;
import org.cloud4fun.myjobs.server.hibernate.WorkUnit;
import org.cloud4fun.myjobs.shared.FieldVerifier;
import org.cloud4fun.myjobs.shared.ProjectDTO;
import org.cloud4fun.myjobs.shared.ReportDTO;
import org.cloud4fun.myjobs.shared.ReportDTO.ReportType;
import org.cloud4fun.myjobs.shared.TaskDTO;
import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MyJobsServiceImpl extends RemoteServiceServlet implements
		MyJobsService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
	
	public List<Task> getDBTasks()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Task> result = session.createQuery("from Task").list();
		session.getTransaction().commit();
		return result;
	}
	
	public List<Project> getDBProjects()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Project> result = session.createQuery("from Project").list();
		session.getTransaction().commit();
		return result;
	}

	@Override
	public List<ProjectDTO> getProjects() throws IllegalArgumentException 
	{
		List<Project> projects_db = getDBProjects();
		List<ProjectDTO> projects_dto = new ArrayList<ProjectDTO>();
		
		Iterator<Project> it = projects_db.iterator();
		while(it.hasNext())
		{
			projects_dto.add(createProjectDTO(it.next()));
		}

		return projects_dto;
	}
	
	public ProjectDTO createProjectDTO(Project project)
	{
		return new ProjectDTO(project.getId(), project.getProject());
	}
	
	public TaskDTO createTaskDTO(Task task)
	{
		return new TaskDTO(task.getId(), task.getTask(), task.getDur(), task.getFinished(), task.getDue());
	}

	@Override
	public List<TaskDTO> getTasks(List<ProjectDTO> projects)
			throws IllegalArgumentException {
		
		if (projects.size() == 0)
			return null;
		
		// result list
		List<TaskDTO> result = new ArrayList<TaskDTO>();
		
		// create the id parameter list of project ids
		ArrayList<Integer> project_ids = new ArrayList<Integer>();
		Iterator<ProjectDTO> it = projects.iterator();
		while (it.hasNext())
			project_ids.add(it.next().getId());
		
		// hibernate query string
		String hql = "select distinct a from Task a " +
                "join a.projects t " +
                "where t.id in (:ids)";
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery(hql);
		query.setParameterList("ids", project_ids);
		List<Task> tasks = query.list();
		
		session.getTransaction().commit();
		
		Iterator<Task> taskIt = tasks.iterator();
		while (taskIt.hasNext()) 
			result.add(createTaskDTO(taskIt.next()));
		return result;
		
	}

	@Override
	public String addWorkUnit(TaskDTO task) throws IllegalArgumentException {
		
		WorkUnit wu = new WorkUnit();
		wu.setTaskId(task.getId());
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.persist(wu);
		
		session.getTransaction().commit();// TODO Auto-generated method stub
		return "Added a work unit for " + task.getTask();
	}

	
	@Override
	public ReportDTO getReport(ReportDTO.ReportType rt) throws IllegalArgumentException {
		
		
		return dailySummaryDB(rt);
		
		
	}
	  
	
	public ReportDTO dailySummaryDB(ReportDTO.ReportType rt) 
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		/* select PROJECT.project, COUNT(*) 
		 * from PROJECT, TASK_PROJECT_REL, WORK_UNIT 
		 * where WORK_UNIT.task_id = TASK_PROJECT_REL.task_id 
		 * 		AND TASK_PROJECT_REL .project_id = PROJECT.id 
		 * 		AND DATE(WORK_UNIT.worked_on) = DATE(NOW()) 
		 * 		GROUP BY PROJECT.id;
		 */
		Query q;  
		
		if (rt == ReportDTO.ReportType.DAILY)
		{
			q= session.createQuery("select p.project, count(*) from Project p, TaskProjectRel tp, WorkUnit wu " +
					"where wu.taskId = tp.taskId " +
					"AND tp.projectId = p.id " +
					"AND DATE(wu.workedOn) = DATE(NOW()) " +
					"GROUP BY p.id");
		}
		else
		{
			q = session.createSQLQuery("select PROJECT.project, COUNT(*)  FROM PROJECT, TASK_PROJECT_REL, WORK_UNIT " +
					"where WORK_UNIT.task_id = TASK_PROJECT_REL.task_id " +
					"AND TASK_PROJECT_REL.project_id = PROJECT.id " +
					"AND WORK_UNIT.worked_on between date_sub(CURDATE(), interval 1 week) and now() " +
					"GROUP BY PROJECT.id;");
		}
		
		List result = q.list();
		session.getTransaction().commit();
		
		return new ReportDTO(result);
	}

	@Override
	public ReportDTO getPeriodReport(ReportType rt, Date start, Date end)
			throws IllegalArgumentException 
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		/* select PROJECT.project, COUNT(*) 
		 * from PROJECT, TASK_PROJECT_REL, WORK_UNIT 
		 * where WORK_UNIT.task_id = TASK_PROJECT_REL.task_id 
		 * 		AND TASK_PROJECT_REL .project_id = PROJECT.id 
		 * 		AND DATE(WORK_UNIT.worked_on) = DATE(NOW()) 
		 * 		GROUP BY PROJECT.id;
		 */
		Query q;  
		
		q = session.createSQLQuery("select PROJECT.project, COUNT(*)  FROM PROJECT, TASK_PROJECT_REL, WORK_UNIT " +
				"where WORK_UNIT.task_id = TASK_PROJECT_REL.task_id " +
				"AND TASK_PROJECT_REL.project_id = PROJECT.id " +
				"AND WORK_UNIT.worked_on between ? and ?" +
				"GROUP BY PROJECT.id;")
				.setParameter(0, start)
				.setParameter(1, end);
		
		
		List result = q.list();
		session.getTransaction().commit();
		
		return new ReportDTO(result);
		
	}

	
	
}
