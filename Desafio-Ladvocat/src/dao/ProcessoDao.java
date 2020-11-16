package dao;

import java.util.List;

import org.hibernate.Session;

import model.Processo;

public class ProcessoDao{
	
	private final Session se;
	
    public ProcessoDao(Session se) {
        this.se = se;
    }

    public void adiciona(Processo processo) {
        se.getTransaction().begin();
        se.persist(processo);
        se.getTransaction().commit();
    }
    
    public void alterar(Processo processo) {
    	se.getTransaction().begin();
    	se.update(processo);
    	se.getTransaction().commit();
    }

    public void rseove(Processo processo) {
        se.getTransaction().begin();
        se.delete(busca(processo));
        se.getTransaction().commit();
    }

    public Processo busca(Processo processo) {
        return (Processo) se.get(Processo.class, processo.getId());
    }

    @SuppressWarnings("unchecked")
    public List<Processo> lista() {
        return se.createQuery("select p from Processo p").list();
    }
}
