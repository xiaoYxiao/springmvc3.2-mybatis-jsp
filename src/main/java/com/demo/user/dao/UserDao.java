package com.demo.user.dao;

import java.util.List;
import com.demo.user.model.User;

public interface UserDao {
	public void insertUser(User user);
	public void updateUser(User user);
	public void deleteUser(Long uid);
	/**
	 * 进行模糊查询
	 * @param likeCondition
	 * @param page
	 * @return
	 */
	public List<User> getAllUser(User user);
	public Integer getAllUserCount(User user);
	public User getUser(Long uid);
}
