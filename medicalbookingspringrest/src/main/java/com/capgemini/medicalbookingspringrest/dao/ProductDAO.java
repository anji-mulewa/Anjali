package com.capgemini.medicalbookingspringrest.dao;

import java.util.List;

import com.capgemini.medicalbookingspringrest.beans.ProductBean;

public interface ProductDAO {
	public List<ProductBean> getAllProducts();

	public boolean insertProduct(ProductBean product);

	public boolean deleteProduct(int productId);

	public boolean updateProduct(ProductBean productBean);

	
}
