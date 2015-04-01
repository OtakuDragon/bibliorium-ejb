package br.com.fortium.bibliorium.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.fortium.bibliorium.persistence.enumeration.EstadoUsuario;
import br.com.fortium.bibliorium.persistence.enumeration.TipoUsuario;

@NamedQueries({
@NamedQuery(
name =  Usuario.AUTENTICA_USUARIO_QUERY,
query = "SELECT u FROM Usuario u WHERE u.cpf = ?1 AND  u.senha = ?2 AND u.estado <> br.com.fortium.bibliorium.persistence.enumeration.EstadoUsuario.INATIVO "
)})
@Entity
@Table(schema="bibliorium", name="usuario")
public class Usuario implements Serializable {
	
	public static transient final String AUTENTICADO = "autenticado";
	public static transient final String AUTENTICA_USUARIO_QUERY = "autenticaUsuario";
	private static final long serialVersionUID = -9117872512921595056L;

	public Usuario(){}
	
	public Usuario(String nome, String matricula, String cpf, TipoUsuario tipo, String email, EstadoUsuario estado, String telefone, Date dataCadastro, String rg) {
		setNome(nome);
		setMatricula(matricula);
		setCpf(cpf);
		setTipo(tipo);
		setEmail(email);
		setEstado(estado);
		setTelefone(telefone);
		setDataCadastro(new Date());
		setRg(rg);
	}

	@Id
	@SequenceGenerator(name="usuarioIdSEQ", sequenceName="id_usuario_seq", initialValue = 1 , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="usuarioIdSEQ")
	@Column(name = "id_usuario")
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 80)
	private String nome;
	
	@Column(name = "matricula", nullable = true)
	private String matricula;
	
	@Column(name = "cpf", nullable = false, unique = true, length = 11)
	private String cpf;
	
	@Column(name = "tipo", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;
	
	@Column(name = "email", nullable = false, unique = true, length = 100)
	private String email;

	@Column(name = "estado", nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoUsuario estado;
	
	@Column(name = "telefone", nullable = true, length = 20)
	private String telefone;
	
	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column(name = "rg", nullable = false, length = 20)
	private String rg;
	
	@Column(name = "senha", nullable = false, length = 255)
	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long idUsuario) {
		this.id = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EstadoUsuario getEstado() {
		return estado;
	}

	public void setEstado(EstadoUsuario estado) {
		this.estado = estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result
				+ ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (estado != other.estado)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
}
