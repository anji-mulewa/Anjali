package com.capgemini.medicalbookingspringrest.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.medicalbookingspringrest.beans.CartBean;
import com.capgemini.medicalbookingspringrest.beans.OrderHistoryBean;
import com.capgemini.medicalbookingspringrest.beans.ProductBean;
import com.capgemini.medicalbookingspringrest.beans.UserBean;

@Repository
public class CartDAOImpl implements CartDAO {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	@SuppressWarnings("unused")
	private int res;
	@SuppressWarnings("unused")
	private List<CartBean> cartList;

	@SuppressWarnings("unused")
	@Override
	public boolean insertProductCart(int userId, int productId, int quantity) {
		boolean inserted = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		String jpql = "from UserBean where userId=:userId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("userId", userId);
		UserBean user = (UserBean) query.getSingleResult();

		String jpql1 = "from ProductBean where productId =: productId";
		query = entityManager.createQuery(jpql1);
		query.setParameter("productId", productId);
		ProductBean product = (ProductBean) query.getSingleResult();
		CartBean cart = new CartBean();
		if (quantity < product.getQuantity()) {
			cart.setProductId(productId);
			cart.setProductName(product.getProductName());
			cart.setQuantity(quantity);
			cart.setUserId(userId);
			cart.setUserName(user.getUserName());
			cart.setPrice(product.getPrice() * quantity);
		}
		try {
			transaction.begin();
			entityManager.persist(cart);
			transaction.commit();
			inserted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		String jpql3 = "update ProductBean set quantity =:newQuantity where productId =: productId";
		query = entityManager.createQuery(jpql3);
		query.setParameter("newQuantity", product.getQuantity() - quantity);
		query.setParameter("productId", productId);
		transaction.begin();
		int res = query.executeUpdate();
		transaction.commit();
		return inserted;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CartBean> displayCart(int userId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "from CartBean where userId =: userId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("userId", userId);
		List<CartBean> cartList = new ArrayList<CartBean>();
		try {
			cartList = query.getResultList();
		} catch (Exception e) {
			System.out.println("No user id found");
		}
		return cartList;
	}

	@SuppressWarnings("unused")
	@Override
	public double doPayment(int userId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		List<OrderHistoryBean> orderList = new ArrayList<OrderHistoryBean>();
		List<CartBean> cartList = displayCart(userId);
		String jpql = "select sum(price) from CartBean where userId =: userId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("userId", userId);
		double price = (double) query.getSingleResult();

		for (CartBean c : cartList) {
			OrderHistoryBean orderBean = new OrderHistoryBean();
			orderBean.setPrice(c.getPrice());
			orderBean.setProductId(c.getProductId());
			orderBean.setProductName(c.getProductName());
			orderBean.setQuantity(c.getQuantity());
			orderBean.setUserId(c.getUserId());
			orderBean.setUserName(c.getUserName());
			transaction.begin();
			entityManager.persist(orderBean);
			transaction.commit();
		}
//		deleteCart(userId);
		return price;
	}

	@Override
	public boolean deleteCart(int userId) {
		boolean deleted = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		String jpql = "delete from CartBean where userId =: userId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("userId", userId);
//		cartList = displayCart(userId);
//		for (CartBean c : cartList) {
//			OrderHistoryBean orderBean = new OrderHistoryBean();
//			orderBean.setPrice(c.getPrice());
//			orderBean.setProductId(c.getProductId());
//			orderBean.setProductName(c.getProductName());
//			orderBean.setQuantity(c.getQuantity());
//			orderBean.setUserId(c.getUserId());
//			orderBean.setUserName(c.getUserName());
//			transaction.begin();
//			entityManager.persist(orderBean);
//			transaction.commit();
//		}
		try {
			transaction.begin();
			res = query.executeUpdate();
			transaction.commit();
			deleted = true;
		} catch (Exception e) {
			System.out.println("No products in cart");
		}
		return deleted;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderHistoryBean> displayOrderHistory(int userId  ,String address ) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//EntityTransaction transaction = entityManager.getTransaction();
		//=============
		// ===============
		
	cartList = displayCart(userId);
//		for (CartBean c : cartList) {
//			OrderHistoryBean orderBean = new OrderHistoryBean();
//			orderBean.setPrice(c.getPrice());
//			orderBean.setProductId(c.getProductId());
//			orderBean.setProductName(c.getProductName());
//			orderBean.setQuantity(c.getQuantity());
//			orderBean.setUserId(c.getUserId());
//			orderBean.setUserName(c.getUserName());
//			orderBean.setAddress(address);
//			transaction.begin();
//			entityManager.persist(orderBean);
//			transaction.commit();
//		}
		List<OrderHistoryBean> orderList = null;
		String jpql = "from OrderHistoryBean where userId =: userId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("userId", userId);
		try {
			orderList = query.getResultList();
		} catch (Exception e) {
			System.out.println("No orders found for this user");
			e.printStackTrace();
		}
		return orderList;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean deleteOneProduct(int cartId) {
		boolean deleted = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		String jpql = "delete from CartBean where cartId =: cartId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("cartId", cartId);
		try {
			transaction.begin();
			int res = query.executeUpdate();
			transaction.commit();
			deleted = true;
		} catch (Exception e) {
			System.out.println("No products in cart");
		}
		return deleted;
	}
}