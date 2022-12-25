package com.examp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.examp.constents.FoodConstents;
import com.examp.repo.UserRepo;
import com.examp.service.UserService;
import com.examp.util.FoodUtils;

@RestController
public class UserController implements UserRepo{

	@Autowired
	private UserService userService;
	
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		try {
			return userService.signup(requestMap);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return FoodUtils.getResponseEntity(FoodConstents.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
