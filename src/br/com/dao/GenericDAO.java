package br.com.dao;

import java.util.List;

import br.com.exceptions.CommitException;
import br.com.exceptions.IdNotFoundExeption;

public interface GenericDAO<T,K>{
	
	void inserir(T entidade);
	void atualizar(T entidade);
	void remover(K id) throws IdNotFoundExeption;
	T buscar(K id);
	List<T> listar();
	void salvar() throws CommitException;
}
