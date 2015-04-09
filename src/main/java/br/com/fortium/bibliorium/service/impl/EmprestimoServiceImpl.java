package br.com.fortium.bibliorium.service.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.fortium.bibliorium.persistence.eao.EmprestimoEAO;
import br.com.fortium.bibliorium.persistence.entity.Copia;
import br.com.fortium.bibliorium.persistence.entity.Emprestimo;
import br.com.fortium.bibliorium.persistence.entity.Usuario;
import br.com.fortium.bibliorium.persistence.enumeration.EstadoCopia;
import br.com.fortium.bibliorium.persistence.enumeration.TipoEmprestimo;
import br.com.fortium.bibliorium.service.CopiaService;
import br.com.fortium.bibliorium.service.EmprestimoService;

@Stateless
public class EmprestimoServiceImpl implements EmprestimoService {

	@EJB
	private EmprestimoEAO emprestimoEAO;
	
	@EJB
	private CopiaService copiaService;

	@Override
	public List<Emprestimo> buscar(Usuario usuario, Date periodo) {
		if(usuario == null){
			throw new IllegalArgumentException("O usuário não pode ser nulo");
		}
		return emprestimoEAO.buscar(usuario, periodo);
	}

	@Override
	public void efetuarEmprestimo(Emprestimo emprestimo) {
		if(emprestimo == null || emprestimo.getCopia() == null){
			throw new IllegalArgumentException("O emprestimo inválido, ele ou a copia estão nulos");
		}
		
		Copia copia = emprestimo.getCopia();
		
		if(emprestimo.getTipo() == TipoEmprestimo.EMPRESTIMO){
			copia.setEstado(EstadoCopia.EMPRESTADA);
		}else if(emprestimo.getTipo() == TipoEmprestimo.RESERVA){
			copia.setEstado(EstadoCopia.RESERVADA);
		}
		
		copiaService.update(copia);
		emprestimoEAO.save(emprestimo);
	}

	@Override
	public Long countEmprestimoAtivos(Usuario leitor) {
		if(leitor == null){
			throw new IllegalArgumentException("O leitor não pode ser nulo");
		}
		
		return emprestimoEAO.countEmprestimoAtivos(leitor);
	}

	@Override
	public Emprestimo buscarReserva(Copia copia) {
		if(copia == null){
			throw new IllegalArgumentException("A copia não pode ser nula");
		}
		return emprestimoEAO.buscarReserva(copia);
	}

	@Override
	public void update(Emprestimo emprestimo) {
		emprestimoEAO.update(emprestimo);
	}

	@Override
	public void concluirEmprestimo(Emprestimo emprestimo) {
		emprestimo.setDataFechamento(new Date());
		
		if(emprestimo.getTipo() == TipoEmprestimo.EMPRESTIMO){
			//TODO Calcular e setar multa
		}
		
		update(emprestimo);
	}

}
