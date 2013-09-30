package ru.arkuzmin.costtracker.common;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFSingleton {
	
	private static EntityManagerFactory factory;
	
	static {
		factory = Persistence.createEntityManagerFactory(Globals.PERSISTENCE_UNIT_NAME);
	}
	
	public static EntityManagerFactory getEMF() {
		if (factory != null && factory.isOpen()) {
			return factory;
		} else {
			throw new IllegalStateException("Фабрика не была инициализирована или закрыта");
		}
			
	}
	
	public static void newEMF() {
		factory = Persistence.createEntityManagerFactory(Globals.PERSISTENCE_UNIT_NAME);
	}
	
	public static void closeEMF() {
		if (factory != null && !factory.isOpen()) {
			factory.close();
		} else {
			throw new IllegalStateException("Фабрика не была инициализирована или закрыта");
		}
	}
	
}
