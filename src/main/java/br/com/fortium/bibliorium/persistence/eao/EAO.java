package br.com.fortium.bibliorium.persistence.eao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class EAO {
	
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext(name="biblioriumPU")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
}
