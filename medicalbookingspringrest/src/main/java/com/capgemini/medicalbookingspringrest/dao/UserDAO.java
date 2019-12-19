package com.capgemini.medicalbookingspringrest.dao;

import java.util.List;

import com.capgemini.medicalbookingspringrest.beans.AdminBean;
import com.capgemini.medicalbookingspringrest.beans.UserBean;

public interface UserDAO {

	public UserBean getUserLogin(String email, String password);

	public boolean register(UserBean user);

	public boolean changePassword(int userId , String password);

	public List<UserBean> getAllUsers();
	
	public boolean deleteUser(int userId);
}
