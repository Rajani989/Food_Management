package com.examp.serviceImpl;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examp.constents.FoodConstents;
import com.examp.entity.UserEntity;
import com.examp.repository.UserRepository;
import com.examp.service.UserService;
import com.examp.util.FoodUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public ResponseEntity<String> signup(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		log.info("Inside signUp {}", requestMap);
		try {
		if(validateSignUpMap(requestMap)) {
		   UserEntity userEntity =userRepository.findByEmailId(requestMap.get("email"));
		   if(Objects.isNull(userEntity)) {
			   userRepository.save(getUserFromMap(requestMap));
			   return FoodUtils.getResponseEntity("SuccessFully Registered", HttpStatus.OK);
		   }
		   else {
			   return FoodUtils.getResponseEntity("Email already exsits.",HttpStatus.BAD_REQUEST);
		   }
		}
		else {
			return FoodUtils.getResponseEntity(FoodConstents.INVALID_DATA, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return FoodUtils.getResponseEntity(FoodConstents.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	private boolean validateSignUpMap(Map<String, String> requestMap) {
		if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber") 
				&& requestMap.containsKey("email") && requestMap.containsKey("pass")
				&& requestMap.containsKey("gender") && requestMap.containsKey("address"))
		{
			return true;
		}
		else {
			return false;
		}
	}

	
	private UserEntity getUserFromMap(Map<String, String> requestmap) {
		UserEntity userEntity=new UserEntity();
		userEntity.setName(requestmap.get("name"));
		userEntity.setContactNumber(requestmap.get("contactNumber"));
		userEntity.setEmail(requestmap.get("email"));
		userEntity.setPass(requestmap.get("pass"));
		userEntity.setGender(requestmap.get("gender"));
		userEntity.setAddress(requestmap.get("address"));
		userEntity.setStatus("false");
		userEntity.setRole("user");
		return userEntity;
	}
}
