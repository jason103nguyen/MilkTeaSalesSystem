package fa.training.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	static {
		Configuration cfg = new Configuration();
		cfg.configure();
		if (sessionFactory == null) {
			sessionFactory = cfg.buildSessionFactory();
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		HibernateUtil.sessionFactory = sessionFactory;
	}
	
	static {
		refreshNewSessionFactory();
	}
	
	public static void refreshNewSessionFactory() {
		Configuration cfg = new Configuration();
		cfg.configure();
		sessionFactory = cfg.buildSessionFactory();
	}
	
}
