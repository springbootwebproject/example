package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dao.RoleDAO;
import com.example.dao.UserDAO;
import com.example.entity.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	private static Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RoleDAO  roleDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userDAO.findUserByUsername(username);
		if (user == null) {
			logger.info("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
       
		}
		List<String> roleNames = this.roleDAO.getRoleNames(user.getUser_id());
		 List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		  if (roleNames != null) {
	            for (String role : roleNames) {
	                // ROLE_USER, ROLE_ADMIN,..
	            	logger.info("role: " + role);
	                GrantedAuthority authority = new SimpleGrantedAuthority(role);
	                grantList.add(authority);
	            }
	        }
		
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantList);
		return userDetails;
	}

}
