package br.com.fortium.bibliorium.persistence.eao.impl;

import javax.ejb.Stateless;

import br.com.fortium.bibliorium.persistence.eao.CategoriaEAO;
import br.com.fortium.bibliorium.persistence.entity.Categoria;

@Stateless
public class CategoriaEAOImpl extends EAOImpl<Categoria> implements CategoriaEAO {

	@Override
	protected Class<Categoria> getEntityClass() {
		return Categoria.class;
	}

}
