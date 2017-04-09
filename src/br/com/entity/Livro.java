package br.com.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TB_LIVRO")
public class Livro {

	@Id
	private int isnb;
	
	@Column(name="titulo",nullable=false, length=200)
	private String titulo;
	
	private float preco;
	
	@Column(name="dt_lancamento")
	@Temporal(TemporalType.DATE)
	private Calendar dataLancamento;
	
	private byte[] capa;

	@ManyToMany(mappedBy="livros")
	private List<Autor> autores;
	
	@ManyToOne
	@JoinColumn(name="EDITORA_id")
	private Editora editora;
	
	@OneToMany(mappedBy="livro", cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	private List<Exemplar> exemplares;
	
	public void adicionarExemplar(Exemplar exemplar){
		exemplar.setLivro(this);
		exemplares.add(exemplar);
	}

	public Livro(int isnb, String titulo, float preco, Calendar dataLancamento, byte[] capa) {
		super();
		this.isnb = isnb;
		this.titulo = titulo;
		this.preco = preco;
		this.dataLancamento = dataLancamento;
		this.capa = capa;
	}

	public Livro(){
		
	}

	public int getIsnb() {
		return isnb;
	}

	public void setIsnb(int isnb) {
		this.isnb = isnb;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public byte[] getCapa() {
		return capa;
	}

	public void setCapa(byte[] capa) {
		this.capa = capa;
	}
	
	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public List<Exemplar> getExemplares() {
		return exemplares;
	}

	public void setExemplares(List<Exemplar> exemplares) {
		this.exemplares = exemplares;
	}
}
