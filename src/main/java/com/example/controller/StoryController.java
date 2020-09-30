package com.example.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.StoryEntity;


@Controller
@RequestMapping("story")
public class StoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(StoryController.class);
	
	@RequestMapping(value = {"/"}, method = {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
	public String storyPage(Model model, StoryEntity storyEntity) {
		
		
		return "admin/pages/storys/story";
		
	}
	
	//public String 
}	
