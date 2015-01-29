package com.taurusandchicken.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taurusandchicken.web.dao.UserDAO;
import com.taurusandchicken.web.module.User;

@Controller
public class UserNameValidationController {
	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/usernamevalidation", method = RequestMethod.POST)
	public @ResponseBody String hello(
			@RequestParam(value = "username") String userName) {

		if (userDAO.findByUserName(userName) == null) {
			return "valid";
		}
		return "invalid";

	}
}
