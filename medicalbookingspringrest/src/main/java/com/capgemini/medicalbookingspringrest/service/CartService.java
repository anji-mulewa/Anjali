package com.capgemini.medicalbookingspringrest.service;

import java.util.List;

import com.capgemini.medicalbookingspringrest.beans.CartBean;
import com.capgemini.medicalbookingspringrest.beans.OrderHistoryBean;

public interface CartService {

	public boolean insertProductCart(int userId , int productId , int quantity);
	
	public List<CartBean> displayCart(int userId);
	
	public double doPayment(int userId);
	
	public boolean deleteCart(int userId);
	
	public boolean deleteOneProduct(int cartId);
	
	public List<OrderHistoryBean> displayOrderHistory(int userId , String address);
}
