package br.com.fortium.bibliorium.persistence.eao.impl;

import javax.ejb.Stateless;

import br.com.fortium.bibliorium.persistence.eao.CopiaEAO;
import br.com.fortium.bibliorium.persistence.entity.Copia;
import br.com.fortium.bibliorium.persistence.entity.Livro;

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
	
	@Override
	public void desativarCopias(Livro livro) {
		String jpql = "UPDATE Copia c SET c.estado = br.com.fortium.bibliorium.persistence.enumeration.EstadoCopia.INATIVA WHERE  c.livro = ?1";
		update(jpql, new Object[]{livro});
	}
	
}
