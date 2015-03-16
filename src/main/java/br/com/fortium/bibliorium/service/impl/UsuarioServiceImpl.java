package br.com.fortium.bibliorium.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.fortium.bibliorium.persistence.eao.UsuarioEAO;
import br.com.fortium.bibliorium.persistence.enumeration.TipoUsuario;
import br.com.fortium.bibliorium.service.UsuarioService;

@Stateless
public class UsuarioServiceImpl extends ServiceImpl implements UsuarioService {

	@EJB
	UsuarioEAO usuarioEAO;
	
	public TipoUsuario autenticarUsuario(String cpf, String senha){
		return usuarioEAO.autenticarUsuario(cpf, senha);
	}
	
}
