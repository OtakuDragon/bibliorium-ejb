package br.com.fortium.bibliorium.service;

import java.util.Date;
import java.util.List;

import br.com.fortium.bibliorium.exception.ValidationException;
import br.com.fortium.bibliorium.persistence.entity.Copia;
import br.com.fortium.bibliorium.persistence.entity.Emprestimo;
import br.com.fortium.bibliorium.persistence.entity.Usuario;

public interface EmprestimoService extends Service {
	List<Emprestimo> buscar(Usuario usuario, Date periodo);
	Emprestimo buscar(Long id);
	void efetuarEmprestimo(Emprestimo emprestimo) throws ValidationException;
	Long countEmprestimoAtivos(Usuario usuario);
	Emprestimo buscarReserva(Copia copia);
	void update(Emprestimo emprestimo);
	void concluirEmprestimo(Emprestimo emprestimo);
	void liquidarMulta(Emprestimo emprestimo);
	void cancelarReserva(Copia copia);
	Emprestimo buscarEmprestimo(Copia copia);
	Emprestimo buscarEmprestimo(Long id);
	Emprestimo renovarEmprestimo(Copia copia) throws ValidationException;
	List<Emprestimo> buscarEmprestimosAtivos();
	void atualizarEmprestimosAtivos();
}
