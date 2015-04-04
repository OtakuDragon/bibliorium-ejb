package br.com.fortium.bibliorium.service.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.fortium.bibliorium.persistence.eao.EmprestimoEAO;
import br.com.fortium.bibliorium.persistence.entity.Emprestimo;
import br.com.fortium.bibliorium.persistence.entity.Usuario;
import br.com.fortium.bibliorium.service.EmprestimoService;

@Stateless
public class EmprestimoServiceImpl implements EmprestimoService {

	@EJB
	private EmprestimoEAO emprestimoEAO;

	@Override
	public List<Emprestimo> buscar(Usuario usuario, Date periodo) {
		if(usuario == null){
			throw new IllegalArgumentException("O usuário não pode ser nulo");
		}
		return emprestimoEAO.buscar(usuario, periodo);
	}
}
