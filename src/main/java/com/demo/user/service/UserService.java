package com.demo.user.service;

import java.util.List;
import java.util.Map;

import com.demo.user.model.User;

public interface UserService {
	public User insertUser(User user);
	public void deleteUser(Long uid);
	public void updateUser(User user);
	
	public List<User> getAllUser(User user);
	public List<User> getUserNeeded(User user);
	public User getOneUser(Long uid);
    int getAllUserCount(User user);
}
