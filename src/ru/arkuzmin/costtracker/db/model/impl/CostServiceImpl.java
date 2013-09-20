package ru.arkuzmin.costtracker.db.model.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ru.arkuzmin.costtracker.common.Globals;
import ru.arkuzmin.costtracker.db.bean.Cost;
import ru.arkuzmin.costtracker.db.model.CostService;

public class CostServiceImpl implements CostService {

	private static EntityManagerFactory factory;
	
	private static final String GET_ALL_COSTS = "select c from Cost c";
	
	static {
		factory = Persistence.createEntityManagerFactory(Globals.PERSISTENCE_UNIT_NAME);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cost> getAllCosts() {
	    EntityManager em = factory.createEntityManager();
	    Query q = em.createQuery(GET_ALL_COSTS);
	    List<Cost> list = q.getResultList();
	    em.close();
	    
	    return list;
	}

	@Override
	public void addCost(Cost newCost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCost(Cost cost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCost(Cost cost) {
		// TODO Auto-generated method stub
		
	}

}
