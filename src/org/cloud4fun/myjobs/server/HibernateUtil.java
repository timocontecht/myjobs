package org.cloud4fun.myjobs.server;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistryBuilder;
//import org.hibernate.service.ServiceRegistry;


public class HibernateUtil 
{
	private static final SessionFactory sessionFactory;
	//private static ServiceRegistry serviceRegistry;
	
	static
	{
		try
		{
			sessionFactory = new Configuration().configure().buildSessionFactory();;
		}
		catch (Throwable ex)
		{
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
	}

	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}
