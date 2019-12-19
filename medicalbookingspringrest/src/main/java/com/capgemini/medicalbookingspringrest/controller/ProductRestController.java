package com.capgemini.medicalbookingspringrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.medicalbookingspringrest.beans.MedicalResponse;
import com.capgemini.medicalbookingspringrest.beans.ProductBean;
import com.capgemini.medicalbookingspringrest.service.ProductService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*" , allowCredentials = "true")
public class ProductRestController {
	@Autowired
	private ProductService productService;
	@GetMapping(path ="/getAllProducts")
	public MedicalResponse getAllProducts() {
		List<ProductBean> productList = productService.getAllProducts();
		MedicalResponse response = new MedicalResponse();
		if(productList != null) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Product list...");
			response.setProductLists(productList);
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Products can't be fetched");
		}
		return response;
	}
	@PutMapping(path =  "/updateProducts", consumes = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponse updateProduct(@RequestBody ProductBean productBean) {
		boolean updated = productService.updateProduct(productBean);
		MedicalResponse response = new MedicalResponse();
		if(updated) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Product updated successfully");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Product can't be updated");
		}
		return response;
	}
	
	@PutMapping(path = "/insertProduct" ,consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponse insertProduct(@RequestBody ProductBean product) {
		boolean inserted = productService.insertProduct(product);
		MedicalResponse response = new MedicalResponse();
		if(inserted) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Product inserted successfully");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Product can't be inserted");
		}
		return response;
	}
	
	@DeleteMapping(path = "/deleteProduct")
	public MedicalResponse deleteProduct(@RequestParam int productId) {
		boolean deleted = productService.deleteProduct(productId);
		MedicalResponse response = new MedicalResponse();
		if(deleted) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Product deleted successfully");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Product not deleted");
		}
		return response;
	}
}
