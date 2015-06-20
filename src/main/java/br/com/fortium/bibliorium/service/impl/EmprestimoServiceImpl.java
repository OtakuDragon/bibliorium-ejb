package br.com.fortium.bibliorium.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.fortium.bibliorium.exception.ValidationException;
import br.com.fortium.bibliorium.persistence.eao.EmprestimoEAO;
import br.com.fortium.bibliorium.persistence.entity.Copia;
import br.com.fortium.bibliorium.persistence.entity.Emprestimo;
import br.com.fortium.bibliorium.persistence.entity.Usuario;
import br.com.fortium.bibliorium.persistence.enumeration.EstadoCopia;
import br.com.fortium.bibliorium.persistence.enumeration.EstadoEmprestimo;
import br.com.fortium.bibliorium.persistence.enumeration.EstadoUsuario;
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
	public Emprestimo buscar(Long id) {
		if(id == null){
			throw new IllegalArgumentException("O id não pode ser nulo");
		}
		return emprestimoEAO.buscar(id);
	}

	@Override
	public void efetuarEmprestimo(Emprestimo emprestimo) throws ValidationException {
		validarEmprestimo(emprestimo);
		
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
	public Long countEmprestimoAtivos(Usuario usuario) {
		if(usuario == null){
			throw new IllegalArgumentException("O usuario não pode ser nulo");
		}
		
		return emprestimoEAO.countEmprestimoAtivos(usuario);
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
	public Emprestimo buscarEmprestimo(Long id) {
		if(id == null){
			throw new IllegalArgumentException("O id não pode ser nulo");
		}
		return emprestimoEAO.buscarEmprestimo(id);
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
		if(emprestimo.getEstado() != EstadoEmprestimo.DEVIDO){
			emprestimo.setDataFechamento(new Date());
		}
		emprestimo.getCopia().setEstado(EstadoCopia.DISPONIVEL);
		emprestimo.setCopia(copiaService.attachLivro(emprestimo.getCopia()));
		
		update(emprestimo);
	}
	
	@Override
	public void liquidarMulta(Emprestimo emprestimo) {
		emprestimo.setDataFechamento(new Date());
		emprestimo.setEstado(EstadoEmprestimo.FINALIZADO);
		copiaService.attachLivro(emprestimo.getCopia());
		
		emprestimoEAO.update(emprestimo);
	}

	@Override
	public Emprestimo renovarEmprestimo(Copia copia) throws ValidationException {
		Emprestimo emprestimo = buscarEmprestimo(copia);

		validarRenovacao(emprestimo);
		
		emprestimo.setDataRenovacao(new Date());
		emprestimo.setDataPrevista(DataUtil.calcularDataDevolucao(emprestimo.getUsuario().getTipo()));
		emprestimo.getCopia().getLivro();
		
		emprestimoEAO.update(emprestimo);
		
		return emprestimo;
	}
	
	@Override
	public List<Emprestimo> buscarEmprestimosAtivos() {
		return emprestimoEAO.buscarEmprestimosAtivos();
	}

	@Override
	public void atualizarEmprestimosAtivos() {
		List<Emprestimo> emprestimosAtivos = buscarEmprestimosAtivos();
		
		for (Emprestimo emprestimoAtivo : emprestimosAtivos) {
			switch(emprestimoAtivo.getEstado()){
				case ABERTO:
					if(emprestimoAtivo.getDataPrevista().before(new Date())){
						emprestimoAtivo.setValorMulta(Emprestimo.MULTA_DIARIA);
					}
					break;
				case DEVIDO:
					int numDias = DataUtil.getDiferencaEmDias(emprestimoAtivo.getDataPrevista(), new Date());
					emprestimoAtivo.setValorMulta(Emprestimo.MULTA_DIARIA.multiply(new BigDecimal(numDias)).setScale(2, RoundingMode.HALF_UP));
					break;
				default:
					break;
			}
			
			emprestimoEAO.update(emprestimoAtivo);
		}
	}

	private void validarEmprestimo(Emprestimo emprestimo) throws ValidationException {
		Usuario usuario = emprestimo.getUsuario();
		
		if(emprestimo == null || emprestimo.getCopia() == null){
			throw new IllegalArgumentException("O emprestimo inválido, ele ou a copia estão nulos");
		} else if(usuario.getEstado() == EstadoUsuario.INADIMPLENTE){
			throw new ValidationException("Empréstimo/Reserva recusado(a), este usuário está inadimplente");
		}else if(countEmprestimoAtivos(usuario) >= 5){
			throw new ValidationException("Empréstimo/Reserva recusado(a), este usuário já atingiu o limite de 5 empréstimo/reserva ativos");
		}
	}
	
	private void validarRenovacao(Emprestimo emprestimo) throws ValidationException{
		Usuario usuario = emprestimo.getUsuario();
		
		if(emprestimo.getDataRenovacao() != null){
			//TODO Fazer com que sejam 4 renovações e não uma.
			throw new ValidationException("Este emprestimo ja foi renovado uma vez.");
		}else if(usuario.getEstado() == EstadoUsuario.INADIMPLENTE){
			throw new ValidationException("Usuário inadimplente");
		}else if(emprestimo.getDataEmprestimo().after(emprestimo.getDataPrevista())){
			throw new ValidationException("Emprestimo vencido");
		}
	}

}
