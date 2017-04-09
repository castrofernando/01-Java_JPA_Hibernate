package br.com.dao.impl;

import javax.persistence.EntityManager;

import br.com.dao.UsuarioDAO;
import br.com.entity.Usuario;

public class UsuarioDAOImpl extends GenericDAOImpl<Usuario, Integer> implements UsuarioDAO{

	public UsuarioDAOImpl(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}
