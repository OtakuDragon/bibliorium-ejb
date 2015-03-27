package br.com.fortium.bibliorium.persistence.eao;

import java.util.List;

import javax.persistence.EntityManager;

public interface EAO<T,ID> {
	EntityManager getEntityManager();
	void save(T entity);
	List<T> list();
	T buscar(ID id);
	List<T> buscar(String jpql, Object... parametros);
	T buscarUm(String jpql, Object... parametros);
	boolean exists(String jpql, Object... parametros);
}
