package br.com.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TB_AUTOR")
@SequenceGenerator(allocationSize=1, name="seqAutor", sequenceName="SQ_AUTOR")
public class Autor {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="nome", nullable=false, length=300)
	private String nome;
	
	@Column(name="sexo", nullable=false)
	private Sexo sexo;
	
	@Column(name="sobrenome", nullable=false, length=300)
	private String sobrenome;
	
	@Column(name="dt_nascimento")
	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento;

	public Autor(String nome, Sexo sexo, String sobrenome, Calendar dataNascimento) {
		super();
		this.nome = nome;
		this.sexo = sexo;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
	}

	public Autor() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
