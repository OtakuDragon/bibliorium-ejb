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

	public Livro(){
		setCategoria(new Categoria());
	}
	
	public Livro(Categoria categoria, String isbn, String titulo, Date dataCadastro, String edicao, String autores, String editora, Integer numPaginas) {
		setCategoria(categoria);
		setIsbn(isbn);
		setTitulo(titulo);
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
	
	@Column(name = "titulo", nullable = false, length = 255)
	private String titulo;
	
	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column(name = "edicao", nullable = false, length = 10)
	private String edicao;
	
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
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

	public Integer getNumPaginas() {
		return numPaginas;
	}
	
	public void setNumPaginas(Integer numPaginas) {
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
		result = prime * result + ((edicao == null) ? 0 : edicao.hashCode());
		result = prime * result + ((editora == null) ? 0 : editora.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result
				+ ((numPaginas == null) ? 0 : numPaginas.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		if (edicao == null) {
			if (other.edicao != null) {
				return false;
			}
		} else if (!edicao.equals(other.edicao)) {
			return false;
		}
		if (editora == null) {
			if (other.editora != null) {
				return false;
			}
		} else if (!editora.equals(other.editora)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (isbn == null) {
			if (other.isbn != null) {
				return false;
			}
		} else if (!isbn.equals(other.isbn)) {
			return false;
		}
		if (numPaginas == null) {
			if (other.numPaginas != null) {
				return false;
			}
		} else if (!numPaginas.equals(other.numPaginas)) {
			return false;
		}
		if (titulo == null) {
			if (other.titulo != null) {
				return false;
			}
		} else if (!titulo.equals(other.titulo)) {
			return false;
		}
		return true;
	}
}
