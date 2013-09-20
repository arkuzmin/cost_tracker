package ru.arkuzmin.costtracker;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.bean.Category;
import ru.arkuzmin.costtracker.db.bean.Cost;

public class Main {
	 private static final String PERSISTENCE_UNIT_NAME = "cost";
	  private static EntityManagerFactory factory;

	  public static void main(String[] args) {
	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    EntityManager em = factory.createEntityManager();
	    
	    // Read the existing entries and write to console
	    Query q = em.createQuery("select c from Cost c");
	    List<Cost> todoList = q.getResultList();
	    for (Cost todo : todoList) {
	      System.out.println(todo);
	    }
	    
	    /*
	    em.getTransaction().begin();
	    Category cat = em.find(Category.class, 1);
	    Agent agent = em.find(Agent.class, 1);
	    Cost cost = new Cost();
	    cost.setAgent(agent);
	    cost.setCategory(cat);
	    cost.setName("Обед в Кроке");
	    cost.setDesc("Купил пиво");
	    cost.setAmount(100500);
	    cost.setDate(new Date());
	    em.persist(cost);
	    em.getTransaction().commit();
	    em.close();
	    */
	    
	    /*
	    em.getTransaction().begin();
	    Category cat = new Category();
	    cat.setName("Еда вне дома");
	    cat.setDesc("Затраты на еду вне дома");
	    em.persist(cat);
	    em.getTransaction().commit();
	    em.close();
	    
	    // Create new todo
	    em.getTransaction().begin();
	    Agent agent = new Agent();
	    agent.setName("Артём");
	    em.persist(agent);
	    em.getTransaction().commit();

	    em.close(); */
	  }
}
