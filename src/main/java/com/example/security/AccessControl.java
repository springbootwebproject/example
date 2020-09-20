package com.example.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.model.UserDTO;
import com.example.service.UserService;

@Component
public class AccessControl implements AuthenticationProvider{
	
	private static Logger logger = LoggerFactory.getLogger(AccessControl.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		final String username = authentication.getName();
		logger.info("Info: "+username);
		if (authentication.getCredentials() == null) {
			logger.info("Authentication is null: ");
		}
		final String password = authentication.getCredentials().toString();
		
		
		  UserDTO userDTO = userService.findUserByUsername(username); 
		 logger.info("User: "+userDTO.getUser_name()+" - "+userDTO.getPassword());
	     logger.info("Pass is correct: "+passwordEncoder.matches(password, userDTO.getPassword()));
	        
		
		
		//User user = new User();
		if (username.equals(userDTO.getUser_name()) && passwordEncoder.matches(password, userDTO.getPassword())) {
			
			logger.info("Login Success!");
			final List<GrantedAuthority> grantedAuths = new ArrayList<>();
			
			  // Gan danh sach cac quyen
           // logger.info(" Admin Config: "+this.getUsername());
			if ("admin".equals(userDTO.getUser_name())) {
				logger.info(userDTO.getUser_name() + " is Admin");	
				grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
			
			List<String> listPermision = userService.getAllPermission(Integer.parseInt(userDTO.getUser_id()));
			logger.info("Permission size", listPermision.size());
			for (String permiss : listPermision) {
				logger.info("permiss : " + permiss);
				grantedAuths.add(new SimpleGrantedAuthority("ROLE_"+permiss));
			}
			final UserDetails principal = new org.springframework.security.core.userdetails.User(username, password, grantedAuths);
			final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
			int  per = auth.getAuthorities().size();
			logger.info("Authorities size : "+per);
			boolean isAdmin = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
			logger.info("Check permission admin : "+isAdmin);
			
			 return auth;
		}else {
			return null;
		}
		
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
