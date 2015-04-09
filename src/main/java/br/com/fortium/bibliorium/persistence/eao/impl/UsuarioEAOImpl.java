package br.com.fortium.bibliorium.persistence.eao.impl;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.fortium.bibliorium.persistence.eao.UsuarioEAO;
import br.com.fortium.bibliorium.persistence.entity.Usuario;

@Stateless
public class UsuarioEAOImpl extends EAOImpl<Usuario, Long> implements UsuarioEAO {

	@Override
	protected Class<Usuario> getEntityClass() {
		return Usuario.class;
	}
	
	@Override
	public Usuario autenticarUsuario(String cpf, String senha) {
		Query query = getEntityManager().createNamedQuery(Usuario.AUTENTICA_USUARIO_QUERY);
		return buscarUm(query, new Object[]{cpf, senha});
	}

	@Override
	public boolean isCpfCadastrado(String cpf) {
		String jpql = "FROM Usuario u WHERE u.cpf = ? ";
		return exists(jpql, new Object[]{cpf});
	}

	@Override
	public boolean isEmailCadastrado(String email) {
		String jpql = "FROM Usuario u WHERE u.email = ? ";
		return exists(jpql, new Object[]{email});
	}

	@Override
	public Usuario buscar(String cpf) {
		String jpql = "FROM Usuario u WHERE u.cpf = ? AND u.estado <> br.com.fortium.bibliorium.persistence.enumeration.EstadoUsuario.INATIVO ";
		return buscarUm(jpql, new Object[]{cpf});
	}
	
	@Override
	public Usuario buscarComInativos(String cpf) {
		String jpql = "FROM Usuario u WHERE u.cpf = ? ";
		return buscarUm(jpql, new Object[]{cpf});
	}
}
