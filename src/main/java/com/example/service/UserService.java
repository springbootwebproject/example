package com.example.service;

import java.util.List;

import com.example.model.UserDTO;


public interface UserService{
	public UserDTO findUserByUsername(String username);
	
	public void saveUser(UserDTO user);
	
	public UserDTO findUserById(int id);
	
	List<String> getAllPermission(int user_id);
	
}
