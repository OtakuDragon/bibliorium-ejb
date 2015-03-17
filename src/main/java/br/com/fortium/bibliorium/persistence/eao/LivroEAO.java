package br.com.fortium.bibliorium.persistence.eao;

import br.com.fortium.bibliorium.persistence.entity.Livro;

public interface LivroEAO extends EAO<Livro, Long> {
	boolean isIsbnCadastrado(String isbn);
}
