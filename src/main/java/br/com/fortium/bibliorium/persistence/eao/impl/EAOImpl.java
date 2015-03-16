package br.com.fortium.bibliorium.persistence.eao.impl;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.fortium.bibliorium.persistence.eao.EAO;

public abstract class EAOImpl<T,ID> implements EAO<T,ID>{
	
	private EntityManager entityManager;
	
	private Class<T> entityClass; 
	private String idName;
	
	protected EAOImpl(){
		entityClass = getEntityClass();
		setIdName(entityClass);
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
	public List<T> buscar(String jpql, Object[] parametros) {
		Query query = getEntityManager().createQuery(jpql);
		
		for (int i = 1; i < parametros.length; i++) {
			query.setParameter(i, parametros[i]);
		}
		
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T buscar(ID id) {
		String jpql = "FROM "+entityClass.getSimpleName()+" entity WHERE entity."+idName+" = ?";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter(1, id);
		return (T) query.getSingleResult();
	};
	
	@Override
	public void save(T entity) {
		getEntityManager().persist(entity);
	};
	
	protected abstract Class<T> getEntityClass();

	private void setIdName(Class<T> clazz){
		for (Field field : clazz.getDeclaredFields()) {
			if(field.getAnnotation(Id.class) != null){
				idName = field.getName();
				break;
			}
		}
	}
}
