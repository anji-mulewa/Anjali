package com.capgemini.medicalbookingspringrest.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.medicalbookingspringrest.beans.MedicalResponse;
import com.capgemini.medicalbookingspringrest.beans.UserBean;
import com.capgemini.medicalbookingspringrest.service.UserService;

@RestController
@CrossOrigin(origins  = "*" , allowedHeaders = "*", allowCredentials="true")
public class UserRestController {
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/getUserLogin")
			/*produces = MediaType.APPLICATION_JSON_VALUE ,
			consumes = MediaType.APPLICATION_JSON_VALUE)*/
	public MedicalResponse getUserLogin(@RequestParam String email ,@RequestParam String password) {
		UserBean userBean = new UserBean();
		MedicalResponse response = new MedicalResponse();
		userBean = userService.getUserLogin(email, password);
		if(userBean != null) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Logged in successfully...");
			response.setUserBean(userBean);
		} else {
			response.setStatusCode(401);
			response.setMessage("Unsuccessful");
			response.setDescription("Unable to login");
		}
		return response;
	}
	
	@PutMapping(path = "/registerUser", produces=MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponse registerUser (@RequestBody UserBean user) {
		boolean registered = userService.register(user);
		MedicalResponse response = new MedicalResponse();
		if(registered) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("User registered successfully...");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Unable to register");
		}
		return response;
	}
	@GetMapping(path = "/getAllUsers")
	public MedicalResponse getAllUsers() {
		MedicalResponse response = new MedicalResponse();
		List<UserBean> usersList = userService.getAllUsers();
		if(usersList != null) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Users list found");
			response.setUserLists(usersList);
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Unable to fetch user list");
		}
		return response;
	}
	@PostMapping(path = "/changePassword")
	public MedicalResponse changePassword(@RequestParam int userId ,@RequestParam String password) {
		MedicalResponse response = new MedicalResponse();
		boolean changed = userService.changePassword(userId, password);
		if(changed) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Password changed successfully");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Unable to change password");
		}
		return response;
	}

	@DeleteMapping(path = "/deleteUser" /* , consumes = MediaType.APPLICATION_JSON_VALUE */)
	public MedicalResponse deleteUser(@RequestParam int userId) {
		MedicalResponse response = new MedicalResponse();
		boolean deleted = userService.deleteUser(userId);
		if(deleted) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("User deleted successfully");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Unable to delete user");
		}
		return response;
	}
}
