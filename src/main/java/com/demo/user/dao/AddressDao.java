package com.demo.user.dao;

import java.util.List;

import com.demo.user.model.Address;
import com.demo.user.model.Pagenation;

public interface AddressDao {
	public Integer insertAddress(Address addr);
	public void updateAddress(Address addr);
	public void deleteAddress(Long aid);
	public List<Address> allAddress(Pagenation page);
	public Address getAddress(Long aid);
}
