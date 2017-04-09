package br.com.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_EXEMPLAR")
@SequenceGenerator(allocationSize = 1, name = "seqExemplar", sequenceName = "SQ_EXEMPLAR")
public class Exemplar {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "seqExemplar", strategy = GenerationType.SEQUENCE)
	private int codigo;

	@Column(name = "data_aquisicao")
	@Temporal(TemporalType.DATE)
	private Calendar dataAquisicao;

	@Column(name = "situacao", nullable = false)
	private Situacao situacao;

	public Exemplar(Calendar dataAquisicao, Situacao situacao) {
		super();
		this.dataAquisicao = dataAquisicao;
		this.situacao = situacao;
	}

	public Exemplar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Calendar getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(Calendar dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

}
