package com.capgemini.medicalbookingspringrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.medicalbookingspringrest.beans.DiscussionBean;
import com.capgemini.medicalbookingspringrest.dao.DiscussionDAO;
@Service
public class DiscussionServiceImpl implements DiscussionService {
	@Autowired
	private DiscussionDAO discussDao;

	@Override
	public boolean insertQuestion(int userId, String question) {
		return discussDao.insertQuestion(userId, question);
	}

	@Override
	public List<DiscussionBean> getQuestions() {
		return discussDao.getQuestions();
	}

	@Override
	public boolean answerQuestion(int messageId, String answer) {
		return discussDao.answerQuestion(messageId, answer);
	}

	@Override
	public List<DiscussionBean> displayAnswer() {
		return discussDao.displayAnswer();
	}
}
