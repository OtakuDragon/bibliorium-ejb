package br.com.fortium.bibliorium.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.fortium.bibliorium.persistence.eao.CopiaEAO;
import br.com.fortium.bibliorium.persistence.entity.Copia;
import br.com.fortium.bibliorium.service.CopiaService;

@Stateless
public class CopiaServiceImpl extends ServiceImpl implements CopiaService {

	@EJB
	private CopiaEAO copiaEAO;
	
	@Override
	public void cadastrarCopias(List<Copia> copias) {
		if(copias == null || copias.isEmpty()){
			throw new IllegalArgumentException("Lista de copias inv�lida");
		}
		
		for (Copia copia : copias) {
			copiaEAO.save(copia);
		}
	}
}
