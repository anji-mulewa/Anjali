package com.capgemini.medicalbookingspringrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.medicalbookingspringrest.beans.CartBean;
import com.capgemini.medicalbookingspringrest.beans.MedicalResponse;
import com.capgemini.medicalbookingspringrest.beans.OrderHistoryBean;
import com.capgemini.medicalbookingspringrest.service.CartServiceImpl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*" , allowCredentials = "true")
public class CartRestController {
	@Autowired
	private CartServiceImpl cartService;

	@PostMapping(path = "/insertToCart", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponse insertToCart(@RequestParam int userId, @RequestParam int productId,
			@RequestParam int quantity) {
		boolean inserted = cartService.insertProductCart(userId, productId, quantity);
		MedicalResponse response = new MedicalResponse();
		if (inserted) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Product added to cart");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Product can't be added");
		}
		return response;
	}

	@GetMapping(path = "/displayCart")
	public MedicalResponse displayCart(@RequestParam int userId) {
		List<CartBean> cartList = cartService.displayCart(userId);
		MedicalResponse response = new MedicalResponse();
		if (cartList != null) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Products in your cart");
			response.setCartList(cartList);
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("No products in your cart");
		}
		return response;
	}

	@GetMapping(path = "/doPayment", produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponse doPayment(@RequestParam int userId) {
		double totalBill = cartService.doPayment(userId);
		MedicalResponse response = new MedicalResponse();
		if (totalBill > 0) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Payment done successfully");
			response.setPrice(totalBill);
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Payment not done");
		}
		return response;
	}

	@DeleteMapping(path = "/deleteOneProduct")
	public MedicalResponse deleteOneProduct(@RequestParam int cartId) {
		boolean deleted = cartService.deleteOneProduct(cartId);
		MedicalResponse response = new MedicalResponse();
		if (deleted) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Product removed from the cart successfully");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Product wasn't removed");
		}
		return response;
	}
	
	@DeleteMapping(path = "/deleteCart")
	public MedicalResponse deleteCart(@RequestParam int userId) {
		boolean deleted = cartService.deleteCart(userId);
		MedicalResponse response = new MedicalResponse();
		if (deleted) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Product removed from the cart successfully");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Product wasn't removed");
		}
		return response;
	}
	
	@GetMapping(path = "/displayOrderHistory")
	public MedicalResponse displayOrderHistory(@RequestParam int userId , @RequestParam String address) {
		MedicalResponse response = new MedicalResponse();
		List<OrderHistoryBean> orderList = cartService.displayOrderHistory(userId , address);
		if (orderList != null && !orderList.isEmpty()) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Order list displayed successfully");
			response.setOrderList(orderList);

		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Order list not present");
		}
		return response;
	}
}
