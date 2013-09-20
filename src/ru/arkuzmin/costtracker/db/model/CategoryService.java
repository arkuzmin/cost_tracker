package ru.arkuzmin.costtracker.db.model;

import java.util.List;

import ru.arkuzmin.costtracker.db.bean.Category;

public interface CategoryService {
	
	public List<Category> getAllCats();
	public void addCategory(Category newCategory);
	public void updateCategory(Category category);
	public void deleteCategory(Category category);
}
