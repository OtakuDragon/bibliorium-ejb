package br.com.fortium.bibliorium.service.impl;

import java.math.BigDecimal;
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
import br.com.fortium.bibliorium.util.DataUtil;

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
	public Emprestimo buscarEmprestimo(Copia copia) {
		if(copia == null){
			throw new IllegalArgumentException("A copia não pode ser nula");
		}
		return emprestimoEAO.buscarEmprestimo(copia);
	}
	
	@Override
	public void cancelarReserva(Copia copia) {
		Emprestimo reserva = buscarReserva(copia);
		
		if(reserva == null){
			throw new IllegalArgumentException("A reserva não existe");
		}
		
		reserva.getCopia().setEstado(EstadoCopia.DISPONIVEL);
		reserva.setDataFechamento(new Date());
		
		emprestimoEAO.update(reserva);
	}

	@Override
	public void update(Emprestimo emprestimo) {
		emprestimoEAO.update(emprestimo);
	}

	@Override
	public void concluirEmprestimo(Emprestimo emprestimo) {
		emprestimo.setDataFechamento(new Date());
		emprestimo.getCopia().setEstado(EstadoCopia.DISPONIVEL);
		
		if(emprestimo.getTipo() == TipoEmprestimo.EMPRESTIMO){
			int diasAtraso = DataUtil.getDiferencaEmDias(emprestimo.getDataDevolucao(), new Date());
			
			BigDecimal valorMulta = new BigDecimal(1.5 * diasAtraso).setScale(2);
			
			if(valorMulta.doubleValue() > 0){
				emprestimo.setValorMulta(valorMulta);
			}
		}
		
		update(emprestimo);
	}

}
