package br.com.view;

import javax.persistence.EntityManager;

import br.com.singleton.EntityManagerFactorySingleton;

public class TesteConsole {
	
	public static void main(String[] args) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
	}
}
