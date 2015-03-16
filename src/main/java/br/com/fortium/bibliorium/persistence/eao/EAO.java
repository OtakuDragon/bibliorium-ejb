package br.com.fortium.bibliorium.persistence.eao;

import java.util.List;

import javax.persistence.EntityManager;

public interface EAO<T,ID> {
	EntityManager getEntityManager();
	List<T> list();
	List<T> buscar(String jpql, Object[] parametros);
	T buscar(ID id);
	void save(T entity);
}
