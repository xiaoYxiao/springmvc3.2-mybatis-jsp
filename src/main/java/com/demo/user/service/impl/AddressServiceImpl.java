package com.demo.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.user.dao.AddressDao;
import com.demo.user.model.Address;
import com.demo.user.service.AddressService;
@Service
public class AddressServiceImpl implements AddressService{
    @Resource
	private AddressDao addressDao;
	
	public void insertAddress(Address addr) {
		Integer aid = addressDao.insertAddress(addr);
	}
}
