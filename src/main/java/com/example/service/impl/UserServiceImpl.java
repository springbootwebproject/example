package com.example.service.impl;



import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserDAO;
import com.example.entity.User;
import com.example.model.UserDTO;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	SimpleDateFormat sdf = new SimpleDateFormat("mm-DD-yyyy");
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public UserDTO findUserByUsername(String username) {
		logger.info("Find user by Username");
		UserDTO userDTO = new UserDTO();
		User user = userDAO.findUserByUsername(username);
		userDTO.setUser_id(String.valueOf(user.getUser_id()));
		userDTO.setUser_name(user.getUser_name());
		userDTO.setPassword(user.getPassword());
		userDTO.setEmail(user.getEmail());
		userDTO.setFull_name(user.getFull_name());
		userDTO.setCreated_date(sdf.format(user.getCreated_date()));
		return userDTO;
	}

	@Override
	public void saveUser(UserDTO userDTO) {
		User user = new User();
		
	}

	@Override
	public UserDTO findUserById(int id) {
		logger.info("Find user by Id");
		UserDTO userDTO = new UserDTO();
		User user = userDAO.findUserById(id);
		userDTO.setUser_id(String.valueOf(user.getUser_id()));
		userDTO.setUser_name(user.getUser_name());
		userDTO.setPassword(user.getPassword());
		userDTO.setEmail(user.getEmail());
		userDTO.setFull_name(user.getFull_name());
		userDTO.setCreated_date(sdf.format(user.getCreated_date()));
		return userDTO;
	}

	

	@Override
	public List<String> getAllPermission(int user_id) {
		logger.info("Start getAllPermission >>>");
		try {
			List<String> permissions = userDAO.getAllPermission(user_id);
			return permissions;
			
		} catch (Exception e) {
			logger.error("Query get all permission failed !");
			return null;
		}
		
	}

	
}
