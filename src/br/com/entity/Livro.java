package br.com.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	
}
