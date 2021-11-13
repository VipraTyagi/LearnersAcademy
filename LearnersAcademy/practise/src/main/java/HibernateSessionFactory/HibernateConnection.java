package HibernateSessionFactory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {
	
	public static SessionFactory sessionfactory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	public static SessionFactory getSessionfactory()
	{
		return(sessionfactory);
	}

}
