package br.com.fortium.bibliorium.service;

import java.util.List;

import br.com.fortium.bibliorium.persistence.entity.Copia;

public interface CopiaService extends Service {
	void cadastrarCopias(List<Copia> copias);
	List<Copia> list();
	Copia buscar(Long id);
	void update(Copia copia);
}
