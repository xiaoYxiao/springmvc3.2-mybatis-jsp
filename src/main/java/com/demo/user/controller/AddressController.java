package com.demo.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.user.model.Address;
import com.demo.user.model.User;
import com.demo.user.service.AddressService;
import com.demo.user.service.UserService;

@Controller
@RequestMapping("demo/address/")
public class AddressController {
	@Resource
	private UserService userService;
	@Resource
	private AddressService addressService;
	
	@RequestMapping(value="addUserStepTwo", method=RequestMethod.POST)
	public void addUserStepTwo(HttpServletRequest req, HttpServletResponse resp, HttpSession session)
	  throws Exception{
		User user = (User)session.getAttribute("user");
		Address addr = new Address();
		addr.setAddress(req.getParameter("address"));
		addr.setPostCode(req.getParameter("postCode"));
		user.getAddrs().add(addr);
		userService.insertUser(user);
		session.removeAttribute("user");
		req.getRequestDispatcher("/demo/user/listUser.do").forward(req, resp);
	}
	
	@RequestMapping(value="toAddNewAddress", method=RequestMethod.GET)
	public String toAddNewAddress(@RequestParam Long uid, HttpServletRequest req){
		User user = userService.getOneUser(uid);
		req.setAttribute("user", user);
		return "addNewAddress";
	}
	
	@RequestMapping(value="addNewAddress", method=RequestMethod.POST)
	public String addNewAddress(@RequestParam Long uid, @RequestParam String address, @RequestParam String postCode){
		Address addr = new Address(address, postCode);
		User user = userService.getOneUser(uid);
		addr.setUser(user);
		addressService.insertAddress(addr);
		return "redirect:/demo/user/listUser.do";
	}
}
