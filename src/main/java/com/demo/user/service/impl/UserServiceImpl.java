package com.demo.user.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.user.dao.AddressDao;
import com.demo.user.dao.UserDao;
import com.demo.user.model.Address;
import com.demo.user.model.User;
import com.demo.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Resource
	private UserDao userDao;
    @Resource
	private AddressDao addressDao;
	
	public void deleteUser(Long uid) {
		userDao.deleteUser(uid);
	}
	/**
	 * 查询出全部的User。
	 * @param page 分页页码。
	 * @param pageCount 页数。
	 */
	public List<User> getAllUser(User user) {
		List<User> users = userDao.getAllUser(user);
		return users;
	}
	
	@Override
	public int getAllUserCount(User user) {
        return userDao.getAllUserCount(user);
    }

	public User getOneUser(Long uid) {
		User user = userDao.getUser(uid);
		return user;
	}

	/**
	 * 通配查找需要的数据。
	 */
	public List<User> getUserNeeded(User user) {
		List<User> users = userDao.getAllUser(user);
		return users;
	}

	/**
	 * 级联插入Address
	 */
	public User insertUser(User user) {
		userDao.insertUser(user);
		List<Address> addrs = user.getAddrs();
//		int a = 9 / 0;
		if(addrs != null && addrs.size()>0){
			for(Address addr : addrs){
				addr.setUser(user);
				addressDao.insertAddress(addr);
			}
		}
		return user;
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}


}
