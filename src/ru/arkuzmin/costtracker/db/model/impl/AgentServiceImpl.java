package ru.arkuzmin.costtracker.db.model.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ru.arkuzmin.costtracker.common.EMFSingleton;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.model.AgentService;

public class AgentServiceImpl implements AgentService {

	private static final String GET_ALL_AGENTS = "select a from Agent a";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Agent> getAllAgents() {
	    EntityManager em = EMFSingleton.getEMF().createEntityManager();
	    Query q = em.createQuery(GET_ALL_AGENTS);
	    List<Agent> list = q.getResultList();
	    em.close();
	    
	    return list;
	}

	@Override
	public void addAgent(Agent newAgent) {
	    EntityManager em = EMFSingleton.getEMF().createEntityManager();
	    em.getTransaction().begin();
	    em.persist(newAgent);
	    em.getTransaction().commit();
	    em.close();
	}

	@Override
	public void updateAgent(Agent agent) {
	    EntityManager em = EMFSingleton.getEMF().createEntityManager();
	    em.getTransaction().begin();
	    Agent old = em.find(Agent.class, agent.getId());
	    old.update(agent);
	    em.getTransaction().commit();
	    em.close(); 
	}

	@Override
	public void deleteAgent(Agent agent) {
	    EntityManager em = EMFSingleton.getEMF().createEntityManager();
	    em.getTransaction().begin();
	    Agent old = em.find(Agent.class, agent.getId());
	    em.remove(old);
	    em.getTransaction().commit();
	    em.close();
	}

}
