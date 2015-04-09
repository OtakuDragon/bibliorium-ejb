package br.com.fortium.bibliorium.persistence.eao;

import java.util.Date;
import java.util.List;

import br.com.fortium.bibliorium.persistence.entity.Copia;
import br.com.fortium.bibliorium.persistence.entity.Emprestimo;
import br.com.fortium.bibliorium.persistence.entity.Usuario;

public interface EmprestimoEAO extends EAO<Emprestimo, Long> {
	List<Emprestimo> buscar(Usuario usuario, Date periodo);
	Long countEmprestimoAtivos(Usuario leitor);
	Emprestimo buscarReserva(Copia copia);
}
