package com.capgemini.medicalbookingspringrest.dao;

import java.util.List;

import com.capgemini.medicalbookingspringrest.beans.CartBean;
import com.capgemini.medicalbookingspringrest.beans.OrderHistoryBean;

public interface CartDAO {

	public boolean insertProductCart(int userId, int productId, int quantity);

	public double doPayment(int userId);

	public boolean deleteCart(int userId);
	
	public boolean deleteOneProduct(int cartId);

	public List<CartBean> displayCart(int userId);

	// public List<OrderHistoryBean> displayOrderHistory(int userId);
	public List<OrderHistoryBean> displayOrderHistory(int userId , String address);

}
