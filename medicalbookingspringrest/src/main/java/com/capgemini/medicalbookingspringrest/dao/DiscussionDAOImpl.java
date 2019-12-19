package com.capgemini.medicalbookingspringrest.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.medicalbookingspringrest.beans.DiscussionBean;
@Repository
public class DiscussionDAOImpl implements DiscussionDAO {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public boolean insertQuestion(int userId, String question) {
		boolean inserted = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		DiscussionBean discussionBean = new DiscussionBean();
		discussionBean.setQuestion(question);
		discussionBean.setStatus(0);
		discussionBean.setAnswer("Will update the response soon");
		discussionBean.setUserId(userId);
		try {
			transaction.begin();
			entityManager.persist(discussionBean);
			transaction.commit();
			 inserted = true;
		} catch (Exception e) {
			System.out.println("Question can't be inserted");
		}
		return inserted;
	}

	@Override
	public List<DiscussionBean> getQuestions() {
		List<DiscussionBean> discussionList = new ArrayList<DiscussionBean>();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "from DiscussionBean where status = 0";
		Query query = entityManager.createQuery(jpql);
		try {
			discussionList = query.getResultList();
		} catch (Exception e) {
			System.out.println("Question can't be fetched");
			e.printStackTrace();
		}
		return discussionList;
	}

	@Override
	public boolean answerQuestion(int messageId, String answer) {
		boolean isAnswered = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		String jpql = "update DiscussionBean set status= 1 , answer =: ans where messageId = : messageId ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("ans", answer);
		query.setParameter("messageId", messageId);
		try {
			transaction.begin();
			int res = query.executeUpdate();
			transaction.commit();
			if(res>0) {
				isAnswered = true;
			}
		} catch (Exception e) {
			System.out.println("Answer not updated since message id not found");
		}
		return isAnswered;
	}

	@Override
	public List<DiscussionBean> displayAnswer() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<DiscussionBean> discussionList = new ArrayList<DiscussionBean>();
		String jpql ="from DiscussionBean";
		Query query = entityManager.createQuery(jpql);
		try {
			discussionList = query.getResultList();
			
		} catch (Exception e) {
			System.out.println("Discussion can't be displayed");
		}
		return discussionList;
	}
}
