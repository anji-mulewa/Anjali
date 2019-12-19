package com.capgemini.medicalbookingspringrest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.medicalbookingspringrest.beans.ProductBean;

@Repository
public class ProductDAOImpl implements ProductDAO {
	@PersistenceUnit
	private EntityManagerFactory emf;

	@Override
	public List<ProductBean> getAllProducts() {
		EntityManager em = emf.createEntityManager();
		String jpql = "from ProductBean";
		Query query = em.createQuery(jpql);
		List<ProductBean> productList = null;
		try {
			productList = query.getResultList();
		} catch (Exception e) {
			System.out.println("Products not found");
		}
		return productList;
	}

	@Override
	public boolean updateProduct(ProductBean productBean) {
//		public boolean updateEmployee(EmployeeInfoBean employeeInfoBean) {
			EntityManager em = emf.createEntityManager();
			EntityTransaction transaction = em.getTransaction();
			ProductBean existingProductInfo = em.find(ProductBean.class, productBean.getProductId());
			boolean isUpdated = false;
			if (existingProductInfo != null) {
				String name = productBean.getProductName();
				if (name != null) {
					existingProductInfo.setProductName(name);
				}
				String category = productBean.getCategory();
				if(category !=null) {
					existingProductInfo.setCategory(category);
				}
				double price = productBean.getPrice();
				if(price !=0) {
					existingProductInfo.setPrice(price);
				}
				int quantity = productBean.getQuantity();
				if(quantity !=0) {
					existingProductInfo.setQuantity(quantity);
				}
			}

			try {
				transaction.begin();
				em.persist(existingProductInfo);
				transaction.commit();
				isUpdated = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			em.close();
			return isUpdated;
		
			}

	@Override
	public boolean insertProduct(ProductBean product) {
		boolean inserted = false;
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
		transaction.begin();
		em.persist(product);
		inserted = true;
		transaction.commit();
		} catch(Exception e) {
			System.out.println("Product can't be inserted");
		}
		return inserted;
	}

	@Override
	public boolean deleteProduct(int productId) {
		boolean deleted = false;
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		String jpql ="delete from ProductBean where productId=:pid";
		Query query = em.createQuery(jpql);
		query.setParameter("pid", productId);
		try {
			transaction.begin();
			int res = query.executeUpdate();
			transaction.commit();
			if(res>0) {
				deleted = true;
			} 
		} catch (Exception e) {
			System.out.println("Product id not found");
		}
		return deleted;
	}	
}