package com.example.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Category;
import com.example.entity.CategoryParent;
import com.example.service.CategoryParentService;
import com.example.service.CategoryService;
import com.example.utils.DataContants;


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
	public String managerMenu(Model model, Category category) {
		logger.info("Enter manager categories >>");
		
		List<CategoryParent> categoryParents = this.categoryParentService.categoryParents();
		model.addAttribute("categoryParents", categoryParents);
		
		
		List<Category> categories = new ArrayList<Category>();
		
		
		if (categoryParents.size() > 0) {
			for (CategoryParent cp : categoryParents) {
				List<Category> listCategory = this.categoryService.categories(cp.getId());
				categories.addAll(listCategory);
			}
		}
		
		model.addAttribute("categories", categories);
		
		return "admin/pages/categorys/manager-categories";
	}
	
	@PostMapping("/category/insert-update")
	public String inserOrUpdateCategory(@Valid Category category,
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		logger.info("Category parent Id: "+category.getParent_category_id().getId());
		logger.info("Category Id: "+category.getCategory_id());
		if (result.hasErrors()) {
			logger.error("inserOrUpdateCategory Failed !");
			redirectAttributes.addFlashAttribute("messageErrors", "inserOrUpdateCategory Failed !");
		}
		if (category.getCategory_id() == null || category.getCategory_id() == -1) {
			// insert 
			int rs = this.categoryService.insert(category);
			if (rs == DataContants.SUCCESS) {
				logger.info("Insert category success !");
			}
			redirectAttributes.addFlashAttribute("messageSuccesses", "inserOrUpdateCategory insert success!");
		}else {
			// update 
			int rs = this.categoryService.update(category);
			if (rs == DataContants.SUCCESS) {
				logger.info("Update category success !");
			}
			redirectAttributes.addFlashAttribute("messageSuccesses", "inserOrUpdateCategory Update success!");
		}
		return "redirect:/cms/category";
	}
	
	@PostMapping("/category/delete/{id}")
	public String deleteCategory(@PathVariable("id") Integer idCategory, RedirectAttributes redirectAttributes) {
		logger.info("Delete category");
		if (this.categoryService.delete(idCategory) == DataContants.SUCCESS) {
			logger.info("Delete category id: "+idCategory +" Success !");
			redirectAttributes.addFlashAttribute("messageSuccess", "delete category id: "+idCategory +"success");
			
		}else {
			logger.info("Delete category id: "+idCategory +"Failed !");
			redirectAttributes.addFlashAttribute("messageErrors", "delete category id: "+idCategory +"failed !");
		}
		return "redirect:/cms/category";
	}
	
	@PostMapping("/category/insert_update")
	public String insertCategory(@Valid Category category, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			logger.error("Insert or update failed !");
			redirectAttributes.addFlashAttribute("messageErrors", "insertCategory lỗi");
			
		}
		
		
		
		return "redirect:/cms/category";
	}
	
	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getCategoryById(Model model, @PathVariable("id") Integer id){
		logger.info("Get Category By Id:  "+id+" Start >>");
		
		Category category = this.categoryService.categoryById(id);
		
		
		return ResponseEntity.ok(category);
		
	}
	
	@RequestMapping("/error")
	@Override
	public String getErrorPath() {
		
		return "errorPage";
	}
	public static void main(String[] args) {
		
	}
	
	
}
