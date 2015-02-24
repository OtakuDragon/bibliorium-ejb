package br.com.fortium.bibliorium.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(schema="bibliorium", name = "livro")
public class Livro implements Serializable {

	private static final long serialVersionUID = 6095294978884506986L;

	public Livro(){}
	
	public Livro(Categoria categoria, String isbn, String nome, Date dataCadastro, Character edicao, String autores, String editora, Integer numPaginas) {
		setCategoria(categoria);
		setIsbn(isbn);
		setNome(nome);
		setDataCadastro(new Date());
		setEdicao(edicao);
		setAutores(autores);
		setEditora(editora);
		setNumPaginas(numPaginas);
	}

	@Id
	@SequenceGenerator(name="livroIdSEQ", sequenceName="id_livro_seq", initialValue = 1 , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="livroIdSEQ")
	@Column(name = "id_livro")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_categoria", nullable = false)
	private Categoria categoria;
	
	@Column(name = "isbn", nullable = false, unique = true, length = 13)
	private String isbn;
	
	@Column(name = "nome", nullable = false, length = 255)
	private String nome;
	
	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column(name = "edicao", nullable = false)
	private Character edicao;
	
	@Column(name = "autores", nullable = false, length = 300)
	private String autores;
	
	@Column(name = "editora", nullable = false, length = 100)
	private String editora;

	@Column(name = "num_paginas", nullable = false)
	private Integer numPaginas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Character getEdicao() {
		return edicao;
	}

	public void setEdicao(Character edicao) {
		this.edicao = edicao;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autores == null) ? 0 : autores.hashCode());
		result = prime * result
				+ ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result
				+ ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + edicao;
		result = prime * result + ((editora == null) ? 0 : editora.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + numPaginas;
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
		Livro other = (Livro) obj;
		if (autores == null) {
			if (other.autores != null) {
				return false;
			}
		} else if (!autores.equals(other.autores)) {
			return false;
		}
		if (categoria == null) {
			if (other.categoria != null) {
				return false;
			}
		} else if (!categoria.equals(other.categoria)) {
			return false;
		}
		if (dataCadastro == null) {
			if (other.dataCadastro != null) {
				return false;
			}
		} else if (!dataCadastro.equals(other.dataCadastro)) {
			return false;
		}
		if (edicao != other.edicao) {
			return false;
		}
		if (editora == null) {
			if (other.editora != null) {
				return false;
			}
		} else if (!editora.equals(other.editora)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (isbn == null) {
			if (other.isbn != null) {
				return false;
			}
		} else if (!isbn.equals(other.isbn)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (numPaginas != other.numPaginas) {
			return false;
		}
		return true;
	}
}
