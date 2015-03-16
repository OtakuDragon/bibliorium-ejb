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


}
