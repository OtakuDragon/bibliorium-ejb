package br.com.fortium.bibliorium.persistence.eao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public interface EAO<T,ID> {
	EntityManager getEntityManager();
	void save(T entity);
	List<T> list();
	T buscar(ID id);
	Long count(String jpql, Object... parametros);
	List<T> buscar(String jpql, Object... parametros);
	T buscarUm(String jpql, Object... parametros);
	T buscarUm(Query query, Object... parametros);
	boolean exists(String jpql, Object... parametros);
	T update(T entity);
	void delete(T entity);
	int update(String jpql, Object... parametros);
}
