package br.com.fortium.bibliorium.service.impl;

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
			throw new IllegalArgumentException("O usu�rio n�o pode ser nulo");
		}
		return emprestimoEAO.buscar(usuario, periodo);
	}
	
	@Override
	public Emprestimo buscar(Long id) {
		if(id == null){
			throw new IllegalArgumentException("O id n�o pode ser nulo");
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
	public Long countEmprestimoAtivos(Usuario leitor) {
		if(leitor == null){
			throw new IllegalArgumentException("O leitor n�o pode ser nulo");
		}
		
		return emprestimoEAO.countEmprestimoAtivos(leitor);
	}

	@Override
	public Emprestimo buscarReserva(Copia copia) {
		if(copia == null){
			throw new IllegalArgumentException("A copia n�o pode ser nula");
		}
		return emprestimoEAO.buscarReserva(copia);
	}
	
	@Override
	public Emprestimo buscarEmprestimo(Copia copia) {
		if(copia == null){
			throw new IllegalArgumentException("A copia n�o pode ser nula");
		}
		return emprestimoEAO.buscarEmprestimo(copia);
	}
	
	@Override
	public Emprestimo buscarEmprestimo(Long id) {
		if(id == null){
			throw new IllegalArgumentException("O id n�o pode ser nulo");
		}
		return emprestimoEAO.buscarEmprestimo(id);
	}
	
	@Override
	public void cancelarReserva(Copia copia) {
		Emprestimo reserva = buscarReserva(copia);
		
		if(reserva == null){
			throw new IllegalArgumentException("A reserva n�o existe");
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
		
		update(emprestimo);
	}
	
	@Override
	public void liquidarMulta(Emprestimo emprestimo) {
		emprestimo.setDataFechamento(new Date());
		emprestimo.setEstado(EstadoEmprestimo.FINALIZADO);
		
		emprestimoEAO.update(emprestimo);
	}

	@Override
	public Emprestimo renovarEmprestimo(Copia copia) throws ValidationException {
		Emprestimo emprestimo = buscarEmprestimo(copia);

		validarRenovacao(emprestimo);
		
		emprestimo.setDataRenovacao(new Date());
		emprestimo.setDataPrevista(DataUtil.calcularDataDevolucao(emprestimo.getUsuario().getTipo()));
		
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
					emprestimoAtivo.incrementaMulta();
					break;
				default:
					break;
			}
			
			emprestimoEAO.update(emprestimoAtivo);
		}
	}

	private void validarEmprestimo(Emprestimo emprestimo) throws ValidationException {
		Usuario leitor = emprestimo.getUsuario();
		
		if(emprestimo == null || emprestimo.getCopia() == null){
			throw new IllegalArgumentException("O emprestimo inv�lido, ele ou a copia est�o nulos");
		} else if(leitor.getEstado() == EstadoUsuario.INADIMPLENTE){
			throw new ValidationException("Empr�stimo/Reserva recusado(a), este leitor est� inadimplente");
		}else if(countEmprestimoAtivos(leitor) >= 5){
			throw new ValidationException("Empr�stimo/Reserva recusado(a), este leitor j� atingiu o limite de 5 empr�stimo/reserva ativos");
		}
	}
	
	private void validarRenovacao(Emprestimo emprestimo) throws ValidationException{
		Usuario leitor = emprestimo.getUsuario();
		
		if(countEmprestimoAtivos(emprestimo.getUsuario()) >= 5){
			throw new ValidationException("Empr�stimo/Reserva recusado(a), este leitor j� atingiu o limite de 5 empr�stimo/reserva ativos");
		}else if(emprestimo.getDataRenovacao() != null){
			//TODO Fazer com que sejam 4 renova��es e n�o uma.
			throw new ValidationException("Este emprestimo ja foi renovado uma vez.");
		}else if(leitor.getEstado() == EstadoUsuario.INADIMPLENTE){
			throw new ValidationException("Usu�rio inadimplente");
		}else if(emprestimo.getDataEmprestimo().after(emprestimo.getDataPrevista())){
			throw new ValidationException("Emprestimo vencido");
		}
	}

}
