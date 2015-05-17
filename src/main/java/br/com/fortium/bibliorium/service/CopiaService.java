package br.com.fortium.bibliorium.service;

import java.util.List;

import br.com.fortium.bibliorium.persistence.entity.Copia;
import br.com.fortium.bibliorium.persistence.entity.Livro;

public interface CopiaService extends Service {
	void cadastrarCopias(List<Copia> copias);
	List<Copia> list();
	Copia buscar(Long idLivro, Long id);
	void update(Copia copia);
	void desativarCopias(Livro livro);
	Copia attachLivro(Copia copia);
}
