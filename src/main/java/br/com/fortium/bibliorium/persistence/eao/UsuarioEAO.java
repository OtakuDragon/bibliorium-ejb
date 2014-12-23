package br.com.fortium.bibliorium.persistence.eao;

import br.com.fortium.bibliorium.persistence.enumeration.TipoUsuario;

public interface UsuarioEAO {
	public TipoUsuario autenticarUsuario(String cpf, String senha);
}
