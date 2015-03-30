package br.com.fortium.bibliorium.service;

import java.util.List;

import br.com.fortium.bibliorium.persistence.entity.Livro;

public interface LivroService extends Service {
	boolean isIsbnCadastrado(String isbn);
	List<Livro> list();
	void update(Livro livro);
	void delete(Livro livro);
	List<Livro> buscarPorFiltro(Livro filtro);
}
