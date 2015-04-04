package br.com.fortium.bibliorium.persistence.eao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import br.com.fortium.bibliorium.persistence.eao.EmprestimoEAO;
import br.com.fortium.bibliorium.persistence.entity.Emprestimo;
import br.com.fortium.bibliorium.persistence.entity.Usuario;

@Stateless
public class EmprestimoEAOImpl extends EAOImpl<Emprestimo, Long> implements EmprestimoEAO {

	@Override
	protected Class<Emprestimo> getEntityClass() {
		return Emprestimo.class;
	}

	@Override
	public List<Emprestimo> buscar(Usuario usuario, Date periodo) {
		StringBuilder jpql = new StringBuilder();
		List<Object> parametros = new ArrayList<Object>();
		
		jpql.append("FROM Emprestimo e WHERE e.usuario = ?1 ");
		parametros.add(usuario);
		
		if(periodo != null){
			Calendar c = Calendar.getInstance();
			c.setTime(periodo);
			jpql.append("AND MONTH(e.dataEmprestimo) = ?2 AND YEAR(e.dataEmprestimo) = ?3");
			parametros.add(c.get(Calendar.MONTH)+1);
			parametros.add(c.get(Calendar.YEAR));
		}
		
		return buscar(jpql.toString(), parametros.toArray());
	}

}
