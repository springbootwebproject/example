package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/login-google")
	public String loginGoogle(HttpServletRequest request) {
		String code = request.getParameter("code");
		
		if (code == null || code.isEmpty()) {
			return "redirect:/login?google=error";
		}
		
		return null;
		
	}
}
