package ru.arkuzmin.costtracker.db.model.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ru.arkuzmin.costtracker.common.Globals;
import ru.arkuzmin.costtracker.db.bean.Category;
import ru.arkuzmin.costtracker.db.model.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	private static EntityManagerFactory factory;
	
	private static final String GET_ALL_CATS = "select c from Category c";
	
	static {
		factory = Persistence.createEntityManagerFactory(Globals.PERSISTENCE_UNIT_NAME);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCats() {
		EntityManager em = factory.createEntityManager();
	    Query q = em.createQuery(GET_ALL_CATS);
	    List<Category> list = q.getResultList();
	    em.close();
	    
	    return list;
	}

	@Override
	public void addCategory(Category newCategory) {
		EntityManager em = factory.createEntityManager();
	    em.getTransaction().begin();
	    em.persist(newCategory);
	    em.getTransaction().commit();
	    em.close();
	}

	@Override
	public void updateCategory(Category category) {
		EntityManager em = factory.createEntityManager();
	    em.getTransaction().begin();
	    
	    Category old = em.find(Category.class, category.getId());
	    old.update(category);
	    
	    em.getTransaction().commit();
	    em.close();
	}

	@Override
	public void deleteCategory(Category category) {
		EntityManager em = factory.createEntityManager();
	    em.getTransaction().begin();
	    
	    Category cat = em.find(Category.class, category.getId());
	    em.remove(cat);
	    
	    em.getTransaction().commit();
	    em.close();
	}

}
