package com.capgemini.medicalbookingspringrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.medicalbookingspringrest.beans.AdminBean;
import com.capgemini.medicalbookingspringrest.beans.UserBean;
import com.capgemini.medicalbookingspringrest.dao.UserDAO;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDAO userDao;
	
	@Override
	public UserBean getUserLogin(String email, String password) {
		return userDao.getUserLogin(email, password);
	}
 
	@Override
	public boolean register(UserBean user) {
		return userDao.register(user);
	}

	@Override
	public List<UserBean> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public boolean changePassword(int userId, String password) {
		return userDao.changePassword(userId, password);
	}

	@Override
	public boolean deleteUser(int userId) {
		return userDao.deleteUser(userId);
	}
}
