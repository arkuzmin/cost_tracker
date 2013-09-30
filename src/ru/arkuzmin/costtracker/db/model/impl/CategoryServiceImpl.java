package ru.arkuzmin.costtracker.db.model.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ru.arkuzmin.costtracker.common.EMFSingleton;
import ru.arkuzmin.costtracker.db.bean.Category;
import ru.arkuzmin.costtracker.db.model.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	private static final String GET_ALL_CATS = "select c from Category c";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCats() {
		EntityManager em = EMFSingleton.getEMF().createEntityManager();
	    Query q = em.createQuery(GET_ALL_CATS);
	    List<Category> list = q.getResultList();
	    em.close();
	    
	    return list;
	}

	@Override
	public void addCategory(Category newCategory) {
		EntityManager em = EMFSingleton.getEMF().createEntityManager();
	    em.getTransaction().begin();
	    em.persist(newCategory);
	    em.getTransaction().commit();
	    em.close();
	}

	@Override
	public void updateCategory(Category category) {
		EntityManager em = EMFSingleton.getEMF().createEntityManager();
	    em.getTransaction().begin();
	    
	    Category old = em.find(Category.class, category.getId());
	    old.update(category);
	    
	    em.getTransaction().commit();
	    em.close();
	}

	@Override
	public void deleteCategory(Category category) {
		EntityManager em = EMFSingleton.getEMF().createEntityManager();
	    em.getTransaction().begin();
	    
	    Category cat = em.find(Category.class, category.getId());
	    em.remove(cat);
	    
	    em.getTransaction().commit();
	    em.close();
	}

}
