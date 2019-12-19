package com.capgemini.medicalbookingspringrest.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class MedicalResponse {
	private int statusCode;
	private String message;
	private String description;
	private List<UserBean> userLists;
	private List<ProductBean> productLists;
	private List<DiscussionBean> discussionList;
	private List<CartBean> cartList;
	private double price;
	private List<OrderHistoryBean> orderList;
	private UserBean userBean;

	
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public List<OrderHistoryBean> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderHistoryBean> orderList) {
		this.orderList = orderList;
	}

	public List<DiscussionBean> getDiscussionList() {
		return discussionList;
	}

	public void setDiscussionList(List<DiscussionBean> discussionList) {
		this.discussionList = discussionList;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserBean> getUserLists() {
		return userLists;
	}

	public void setUserLists(List<UserBean> userLists) {
		this.userLists = userLists;
	}

	public List<ProductBean> getProductLists() {
		return productLists;
	}

	public void setProductLists(List<ProductBean> productLists) {
		this.productLists = productLists;
	}

	public List<CartBean> getCartList() {
		return cartList;
	}

	public void setCartList(List<CartBean> cartList) {
		this.cartList = cartList;
	}

}
