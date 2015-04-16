package br.com.fortium.bibliorium.persistence.eao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import br.com.fortium.bibliorium.persistence.eao.EmprestimoEAO;
import br.com.fortium.bibliorium.persistence.entity.Copia;
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

	@Override
	public Long countEmprestimoAtivos(Usuario leitor) {
		String jpql = "SELECT COUNT(*) FROM Emprestimo e WHERE e.usuario = ?1 AND e.dataFechamento is null ";
		return count(jpql, leitor);
	}

	@Override
	public Emprestimo buscarReserva(Copia copia) {
		String jpql = "FROM Emprestimo e WHERE e.copia = ?1 AND e.dataFechamento IS NULL AND e.tipo = br.com.fortium.bibliorium.persistence.enumeration.TipoEmprestimo.RESERVA ";
		return buscarUm(jpql, copia);
	}
	
	@Override
	public Emprestimo buscarEmprestimo(Copia copia) {
		String jpql = "FROM Emprestimo e WHERE e.copia = ?1 AND e.dataFechamento IS NULL AND e.tipo = br.com.fortium.bibliorium.persistence.enumeration.TipoEmprestimo.EMPRESTIMO ";
		return buscarUm(jpql, copia);
	}

	@Override
	public Emprestimo buscarEmprestimo(Long id) {
		String jpql = "FROM Emprestimo e WHERE e.id = ?1 AND e.tipo = br.com.fortium.bibliorium.persistence.enumeration.TipoEmprestimo.EMPRESTIMO ";
		return buscarUm(jpql, id);
	}

	@Override
	public List<Emprestimo> buscarEmprestimosAtivos() {
		String jpql = "FROM Emprestimo e WHERE e.estado IN (br.com.fortium.bibliorium.persistence.enumeration.EstadoEmprestimo.ABERTO, br.com.fortium.bibliorium.persistence.enumeration.EstadoEmprestimo.DEVIDO)";
		return buscar(jpql);
	}

}
