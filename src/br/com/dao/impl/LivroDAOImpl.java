package br.com.dao.impl;

import javax.persistence.EntityManager;

import br.com.dao.LivroDAO;
import br.com.entity.Livro;

public class LivroDAOImpl extends GenericDAOImpl<Livro, Integer> implements LivroDAO{

	public LivroDAOImpl(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}
