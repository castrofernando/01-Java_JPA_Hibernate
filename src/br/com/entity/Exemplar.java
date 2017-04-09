package br.com.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

	public Exemplar(int codigo,Calendar dataAquisicao, Situacao situacao) {
		super();
		this.codigo = codigo;
		this.dataAquisicao = dataAquisicao;
		this.situacao = situacao;
	}

	@ManyToOne
	@JoinColumn(name="LIVRO_isnb")
	private Livro livro;
	
	@ManyToMany(mappedBy="exemplares", cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	private List<Emprestimo> emprestimos;
	
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

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}
