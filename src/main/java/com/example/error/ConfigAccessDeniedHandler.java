package com.example.error;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class ConfigAccessDeniedHandler implements AccessDeniedHandler{
	

	final static Logger logger = LoggerFactory.getLogger(ConfigAccessDeniedHandler.class);
	
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
			
			logger.info(request.getContextPath());
		
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			if (auth != null) {
				request.getRequestURI();
			}
			response.sendRedirect(request.getContextPath() + "templates/errors/403");
	}

}
