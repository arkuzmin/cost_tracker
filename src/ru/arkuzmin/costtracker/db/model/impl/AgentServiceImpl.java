package ru.arkuzmin.costtracker.db.model.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ru.arkuzmin.costtracker.common.Globals;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.model.AgentService;

public class AgentServiceImpl implements AgentService {

	private static EntityManagerFactory factory;
	
	private static final String GET_ALL_AGENTS = "select a from Agent a";
	
	static {
		factory = Persistence.createEntityManagerFactory(Globals.PERSISTENCE_UNIT_NAME);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Agent> getAllAgents() {
	    EntityManager em = factory.createEntityManager();
	    Query q = em.createQuery(GET_ALL_AGENTS);
	    List<Agent> list = q.getResultList();
	    em.close();
	    
	    return list;
	}

	@Override
	public void addAgent(Agent newAgent) {
	    EntityManager em = factory.createEntityManager();
	    em.getTransaction().begin();
	    em.persist(newAgent);
	    em.getTransaction().commit();
	    em.close();
	}

	@Override
	public void updateAgent(Agent agent) {
	    EntityManager em = factory.createEntityManager();
	    em.getTransaction().begin();
	    Agent old = em.find(Agent.class, agent.getId());
	    old.update(agent);
	    em.getTransaction().commit();
	    em.close(); 
	}

	@Override
	public void deleteAgent(Agent agent) {
	    EntityManager em = factory.createEntityManager();
	    em.getTransaction().begin();
	    Agent old = em.find(Agent.class, agent.getId());
	    em.remove(old);
	    em.getTransaction().commit();
	    em.close();
	}

}
