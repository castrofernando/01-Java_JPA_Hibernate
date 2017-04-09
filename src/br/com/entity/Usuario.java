package br.com.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@Column(name = "cpf",nullable=false, length = 20)
	private String cpf;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	private List<Emprestimo> emprestimos;
	
	public void adicionarEmprestimo(Emprestimo emprestimo){
		emprestimo.setUsuario(this);
		emprestimos.add(emprestimo);
	}

	public Usuario(int codigo, String usuario, String nome, String sobrenome, String cpf) {
		super();
		this.codigo = codigo;
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

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimo) {
		this.emprestimos = emprestimo;
	}


}
