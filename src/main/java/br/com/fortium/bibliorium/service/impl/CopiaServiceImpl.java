package br.com.fortium.bibliorium.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.fortium.bibliorium.persistence.eao.CopiaEAO;
import br.com.fortium.bibliorium.persistence.entity.Copia;
import br.com.fortium.bibliorium.persistence.entity.Livro;
import br.com.fortium.bibliorium.service.CopiaService;
import br.com.fortium.bibliorium.service.LivroService;

@Stateless
public class CopiaServiceImpl extends ServiceImpl implements CopiaService {

	@EJB
	private LivroService livroService;
	
	@EJB
	private CopiaEAO copiaEAO;
	
	@Override
	public void cadastrarCopias(List<Copia> copias) {
		if(copias == null || copias.isEmpty()){
			throw new IllegalArgumentException("Lista de copias inválida");
		}
		
		for (Copia copia : copias) {
			if(! livroService.isIsbnCadastrado(copia.getLivro().getIsbn())){
				livroService.save(copia.getLivro());
			}else{
				copia.setLivro(livroService.buscarPorIsbn(copia.getLivro().getIsbn()));
			}
			copiaEAO.save(copia);
		}
	}

	@Override
	public List<Copia> list() {
		return copiaEAO.list();
	}

	@Override
	public Copia buscar(Long id) {
		return copiaEAO.buscar(id);
	}

	@Override
	public void update(Copia copia) {
		if(copia == null || copia.getId() == null){
			throw new IllegalArgumentException("Copia ou seu Id nulo");
		}
		copiaEAO.update(copia);
	}
	
	@Override
	public void desativarCopias(Livro livro) {
		copiaEAO.desativarCopias(livro);
	}
}
