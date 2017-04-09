package br.com.dao.impl;

import javax.persistence.EntityManager;

import br.com.dao.ExemplarDAO;
import br.com.entity.Exemplar;

public class ExemplarDAOImpl extends GenericDAOImpl<Exemplar, Integer> implements ExemplarDAO{

	public ExemplarDAOImpl(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}
