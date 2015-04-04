package br.com.fortium.bibliorium.persistence.eao;

import java.util.List;

import br.com.fortium.bibliorium.persistence.entity.Livro;

public interface LivroEAO extends EAO<Livro, Long> {
	boolean isIsbnCadastrado(String isbn);
	List<Livro> buscarPorFiltro(Livro filtro);
}
