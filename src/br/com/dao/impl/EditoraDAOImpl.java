package br.com.dao.impl;

import javax.persistence.EntityManager;

import br.com.dao.EditoraDAO;
import br.com.entity.Editora;

public class EditoraDAOImpl extends GenericDAOImpl<Editora, Integer> implements EditoraDAO{

	public EditoraDAOImpl(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}
