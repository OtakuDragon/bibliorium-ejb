package br.com.fortium.bibliorium.persistence.eao;

import br.com.fortium.bibliorium.persistence.entity.Usuario;

public interface UsuarioEAO extends EAO<Usuario, Long> {
	Usuario autenticarUsuario(String cpf, String senha);
	boolean isCpfCadastrado(String cpf);
	boolean isEmailCadastrado(String email);
	Usuario buscar(String cpf);
}
