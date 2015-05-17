package br.com.fortium.bibliorium.persistence.eao;

import br.com.fortium.bibliorium.persistence.entity.Copia;
import br.com.fortium.bibliorium.persistence.entity.Livro;

public interface CopiaEAO extends EAO<Copia, Long> {
	public void desativarCopias(Livro livro);
	Copia buscarCopia(Long idLivro, Long id);
}
