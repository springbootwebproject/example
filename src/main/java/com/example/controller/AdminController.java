package com.example.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.Category;
import com.example.entity.CategoryParent;
import com.example.service.CategoryParentService;
import com.example.service.CategoryService;


@Controller
@RequestMapping("/cms")
public class AdminController implements ErrorController{
	
	final static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	CategoryParentService categoryParentService;
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = {"/admin"}, method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
	public String welcomePage(Model model) {
		logger.info("to welcome page");
		model.addAttribute("title", "Welcome my CMS");
		model.addAttribute("message", "This is welcome page! ");

		return "admin/pages/welcome";
		
	}
	
	@RequestMapping(value = {"/category"}, method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
	public String managerMenu(Model model, CategoryParent categoryParent) {
		logger.info("Enter manager categories >>");
		
		List<CategoryParent> categoryParents = this.categoryParentService.categoryParents();
		model.addAttribute("categoryParents", categoryParents);
		
		
		List<List<Category>> categories = new ArrayList<List<Category>>();
		
	/*	
		if (categoryParents.size() > 0) {
			for (CategoryParent cp : categoryParents) {
				List<Category> listCategory = this.categoryService.categories(cp.getId());
				categories.add(listCategory);
			}
		}*/
		model.addAttribute("categories", categories);
		
		return "admin/pages/categorys/manager-categories";
	}
	
	@RequestMapping("/error")
	@Override
	public String getErrorPath() {
		
		return "errorPage";
	}
	
}
