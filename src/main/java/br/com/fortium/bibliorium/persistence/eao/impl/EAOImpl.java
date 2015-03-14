package br.com.fortium.bibliorium.persistence.eao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class EAOImpl<T> {
	
	private EntityManager entityManager;
	
	private Class<T> entityClass; 
	
	protected EAOImpl(){
		entityClass = getEntityClass();
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
	
	protected abstract Class<T> getEntityClass();
}
