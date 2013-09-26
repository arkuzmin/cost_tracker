package ru.arkuzmin.costtracker.db.model.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ru.arkuzmin.costtracker.common.Globals;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.bean.Category;
import ru.arkuzmin.costtracker.db.bean.Cost;
import ru.arkuzmin.costtracker.db.model.CostService;

public class CostServiceImpl implements CostService {
	private static EntityManagerFactory factory;
	
	private static final String GET_ALL_COSTS = "select c from Cost c";
	
	private static final String GET_ALL_COSTS_BY_AGENT = "Cost.getAllByAgent";
	
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
	public void addCost(Cost newCost, int agentId, int catId) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Agent agent = em.find(Agent.class, agentId);
		Category cat = em.find(Category.class, catId);
		
		newCost.setAgent(agent);
		newCost.setCategory(cat);
		
		em.persist(newCost);
		em.getTransaction().commit();
		
		em.close();
	}

	@Override
	public void updateCost(Cost cost, int agentId, int catId) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Agent agent = em.find(Agent.class, agentId);
		Category cat = em.find(Category.class, catId);
		
		cost.setAgent(agent);
		cost.setCategory(cat);
		
		Cost oldCost = em.find(Cost.class, cost.getId());
		oldCost.update(cost);
		
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteCost(Cost cost) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Cost c = em.find(Cost.class, cost.getId());
		em.remove(c);
		
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Cost> getAllCostsByAgent(Agent agent, Date bDt, Date eDt) {
		EntityManager em = factory.createEntityManager();
		
		Query q = em.createNamedQuery(GET_ALL_COSTS_BY_AGENT, Cost.class);
		List<Cost> result = q.setParameter("agent", agent)
							 .setParameter("bDt", bDt)
							 .setParameter("eDt", eDt)
							 .getResultList();
		
		em.close();
		return result;
	}
}
