package br.com.fortium.bibliorium.persistence.eao.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.com.fortium.bibliorium.persistence.eao.LivroEAO;
import br.com.fortium.bibliorium.persistence.entity.Livro;

@Stateless
public class LivroEAOImpl extends EAOImpl<Livro, Long> implements LivroEAO {

	@Override
	public boolean isIsbnCadastrado(String isbn) {
		String jpql = "FROM Livro l WHERE l.isbn = ?1";
		return exists(jpql, isbn);
	}

	@Override
	public List<Livro> list() {
		String jpql = "FROM Livro l ORDER BY l.titulo";
		return buscar(jpql);
	}
	
	@Override
	protected Class<Livro> getEntityClass() {
		return Livro.class;
	}
}
