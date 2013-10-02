package ru.arkuzmin.costtracker.db.model.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ru.arkuzmin.costtracker.common.EMFSingleton;
import ru.arkuzmin.costtracker.common.Globals;
import ru.arkuzmin.costtracker.common.ListSizes;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.bean.Category;
import ru.arkuzmin.costtracker.db.bean.Cost;
import ru.arkuzmin.costtracker.db.model.CostService;
import ru.arkuzmin.costtracker.model.dto.CostFilter;

public class CostServiceImpl implements CostService {
	
	private static final String GET_ALL_COSTS = "select c from Cost c";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cost> getAllCosts() {
	    EntityManager em = EMFSingleton.getEMF().createEntityManager();
	    Query q = em.createQuery(GET_ALL_COSTS);
	    List<Cost> list = q.getResultList();
	    em.close();
	    
	    return list;
	}

	@Override
	public void addCost(Cost newCost, int agentId, int catId) {
		EntityManager em = EMFSingleton.getEMF().createEntityManager();
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
		EntityManager em = EMFSingleton.getEMF().createEntityManager();
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
		EntityManager em = EMFSingleton.getEMF().createEntityManager();
		em.getTransaction().begin();
		
		Cost c = em.find(Cost.class, cost.getId());
		em.remove(c);
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public List<Cost> getFilteredCosts(CostFilter filter) {
		
		EntityManager em = EMFSingleton.getEMF().createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cost> cq = cb.createQuery(Cost.class);
		Root<Cost> cost = cq.from(Cost.class);
		cq.select(cost);
		cq.orderBy(cb.desc(cost.get(Cost.DATE)));
		
		List<Predicate> criteria  = new ArrayList<Predicate>();
		
		if (filter.getName() != null && !"".equals(filter.getName())) {
			ParameterExpression<String> p = cb.parameter(String.class, "name");
			criteria.add(cb.like(cost.<String>get(Cost.NAME), p));
		}
		
		if (filter.getAgent() != null && filter.getAgent().getId() != Globals.FAKE_ID) {
			ParameterExpression<Agent> p = cb.parameter(Agent.class, "agent");
			criteria.add(cb.equal(cost.get(Cost.AGENT), p));
		}
		
		if (filter.getCat() != null && filter.getCat().getId() != Globals.FAKE_ID) {
			ParameterExpression<Category> p = cb.parameter(Category.class, "category");
			criteria.add(cb.equal(cost.get(Cost.CAT), p));
		}
		
		if (filter.getStartDt() != null) {
			ParameterExpression<Date> p = cb.parameter(Date.class, "stDate");
			criteria.add(cb.greaterThanOrEqualTo(cost.<Date>get(Cost.DATE), p));
		}
		
		if (filter.getEndDt() != null) {
			ParameterExpression<Date> p = cb.parameter(Date.class, "endDate");
			criteria.add(cb.lessThanOrEqualTo(cost.<Date>get(Cost.DATE), p));
		}
		
		if (filter.getAmount() != null) {
			ParameterExpression<Double> p = cb.parameter(Double.class, "amount");
			criteria.add(cb.equal(cost.get(Cost.AMOUNT), p));
		}
		
		
		if (criteria.size() == 1) {
			cq.where(criteria.get(0));
		} else if (criteria.size() > 0){
			cq.where(cb.and(criteria.toArray(new Predicate[0])));
		}
		
		TypedQuery<Cost> q = em.createQuery(cq);
		if (filter.getName() != null && !"".equals(filter.getName())) {
			q.setParameter("name", filter.getName());
		}
		if (filter.getAgent() != null && filter.getAgent().getId() != Globals.FAKE_ID) {
			q.setParameter("agent", filter.getAgent());
		}
		if (filter.getCat() != null && filter.getCat().getId() != Globals.FAKE_ID) {
			q.setParameter("category", filter.getCat());
		}
		if (filter.getStartDt() != null) {
			q.setParameter("stDate", filter.getStartDt());
		}
		if (filter.getEndDt() != null) {
			q.setParameter("endDate", filter.getEndDt());
		}
		if (filter.getAmount() != null) {
			q.setParameter("amount", filter.getAmount());
		}
		
		List<Cost> list = q.getResultList();
		
		/** Возвращаем только нужное количество записей */
		if (filter.getListSize() != null && !filter.getListSize().equals(ListSizes.ALL)) {
			list = list.subList(0, filter.getListSize().getSize() >= list.size() ? list.size() : filter.getListSize().getSize());
		}
		
		em.close();
		return list;
	}

	@Override
	public Double getTotalCosts() {
		Double result = Globals.UNDEFINED_DOUBLE;
		EntityManager em = EMFSingleton.getEMF().createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Double> cq = cb.createQuery(Double.class);
		
		Root<Cost> cost = cq.from(Cost.class);
		cq.select(cb.sum(cost.<Double>get(Cost.AMOUNT)));
		
		TypedQuery<Double> q = em.createQuery(cq);
		result = q.getSingleResult();
		
		em.close();
		return result;
	}

	@Override
	public Date getDateOfFirstCost() {
		Date result = null;
		EntityManager em = EMFSingleton.getEMF().createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Date> cq = cb.createQuery(Date.class);
		
		Root<Cost> cost = cq.from(Cost.class);
		cq.select(cb.least(cost.<Date>get(Cost.DATE)));
		
		TypedQuery<Date> q = em.createQuery(cq);
		result = q.getSingleResult();
		
		em.close();
		return result;
	}

	@Override
	public Date getDateOfLastCost() {
		Date result = null;
		EntityManager em = EMFSingleton.getEMF().createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Date> cq = cb.createQuery(Date.class);
		
		Root<Cost> cost = cq.from(Cost.class);
		cq.select(cb.greatest(cost.<Date>get(Cost.DATE)));
		
		TypedQuery<Date> q = em.createQuery(cq);
		result = q.getSingleResult();
		
		em.close();
		return result;
	}

	@Override
	public List<Cost> getLargestCosts(ListSizes size) {
		List<Cost> result = null;
		EntityManager em = EMFSingleton.getEMF().createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cost> cq = cb.createQuery(Cost.class);
		
		Root<Cost> cost = cq.from(Cost.class);
		cq.select(cost).orderBy(cb.desc(cost.get(Cost.AMOUNT)));
		
		TypedQuery<Cost> q = em.createQuery(cq);
		q.setMaxResults(size.getSize());
		
		result = q.getResultList();
		
		em.close();		
		return result;
	}
	
	
}
