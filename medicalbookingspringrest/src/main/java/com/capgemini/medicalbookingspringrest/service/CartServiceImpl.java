package com.capgemini.medicalbookingspringrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.medicalbookingspringrest.beans.CartBean;
import com.capgemini.medicalbookingspringrest.beans.OrderHistoryBean;
import com.capgemini.medicalbookingspringrest.dao.CartDAOImpl;
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDAOImpl cartDao;
	
	@Override
	public boolean insertProductCart(int userId , int productId , int quantity) {
		return cartDao.insertProductCart(userId, productId, quantity);
	}

	@Override
	public List<CartBean> displayCart(int userId) {
		return cartDao.displayCart(userId);
	}

	@Override
	public double doPayment(int userId) {
		return cartDao.doPayment(userId);
	}

	@Override
	public boolean deleteCart(int userId) {
		return cartDao.deleteCart(userId);
	}

	@Override
	public List<OrderHistoryBean> displayOrderHistory(int userId , String address) {
		return cartDao.displayOrderHistory(userId , address);
	}

	@Override
	public boolean deleteOneProduct(int cartId) {
		return cartDao.deleteOneProduct(cartId);
	}

}
