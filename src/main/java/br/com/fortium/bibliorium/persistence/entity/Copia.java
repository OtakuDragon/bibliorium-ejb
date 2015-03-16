package br.com.fortium.bibliorium.persistence.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.fortium.bibliorium.persistence.enumeration.EstadoCopia;

@Entity
@Table(schema="bibliorium", name = "copia")
public class Copia implements Serializable {
	
	private static final long serialVersionUID = -2935125477803111512L;

	public Copia() {}
	
	public Copia(Livro livro, EstadoCopia estado) {
		setLivro(livro);
		setEstado(estado);
	}

	@Id
	@SequenceGenerator(name="copiaIdSEQ", sequenceName="id_copia_seq",initialValue = 1 , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="copiaIdSEQ")
	@Column(name = "id_copia")
	private Long id;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="id_livro", nullable = false) 
	private Livro livro;
	
	@Column(name = "estado", nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoCopia estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public EstadoCopia getEstado() {
		return estado;
	}

	public void setEstado(EstadoCopia estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((livro == null) ? 0 : livro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Copia other = (Copia) obj;
		if (estado != other.estado) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (livro == null) {
			if (other.livro != null) {
				return false;
			}
		} else if (!livro.equals(other.livro)) {
			return false;
		}
		return true;
	}
}
