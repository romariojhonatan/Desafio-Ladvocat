package control;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import dao.ProcessoDao;
import model.Processo;

@SuppressWarnings("deprecation")
public class ProcessoController {

	private SessionFactory sessions = new AnnotationConfiguration().configure().buildSessionFactory();
	
	private ProcessoDao dao;
	
	@SuppressWarnings("finally")
	public boolean salvarProcesso(Processo processo) {
		Session session = sessions.openSession();
		dao = new ProcessoDao(session);
		try {
			dao.adiciona(processo);
		} catch ( HibernateException e ) {
			return false;
		} finally {
			session.close();
			dao = null;
			return true;
		}
	}
}
