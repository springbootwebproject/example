package com.example.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CategoryParentDAO;
import com.example.entity.CategoryParent;
import com.example.service.CategoryParentService;

@Service
public class CategoryParentServiceImpl implements CategoryParentService {
	
	private static Logger logger = LoggerFactory.getLogger(CategoryParentServiceImpl.class);
	
	@Autowired
	private CategoryParentDAO categoryParentDAO;
	
	
	@Override
	public int insert(CategoryParent categoryParent) {
		logger.info("insert");
		return categoryParentDAO.insert(categoryParent);
	}

	@Override
	public int update(CategoryParent categoryParent) {
		logger.info("update");
		return categoryParentDAO.update(categoryParent);
	}

	@Override
	public int delete(int idCategory) {
		logger.info("delete");
		return categoryParentDAO.delete(idCategory);
	}

	@Override
	public List<CategoryParent> categoryParents() {
		logger.info("Get categoryparent start >> ");
		return categoryParentDAO.categoryParents();
	}

	@Override
	public CategoryParent categoryParentById(int idCategory) {
		logger.info("Get categoryParent by id start >> ");
		return categoryParentDAO.categoryParentById(idCategory);
	}

}
