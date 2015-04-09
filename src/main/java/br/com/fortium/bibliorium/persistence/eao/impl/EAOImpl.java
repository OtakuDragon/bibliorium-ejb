package br.com.fortium.bibliorium.persistence.eao.impl;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.com.fortium.bibliorium.persistence.eao.EAO;

public abstract class EAOImpl<T,ID> implements EAO<T,ID>{
	
	private EntityManager entityManager;
	
	private Class<T> entityClass; 
	private String idName;
	private Logger logger;
	
	protected EAOImpl(){
		entityClass = getEntityClass();
		setIdName(entityClass);
		logger = Logger.getLogger(entityClass);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> list(){
		String jpql = "FROM "+entityClass.getSimpleName();
		Query query = getEntityManager().createQuery(jpql);
		
		return query.getResultList();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext(name="biblioriumPU")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> buscar(String jpql, Object... parametros) {
		Query query = getEntityManager().createQuery(jpql);
		setParametros(query, parametros);
		
		return (List<T>)query.getResultList();
	}
	
	@Override
	public Long count(String jpql, Object... parametros){
		Query query = getEntityManager().createQuery(jpql);
		setParametros(query, parametros);
		
		return (Long)query.getSingleResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T buscarUm(String jpql, Object... parametros) {
		Query query = getEntityManager().createQuery(jpql);
		setParametros(query, parametros);

		try{
			return (T)query.getSingleResult();
		}catch(NoResultException e){
			getLogger().error(e);
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T buscarUm(Query query, Object... parametros) {
		setParametros(query, parametros);

		try{
			return (T)query.getSingleResult();
		}catch(NoResultException e){
			getLogger().error(e);
			return null;
		}
	}
	
	@Override
	public boolean exists(String jpql, Object... parametros){
		return buscarUm(jpql, parametros) != null ? Boolean.TRUE : Boolean.FALSE;
	}
	
	@Override
	public T buscar(ID id) {
		String jpql = "FROM "+entityClass.getSimpleName()+" entity WHERE entity."+idName+" = ?1";
		return buscarUm(jpql, new Object[]{id});
	};
	
	@Override
	public void save(T entity) {
		getEntityManager().persist(entity);
	};
	
	@Override
	public T update(T entity){
		return getEntityManager().merge(entity);
	}
	
	@Override
	public void delete(T entity){
		getEntityManager().remove(update(entity));
	}
	
	protected abstract Class<T> getEntityClass();

	private void setIdName(Class<T> clazz){
		for (Field field : clazz.getDeclaredFields()) {
			if(field.getAnnotation(Id.class) != null){
				idName = field.getName();
				break;
			}
		}
	}
	
	private Query setParametros(Query query, Object[] parametros){
		for (int i = 0; i < parametros.length; i++) {
			query.setParameter(i+1, parametros[i]);
		}
		
		return query;
	}

	public Logger getLogger() {
		return logger;
	}
}
