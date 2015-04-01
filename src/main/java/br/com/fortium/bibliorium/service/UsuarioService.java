package br.com.fortium.bibliorium.service;

import br.com.fortium.bibliorium.persistence.entity.Usuario;
import br.com.fortium.bibliorium.persistence.enumeration.TipoUsuario;

public interface UsuarioService extends Service {
	TipoUsuario autenticarUsuario(String cpf, String senha);
	void cadastrar(Usuario usuario);
	boolean isCpfCadastrado(String cpf);
	boolean isEmailCadastrado(String email);
	Usuario buscar(String cpf);
}
