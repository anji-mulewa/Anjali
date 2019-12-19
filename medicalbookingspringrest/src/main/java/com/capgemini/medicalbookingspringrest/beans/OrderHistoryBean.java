package com.capgemini.medicalbookingspringrest.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_history")
public class OrderHistoryBean {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	@Column
	private int userId;
	@Column
	private int productId;
	@Column
	private String productName;
	@Column
	private String userName;
	@Column
	private int quantity;
	@Column
	private double price;
	@Column
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderHistoryBean [orderId=" + orderId + ", userId=" + userId + ", productId=" + productId
				+ ", productName=" + productName + ", userName=" + userName + ", quantity=" + quantity + ", price="
				+ price + "]";
	}

}
