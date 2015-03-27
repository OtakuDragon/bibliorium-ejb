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
	LivroEAO livroEAO;
	
	@Override
	public boolean isIsbnCadastrado(String isbn) {
		if(!StringUtils.isNumeric(isbn)){
			throw new IllegalArgumentException("ISBN Inválido");
		}
		return livroEAO.isIsbnCadastrado(isbn);
	}

	@Override
	public List<Livro> list() {
		return livroEAO.list();
	}

}
