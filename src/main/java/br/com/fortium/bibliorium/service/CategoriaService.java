package br.com.fortium.bibliorium.service;

import java.util.List;

import br.com.fortium.bibliorium.persistence.entity.Categoria;

public interface CategoriaService extends Service {
	List<Categoria> buscarCategorias();
}
