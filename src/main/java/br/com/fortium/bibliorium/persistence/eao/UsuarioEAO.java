package br.com.fortium.bibliorium.persistence.eao;

import br.com.fortium.bibliorium.persistence.entity.Usuario;
import br.com.fortium.bibliorium.persistence.enumeration.TipoUsuario;

public interface UsuarioEAO extends EAO<Usuario, Long> {
	public TipoUsuario autenticarUsuario(String cpf, String senha);
	public boolean isCpfCadastrado(String cpf);
	public boolean isEmailCadastrado(String email);
}
