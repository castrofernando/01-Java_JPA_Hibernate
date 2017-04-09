package br.com.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Fabrica de conex�es singleton
 * @author Fernando
 */
public class EntityManagerFactorySingleton {
	
	private static EntityManagerFactory fabrica;

	/**
	 * Construtor privado
	 */
	private EntityManagerFactorySingleton() {

	}

	/**
	 * 
	 * @return Retorna um EntityManagerFactory para utiliza��o com DB Oracle
	 */
	public static EntityManagerFactory getInstance() {
		if (fabrica == null) {
			fabrica = Persistence.createEntityManagerFactory("ORACLE_BD");
		}
		return fabrica;
	}
}
