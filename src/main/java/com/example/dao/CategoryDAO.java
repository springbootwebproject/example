package com.example.dao;

import java.util.List;

import com.example.entity.Category;

public interface CategoryDAO {
	public int insert(Category category);
	
	public int update(Category category);
	
	public int delete(int idCategory);
	
	public List<Category> categories();
	
	public Category categoryById(int idCategory);
	
	public List<Category> categories(int parentId);
	
	public Category getCategoryByParentId(int parentId);
}
