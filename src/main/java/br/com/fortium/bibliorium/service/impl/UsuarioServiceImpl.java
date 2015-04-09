package br.com.fortium.bibliorium.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

import br.com.fortium.bibliorium.persistence.eao.UsuarioEAO;
import br.com.fortium.bibliorium.persistence.entity.Usuario;
import br.com.fortium.bibliorium.service.UsuarioService;

@Stateless
public class UsuarioServiceImpl extends ServiceImpl implements UsuarioService {

	@EJB
	UsuarioEAO usuarioEAO;
	
	public Usuario autenticarUsuario(String cpf, String senha){
		return usuarioEAO.autenticarUsuario(cpf, senha);
	}

	@Override
	public void cadastrar(Usuario usuario) {
		usuarioEAO.save(usuario);
	}

	@Override
	public boolean isCpfCadastrado(String cpf) {
		if(StringUtils.isEmpty(cpf)){
			return Boolean.FALSE;
		}
		return usuarioEAO.isCpfCadastrado(cpf);
	}

	@Override
	public boolean isEmailCadastrado(String email) {
		if(StringUtils.isEmpty(email)){
			return Boolean.FALSE;
		}
		return usuarioEAO.isEmailCadastrado(email);
	}

	@Override
	public Usuario buscar(String cpf) {
		if(StringUtils.isEmpty(cpf)){
			return null;
		}
		return usuarioEAO.buscar(cpf);
	}

	@Override
	public Usuario buscarComInativos(String cpf) {
		if(StringUtils.isEmpty(cpf)){
			return null;
		}
		return usuarioEAO.buscarComInativos(cpf);
	}
	
	@Override
	public void update(Usuario usuario) {
		usuarioEAO.update(usuario);
	}
	
}
