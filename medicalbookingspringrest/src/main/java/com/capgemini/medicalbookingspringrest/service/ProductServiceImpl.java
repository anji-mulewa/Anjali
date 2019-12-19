package com.capgemini.medicalbookingspringrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.medicalbookingspringrest.beans.ProductBean;
import com.capgemini.medicalbookingspringrest.dao.ProductDAO;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDAO productDao;
	
	@Override
	public List<ProductBean> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public boolean updateProduct(ProductBean productBean) {
		return productDao.updateProduct(productBean);
	}

	@Override
	public boolean insertProduct(ProductBean product) {
		return productDao.insertProduct(product);
	}

	@Override
	public boolean deleteProduct(int productId) {
		return productDao.deleteProduct(productId);
	}
}
