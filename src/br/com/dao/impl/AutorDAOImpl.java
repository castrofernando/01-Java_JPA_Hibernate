package br.com.dao.impl;

import javax.persistence.EntityManager;

import br.com.dao.AutorDAO;
import br.com.entity.Autor;

public class AutorDAOImpl extends GenericDAOImpl<Autor, Integer> implements AutorDAO {

	public AutorDAOImpl(EntityManager em) {
		super(em);
	}

	
}
