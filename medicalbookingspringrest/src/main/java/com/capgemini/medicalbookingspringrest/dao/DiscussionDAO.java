package com.capgemini.medicalbookingspringrest.dao;

import java.util.List;

import com.capgemini.medicalbookingspringrest.beans.DiscussionBean;

public interface DiscussionDAO {
	public boolean insertQuestion(int userId, String question);

	public List<DiscussionBean> getQuestions();

	public boolean answerQuestion(int messageId , String answer);

	public List<DiscussionBean> displayAnswer();

}
