package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	    
		private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
		@GetMapping(value = {"/","/welcome","/home","/index"})
	    public String admin() {
	        return "home";
	    }
		
		  
		@GetMapping("/demo")
	    public String demo() {
			  return "home";
	    }

}
