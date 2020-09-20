package com.example.dao;

import java.util.List;

import com.example.entity.User;

public interface UserDAO {
	public User findUserByUsername(String username);
	
	public User findUserById(int id);
	
	public List<User> getListUser();
	
	public int save(User user);
	
	public int delete(int id);
	
	public int updatePassword(User user);
	
	public List<User> getListUser(Integer page, Integer pageSize);
	
	public List<String> getAllPermission(Integer user_id);
}
