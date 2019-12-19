package com.capgemini.medicalbookingspringrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.medicalbookingspringrest.beans.DiscussionBean;
import com.capgemini.medicalbookingspringrest.beans.MedicalResponse;
import com.capgemini.medicalbookingspringrest.service.DiscussionService;

@RestController
@CrossOrigin(origins  = "*" , allowedHeaders = "*" , allowCredentials = "true")
public class DiscussionRestController {

	@Autowired
	private DiscussionService discussService;

	@PostMapping(path = "/insertQuestion", /* consumes = MediaType.APPLICATION_JSON_VALUE , */
			produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponse inserQuestion(@RequestParam int userId,@RequestParam String question) {
		boolean inserted = discussService.insertQuestion(userId, question);
		MedicalResponse response = new MedicalResponse();
		if (inserted) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Question inserted successfully");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Question can't be inserted");
		}
		return response;
	}
	@GetMapping(path = "/getQuestion" ,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponse getQuestions() {
		List<DiscussionBean> discussionList = discussService.getQuestions();
		MedicalResponse response = new MedicalResponse();
		if(discussionList != null) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Question fetched successfully");
			response.setDiscussionList(discussionList);
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Question can't be fetched");
		}	
		return response;
	}
	@PostMapping(path = "/answerQuestion", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponse answerQuestion(@RequestParam int messageId , @RequestParam String answer) {
		MedicalResponse response = new MedicalResponse();
		boolean isAnswered = discussService.answerQuestion(messageId, answer);
		if(isAnswered) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Answer updated successfully");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Answer can't be updated");
		}
		return response;
	}
	@GetMapping(path = "/getAnswer")
	public MedicalResponse getAnswer() {
		MedicalResponse response = new MedicalResponse();
		List<DiscussionBean> discussionList = discussService.displayAnswer();
		if(discussionList != null) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Answers fetched successfully");
			response.setDiscussionList(discussionList);
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Answers can't be fetched");
		}
		return response;
	}
}
