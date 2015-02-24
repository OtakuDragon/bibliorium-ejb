package br.com.fortium.bibliorium.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.fortium.bibliorium.persistence.eao.CategoriaEAO;
import br.com.fortium.bibliorium.persistence.entity.Categoria;
import br.com.fortium.bibliorium.service.CategoriaService;

@Stateless
public class CategoriaServiceImpl extends ServiceImpl implements CategoriaService {

	@EJB
	private CategoriaEAO categoriaEAO;

	@Override
	public List<Categoria> buscarCategorias() {
		return categoriaEAO.list();
	}

}
