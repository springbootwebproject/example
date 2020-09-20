package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController implements ErrorController{
		
		final static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	    @GetMapping("/login")
	    public String login() {
	    	logger.info("loginPage");
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    	//logger.info((SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken));
			logger.info(auth.getName());
			if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
				logger.info("K phải là anonymus");
				return "redirect:/";
			}
	        return "login";
	    }
	    
	    @GetMapping("/logout")
	    public String logoutPage(HttpServletRequest request,HttpServletResponse response) {
	    	logger.info("Loggout page");
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    	if (auth != null) {
	    		logger.info("auth not NULL");
	    		new SecurityContextLogoutHandler().logout(request, response, auth);
			}
	    	
	    	
	    	return "redirect:/login";
	    }
	    
	    @RequestMapping("/loginError")
		@Override
		public String getErrorPath() {
			
			return "errorPage";
		}
	   
}
