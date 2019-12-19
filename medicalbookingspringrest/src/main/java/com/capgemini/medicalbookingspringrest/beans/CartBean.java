package com.capgemini.medicalbookingspringrest.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class CartBean {
	@Column
	private int productId;
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	@Column
	private int userId;
	@Column
	private String productName;
	@Column
	private String userName;
	@Column
	private double price;
	@Column
	private int quantity;
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartBean [productId=" + productId + ", cartId=" + cartId + ", userId=" + userId + ", productName="
				+ productName + ", userName=" + userName + ", price=" + price + ", quantity=" + quantity + "]";
	}
	
}
