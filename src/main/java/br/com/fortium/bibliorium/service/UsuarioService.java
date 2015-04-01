package br.com.fortium.bibliorium.service;

import br.com.fortium.bibliorium.persistence.entity.Usuario;

public interface UsuarioService extends Service {
	Usuario autenticarUsuario(String cpf, String senha);
	void cadastrar(Usuario usuario);
	boolean isCpfCadastrado(String cpf);
	boolean isEmailCadastrado(String email);
	Usuario buscar(String cpf);
	void update(Usuario usuario);
}
