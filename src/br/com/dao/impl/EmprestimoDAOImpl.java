package br.com.dao.impl;

import javax.persistence.EntityManager;

import br.com.dao.EmprestimoDAO;
import br.com.entity.Emprestimo;

public class EmprestimoDAOImpl extends GenericDAOImpl<Emprestimo, Integer> implements EmprestimoDAO{

	public EmprestimoDAOImpl(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}
