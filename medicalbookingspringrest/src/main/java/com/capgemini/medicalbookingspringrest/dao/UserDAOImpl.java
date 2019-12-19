package com.capgemini.medicalbookingspringrest.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.medicalbookingspringrest.beans.AdminBean;
import com.capgemini.medicalbookingspringrest.beans.UserBean;

@Repository
public class UserDAOImpl implements UserDAO {
	@PersistenceUnit
	private EntityManagerFactory emf;

	@Override
	public UserBean getUserLogin(String email, String password) {
		EntityManager entityManager = emf.createEntityManager();
		String jpql = "from UserBean where userEmail =:email and userPassword =:password";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("email", email);
		query.setParameter("password", password);
		UserBean user = null;
		try {
			user = (UserBean) query.getSingleResult();

		} catch (Exception e) {
			System.out.println("No such user found");
		}
		return user;
	}// end of user getLogin()

	@Override
	public boolean register(UserBean user) {
		boolean registered = false;
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(user);
			transaction.commit();
			registered = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registered;
	}

	@Override
	public List<UserBean> getAllUsers() {
		List<UserBean> users = new ArrayList<UserBean>();
		EntityManager entityManager = emf.createEntityManager();
		//EntityTransaction transaction = em.getTransaction();
		String jpql = "from UserBean where type='user'";
		Query query = entityManager.createQuery(jpql);
		try {
			users = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public boolean changePassword(int userId , String password) {
		boolean changed = false;
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		String jpql ="update UserBean set userPassword=: password where userId=:uid";
		Query query = entityManager.createQuery(jpql);
		try {
			transaction.begin();
			query.setParameter("password", password);
			query.setParameter("uid", userId);
			int res = query.executeUpdate();
			transaction.commit();
			if(res>0) {
			changed = true;
			} else {
				changed = false;
			}
		} catch (Exception e) {
			System.out.println("User id not found");
		}
		return changed;
	}

	@Override
	public boolean deleteUser(int userId) {
		boolean deleted =false;
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		String jpql = "delete from UserBean where userId = : uid";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("uid", userId);
		try {
			transaction.begin();
			int res = query.executeUpdate();
			transaction.commit();
			if(res>0) {
				deleted = true;
			}
		} catch (Exception e) {
			System.out.println("User id not found");
		}
		return deleted;
	}
}
