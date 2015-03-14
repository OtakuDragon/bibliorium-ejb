package br.com.fortium.bibliorium.persistence.eao;

import java.util.List;

import javax.persistence.EntityManager;

public interface EAO<T> {
	EntityManager getEntityManager();
	List<T> list();
}
