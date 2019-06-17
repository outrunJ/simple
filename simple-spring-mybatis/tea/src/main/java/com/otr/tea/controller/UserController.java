package com.otr.tea.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.otr.tea.service.UserService;

@Controller
@RequestMapping(value="/", method={RequestMethod.GET, RequestMethod.POST})
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest req, HttpServletResponse res){
		return "hellojsp";
		
	}
}
