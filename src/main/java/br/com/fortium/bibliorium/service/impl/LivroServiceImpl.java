package br.com.fortium.bibliorium.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

import br.com.fortium.bibliorium.persistence.eao.LivroEAO;
import br.com.fortium.bibliorium.persistence.entity.Livro;
import br.com.fortium.bibliorium.service.LivroService;

@Stateless
public class LivroServiceImpl extends ServiceImpl implements LivroService {

	@EJB
	private LivroEAO livroEAO;
	
	@Override
	public boolean isIsbnCadastrado(String isbn) {
		if(!StringUtils.isNumeric(isbn)){
			throw new IllegalArgumentException("ISBN Inv�lido");
		}
		return livroEAO.isIsbnCadastrado(isbn);
	}

	@Override
	public List<Livro> list() {
		return livroEAO.list();
	}

	@Override
	public Livro update(Livro livro) {
		return livroEAO.update(livro);
	}

	@Override
	public void delete(Livro livro) {
		livroEAO.delete(livro);
	}

	@Override
	public List<Livro> buscarPorFiltro(Livro filtro) {
		return livroEAO.buscarPorFiltro(filtro);
	}

	@Override
	public void save(Livro livro) {
		livroEAO.save(livro);
	}

	@Override
	public boolean isIsbnAtivo(String isbn) {
		return 	livroEAO.isIsbnAtivo(isbn);
	}

	@Override
	public Livro buscarPorIsbn(String isbn) {
		return livroEAO.buscarPorIsbn(isbn);
	}

}
