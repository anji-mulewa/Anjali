package com.capgemini.medicalbookingspringrest.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="discussion")
public class DiscussionBean {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int messageId;
	@Column
	private int userId;
	@Column(name="question")
	private String question;	
	@Column(columnDefinition = "int default 0")
	private int status;
	@Column
	private String answer;

	
	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
