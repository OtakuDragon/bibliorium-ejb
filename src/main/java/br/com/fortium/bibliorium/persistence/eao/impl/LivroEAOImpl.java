package br.com.fortium.bibliorium.persistence.eao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

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

	@Override
	public List<Livro> buscarPorFiltro(Livro filtro) {
		List<Object> parametros = new ArrayList<Object>();
		
		StringBuilder jpql = new StringBuilder(" FROM Livro l WHERE 1=1 ");
		
		if(StringUtils.isNotEmpty(filtro.getTitulo())){
			jpql.append("AND l.titulo LIKE '%' || ? || '%' ");
			parametros.add(filtro.getTitulo());
		}
		if(filtro.getCategoria().getId() != null){
			jpql.append("AND l.categoria.id = ? ");
			parametros.add(filtro.getCategoria().getId());
		}
		
		if(StringUtils.isNotEmpty(filtro.getAutores())){
			jpql.append("AND l.autores LIKE '%' || ? || '%' ");
			parametros.add(filtro.getAutores());
		}
		if(StringUtils.isNotEmpty(filtro.getIsbn())){
			jpql.append("AND l.isbn LIKE '%' || ? || '%' ");
			parametros.add(filtro.getIsbn());
		}
		if(StringUtils.isNotEmpty(filtro.getEditora())){
			jpql.append("AND l.editora LIKE '%' || ? || '%' ");
			parametros.add(filtro.getEditora());
		}
		if(StringUtils.isNotEmpty(filtro.getEdicao())){
			jpql.append("AND l.edicao LIKE '%' || ? || '%' ");
			parametros.add(filtro.getEdicao());
		}
		
		return buscar(jpql.toString(), parametros.toArray());
	}
}
