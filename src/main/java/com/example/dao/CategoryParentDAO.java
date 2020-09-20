package com.example.dao;

import java.util.List;

import com.example.entity.CategoryParent;

public interface CategoryParentDAO {
		int insert(CategoryParent categoryParent);
		
		int update(CategoryParent categoryParent);
		
		int delete(int idCategory);
		
		List<CategoryParent> categoryParents();
		
		public CategoryParent categoryParentById(int idCategory);
}
