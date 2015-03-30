package br.com.fortium.bibliorium.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="bibliorium", name="categoria")
public class Categoria implements Serializable {

	private static final long serialVersionUID = -6980134754724153507L;

	public Categoria(){}
	
	public Categoria(Integer id, String nome){
		setId(id);
		setNome(nome);
	}
	
	@Id
	@Column(name = "id_categoria")
	private Integer id;
	
	@Column(name = "nome", unique = true, nullable = false, length = 50)
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Categoria other = (Categoria) obj;
		if (id != other.id) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}
}
