package br.com.fortium.bibliorium.service;

import java.util.Date;
import java.util.List;

import br.com.fortium.bibliorium.persistence.entity.Emprestimo;
import br.com.fortium.bibliorium.persistence.entity.Usuario;

public interface EmprestimoService extends Service {
	List<Emprestimo> buscar(Usuario usuario, Date periodo);
}
