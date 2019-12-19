package com.capgemini.medicalbookingspringrest.service;

import java.util.List;

import com.capgemini.medicalbookingspringrest.beans.ProductBean;

public interface ProductService {
	public List<ProductBean> getAllProducts();

	public boolean updateProduct(ProductBean productBean);
	
	public boolean insertProduct(ProductBean product);

	public boolean deleteProduct(int productId);

}
