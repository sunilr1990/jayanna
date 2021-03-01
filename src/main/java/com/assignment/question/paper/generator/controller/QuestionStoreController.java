/*
 *
 */
package com.assignment.question.paper.generator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.question.papaer.generator.service.DataLoaderService;
import com.assignment.question.paper.generator.model.QuestionLoader;

/**
 * @author jayavardhan.hegde
 *
 */

@RestController
@RequestMapping("/store")
public class QuestionStoreController {

	@Autowired
	private DataLoaderService dataLoaderService;

	@GetMapping("/load/{questionType}")
	public ResponseEntity<Object> greeting(@PathVariable("questionType") String questionType) {
		List<QuestionLoader> questionLoader = dataLoaderService.loadQuestions(questionType.toUpperCase());
		return new ResponseEntity<Object>(questionLoader, HttpStatus.OK);
	}
}
