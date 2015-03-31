package br.com.fortium.bibliorium.persistence.eao.impl;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.fortium.bibliorium.persistence.eao.UsuarioEAO;
import br.com.fortium.bibliorium.persistence.entity.Usuario;
import br.com.fortium.bibliorium.persistence.enumeration.TipoUsuario;

@Stateless
public class UsuarioEAOImpl extends EAOImpl<Usuario, Long> implements UsuarioEAO {

	@Override
	protected Class<Usuario> getEntityClass() {
		return Usuario.class;
	}
	
	@Override
	public TipoUsuario autenticarUsuario(String cpf, String senha) {
		Query query = getEntityManager().createNamedQuery(Usuario.AUTENTICA_USUARIO_QUERY);
		query.setParameter("cpf", cpf);
		query.setParameter("senha", senha);
		try{
			return (TipoUsuario)query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	@Override
	public boolean isCpfCadastrado(String cpf) {
		String jpql = "FROM Usuario u WHERE u.cpf = ?";
		return exists(jpql, new Object[]{cpf});
	}

	@Override
	public boolean isEmailCadastrado(String email) {
		String jpql = "FROM Usuario u WHERE u.email = ?";
		return exists(jpql, new Object[]{email});
	}
}
