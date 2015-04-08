package br.com.fortium.bibliorium.persistence.eao.impl;

import javax.ejb.Stateless;

import br.com.fortium.bibliorium.persistence.eao.CopiaEAO;
import br.com.fortium.bibliorium.persistence.entity.Copia;

@Stateless
public class CopiaEAOImpl extends EAOImpl<Copia, Long> implements CopiaEAO {
	@Override
	protected Class<Copia> getEntityClass() {
		return Copia.class;
	}
	
	@Override
	public Copia buscar(Long id) {
		String jpql = "FROM Copia c WHERE c.id = ?1 AND c.estado != br.com.fortium.bibliorium.persistence.enumeration.EstadoCopia.INATIVA ";
		return buscarUm(jpql, new Object[]{id});
	}
	
}
