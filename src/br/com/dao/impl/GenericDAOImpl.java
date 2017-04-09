package br.com.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.dao.GenericDAO;
import br.com.exceptions.CommitException;
import br.com.exceptions.IdNotFoundException;

public class GenericDAOImpl<T,K> implements GenericDAO<T,K> {

	protected EntityManager em;	
	private Class<T> classe;
	
	@SuppressWarnings("unchecked")
	public GenericDAOImpl(EntityManager em) {
		this.em = em;
		classe = (Class<T>) ((ParameterizedType)getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void inserir(T entidade) {
		em.persist(entidade);
	}

	public void atualizar(T entidade) {
		em.merge(entidade);
	}

	public void remover(K id) throws IdNotFoundException {
		T entidade = this.buscar(id);
		if(entidade==null){
			throw new IdNotFoundException("Id não encontrado");
		}
		em.remove(entidade);
	}

	public T buscar(K id) {
		return em.find(classe,id);
	}

	public List<T> listar() {
		TypedQuery<T> query = em.createQuery("from " +classe.getName(), classe);
		return query.getResultList();
	}

	public void salvar() throws CommitException {
		try{
			em.getTransaction().begin();
			em.getTransaction().commit();
		}catch(Exception erro){
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
				erro.printStackTrace();
				throw new CommitException("Erro ao salvar os dados." + erro.getMessage());
			}
		}
	}

}
