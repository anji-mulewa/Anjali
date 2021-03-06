package com.capgemini.medicalbookingspringrest.service;

import java.util.List;

import com.capgemini.medicalbookingspringrest.beans.DiscussionBean;

public interface DiscussionService {
	
	public boolean insertQuestion(int userId , String question);
	
	public List<DiscussionBean> getQuestions();
	
	public boolean answerQuestion(int messageId , String answer);
	
	public List<DiscussionBean> displayAnswer();


}
