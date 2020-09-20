package com.example.service;

import java.util.List;

import com.example.entity.Category;

public interface CategoryService {
	
	public int insert(Category category);
	
	public int update(Category category);
	
	public int delete(int idCategory);
	
	public List<Category> categories();
	
	public Category categoryById(int idCategory);
	
	public List<Category> categories(int parentId);
}
