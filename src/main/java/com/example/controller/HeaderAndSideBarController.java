package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.entity.CategoryParent;
import com.example.service.CategoryParentService;

@ControllerAdvice(annotations = Controller.class)
public class HeaderAndSideBarController {
	
	@Autowired
	private CategoryParentService categoryParentService;
	
	@ModelAttribute
	public void category(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			String name = auth != null ? auth.getName() : "";
			
			if(name.contains("@")) {
				name = name.substring(0, name.indexOf("@"));
			}
			model.addAttribute("username", name);
			
			// Hiển thị Header
			boolean isAdmin = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
			model.addAttribute("isAdmin", isAdmin);
			

			// phần menu
			boolean hasRoleMenu = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_menu_permission"));
			model.addAttribute("hasRoleMenu", hasRoleMenu);
			
			// phần quản lý người dùng
			boolean hasRoleUser = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_user_manager"));
			model.addAttribute("hasRoleUser", hasRoleUser);

			// phần kinh doanh
			boolean hasRoleBusiness = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_bussiness_permission"));
			model.addAttribute("hasRoleBusiness", hasRoleBusiness);

			// phần quản lý TB
			boolean hasRoleTB = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_history_permission"));
			model.addAttribute("hasRoleTB", hasRoleTB);
			
			// quan ly danh muc 
			boolean hasRoleCategory = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_category"));
			model.addAttribute("hasRoleCategory",hasRoleCategory);
			
			// load category
			List<CategoryParent> listCategoryParent = this.categoryParentService.categoryParents();
			model.addAttribute("listCategoryParent", listCategoryParent);
		}
	}
	
}
