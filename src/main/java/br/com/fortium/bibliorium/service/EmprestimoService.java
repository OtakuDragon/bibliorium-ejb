package br.com.fortium.bibliorium.service;

import java.util.Date;
import java.util.List;

import br.com.fortium.bibliorium.exception.ValidationException;
import br.com.fortium.bibliorium.persistence.entity.Copia;
import br.com.fortium.bibliorium.persistence.entity.Emprestimo;
import br.com.fortium.bibliorium.persistence.entity.Usuario;

public interface EmprestimoService extends Service {
	List<Emprestimo> buscar(Usuario usuario, Date periodo);
	void efetuarEmprestimo(Emprestimo emprestimo) throws ValidationException;
	Long countEmprestimoAtivos(Usuario leitor);
	Emprestimo buscarReserva(Copia copia);
	void update(Emprestimo emprestimo);
	void concluirEmprestimo(Emprestimo emprestimo);
	void cancelarReserva(Copia copia);
	Emprestimo buscarEmprestimo(Copia copia);
	Emprestimo renovarEmprestimo(Copia copia) throws ValidationException;
}
