package br.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_USUARIO")
@SequenceGenerator(allocationSize = 1, name = "seqUsuario", sequenceName = "SQ_USUARIO")
public class Usuario {

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(generator = "seqUsuario", strategy = GenerationType.SEQUENCE)
	private int codigo;

	@Column(name = "nome_usuario", nullable = false)
	private String usuario;

	@Column(name = "nome", nullable = false, length = 300)
	private String nome;

	@Column(name = "sobrenome", length = 300)
	private String sobrenome;

	@Column(name = "cpf", length = 20)
	private String cpf;

	public Usuario(String usuario, String nome, String sobrenome, String cpf) {
		super();
		this.usuario = usuario;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
	}

	public Usuario() {
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
