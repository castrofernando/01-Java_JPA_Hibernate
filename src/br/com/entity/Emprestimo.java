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
@Table(name = "TB_EMPRESTIMO")
@SequenceGenerator(allocationSize = 1, name = "seqEmprestimo", sequenceName = "SQ_EMPRESTIMO")
public class Emprestimo {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "seqEmprestimo", strategy = GenerationType.SEQUENCE)
	private int codigo;

	@Column(name = "data_emprestimo", nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar dataEmprestimo;

	@Column(name = "data_retorno", nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar dataRetorno;

	public Emprestimo(Calendar dataEmprestimo, Calendar dataRetorno) {
		super();
		this.dataEmprestimo = dataEmprestimo;
		this.dataRetorno = dataRetorno;
	}

	public Emprestimo() {
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Calendar getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Calendar dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Calendar getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(Calendar dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

}
