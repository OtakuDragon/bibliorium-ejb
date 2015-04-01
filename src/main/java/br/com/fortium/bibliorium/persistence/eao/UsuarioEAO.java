package br.com.fortium.bibliorium.persistence.eao;

import br.com.fortium.bibliorium.persistence.entity.Usuario;
import br.com.fortium.bibliorium.persistence.enumeration.TipoUsuario;

public interface UsuarioEAO extends EAO<Usuario, Long> {
	TipoUsuario autenticarUsuario(String cpf, String senha);
	boolean isCpfCadastrado(String cpf);
	boolean isEmailCadastrado(String email);
	Usuario buscar(String cpf);
}
