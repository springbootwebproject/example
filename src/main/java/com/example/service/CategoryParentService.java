package com.example.service;

import java.util.List;

import com.example.entity.CategoryParent;

public interface CategoryParentService {
	int insert(CategoryParent categoryParent);
	
	int update(CategoryParent categoryParent);
	
	int delete(int idCategory);
	
	List<CategoryParent> categoryParents();
	
	public CategoryParent categoryParentById(int idCategory);
}
