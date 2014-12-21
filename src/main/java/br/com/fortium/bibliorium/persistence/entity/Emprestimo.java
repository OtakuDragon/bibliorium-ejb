package br.com.fortium.bibliorium.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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

import br.com.fortium.bibliorium.persistence.enumeration.TipoEmprestimo;

@Entity
@Table(schema="bibliorium", name = "emprestimo")
public class Emprestimo implements Serializable {

	private static final long serialVersionUID = -6434682029164398820L;

	public Emprestimo(){}
	
	public Emprestimo(Usuario usuario, Copia copia, Date dataEmprestimo, Date dataDevolucao, Date dataFechamento, BigDecimal valorMulta, TipoEmprestimo tipo, Date dataRenovacao) {
		setUsuario(usuario);
		setCopia(copia);
		setDataEmprestimo(dataEmprestimo);
		setDataDevolucao(dataDevolucao);
		setDataFechamento(dataFechamento);
		setValorMulta(valorMulta);
		setTipo(tipo);
		setDataRenovacao(dataRenovacao);
	}
	
	@Id
	@SequenceGenerator(name="emprestimoIdSEQ", sequenceName="id_emprestimo_seq", initialValue = 1 , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="emprestimoIdSEQ")
	@Column(name = "id_emprestimo")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_usuario", nullable = false) 
	private Usuario usuario;
	

	@ManyToOne
	@JoinColumn(name="id_copia", nullable = false)
	private Copia copia;
	
	@Column(name = "data_emprestimo", nullable = false)
	private Date dataEmprestimo;
	
	@Column(name = "data_devolucao")
	private Date dataDevolucao;
	
	@Column(name = "data_fechamento")
	private Date dataFechamento;
	
	@Column(name = "valor_multa")
	private BigDecimal valorMulta;
	
	@Column(name = "tipo", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoEmprestimo tipo;
	
	@Column(name = "data_renovacao")
	private Date dataRenovacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Copia getCopia() {
		return copia;
	}

	public void setCopia(Copia copia) {
		this.copia = copia;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public BigDecimal getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	public TipoEmprestimo getTipo() {
		return tipo;
	}

	public void setTipo(TipoEmprestimo tipo) {
		this.tipo = tipo;
	}

	public Date getDataRenovacao() {
		return dataRenovacao;
	}

	public void setDataRenovacao(Date dataRenovacao) {
		this.dataRenovacao = dataRenovacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((copia == null) ? 0 : copia.hashCode());
		result = prime * result
				+ ((dataDevolucao == null) ? 0 : dataDevolucao.hashCode());
		result = prime * result
				+ ((dataEmprestimo == null) ? 0 : dataEmprestimo.hashCode());
		result = prime * result
				+ ((dataFechamento == null) ? 0 : dataFechamento.hashCode());
		result = prime * result
				+ ((dataRenovacao == null) ? 0 : dataRenovacao.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result
				+ ((valorMulta == null) ? 0 : valorMulta.hashCode());
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
		Emprestimo other = (Emprestimo) obj;
		if (copia == null) {
			if (other.copia != null) {
				return false;
			}
		} else if (!copia.equals(other.copia)) {
			return false;
		}
		if (dataDevolucao == null) {
			if (other.dataDevolucao != null) {
				return false;
			}
		} else if (!dataDevolucao.equals(other.dataDevolucao)) {
			return false;
		}
		if (dataEmprestimo == null) {
			if (other.dataEmprestimo != null) {
				return false;
			}
		} else if (!dataEmprestimo.equals(other.dataEmprestimo)) {
			return false;
		}
		if (dataFechamento == null) {
			if (other.dataFechamento != null) {
				return false;
			}
		} else if (!dataFechamento.equals(other.dataFechamento)) {
			return false;
		}
		if (dataRenovacao == null) {
			if (other.dataRenovacao != null) {
				return false;
			}
		} else if (!dataRenovacao.equals(other.dataRenovacao)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (tipo != other.tipo) {
			return false;
		}
		if (usuario == null) {
			if (other.usuario != null) {
				return false;
			}
		} else if (!usuario.equals(other.usuario)) {
			return false;
		}
		if (valorMulta == null) {
			if (other.valorMulta != null) {
				return false;
			}
		} else if (!valorMulta.equals(other.valorMulta)) {
			return false;
		}
		return true;
	}
}
