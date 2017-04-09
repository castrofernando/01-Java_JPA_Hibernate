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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name="USUARIO_id_usuario")
	private Usuario usuario;
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinTable(name="TB_EMPRESTIMO_EXEMPLAR", joinColumns={@JoinColumn(name="EMPRESTIMO_id")}, inverseJoinColumns={@JoinColumn(name="EXEMPLAR_id")})
	private List<Exemplar> exemplares;

	public Emprestimo(int codigo,Calendar dataEmprestimo, Calendar dataRetorno) {
		super();
		this.codigo = codigo;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Exemplar> getExemplares() {
		return exemplares;
	}

	public void setExemplares(List<Exemplar> exemplares) {
		this.exemplares = exemplares;
	}

}
