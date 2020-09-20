package com.example.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CategoryDAO;
import com.example.entity.Category;
import com.example.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@Override
	public int insert(Category category) {
		logger.info("insert category >>");
		return categoryDAO.insert(category);
	}

	@Override
	public int update(Category category) {
		logger.info("update category >>");
		return categoryDAO.update(category);
	}

	@Override
	public int delete(int idCategory) {
		logger.info("delete category >>");
		return categoryDAO.delete(idCategory);
	}

	@Override
	public List<Category> categories() {
		logger.info("Get categories >>");
		return categoryDAO.categories();
	}

	@Override
	public Category categoryById(int idCategory) {
		logger.info("Get category By Id >>");
		return categoryDAO.categoryById(idCategory);
	}

	@Override
	public List<Category> categories(int parentId) {
		logger.info("Get category By Parent Id >>");
		return categoryDAO.categories(parentId);
	}

}
