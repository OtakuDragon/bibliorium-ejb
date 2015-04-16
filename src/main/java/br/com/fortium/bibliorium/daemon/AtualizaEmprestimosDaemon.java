package br.com.fortium.bibliorium.daemon;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import br.com.fortium.bibliorium.service.EmprestimoService;

@Stateless
public class AtualizaEmprestimosDaemon {

	private final Logger logger;
	
	{logger = Logger.getLogger(getClass());}
	
	@EJB
	private EmprestimoService emprestimoService;
	
	@Schedule
	private void atualizaEmprestimos(){
		logger.info("Iniciando a atualização automática de emprestimos");
		emprestimoService.atualizarEmprestimosAtivos();
	}
}
