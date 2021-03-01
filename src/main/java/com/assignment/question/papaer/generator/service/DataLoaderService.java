/*
 *
 */
package com.assignment.question.papaer.generator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.question.paper.generator.config.ApplicationProperties;
import com.assignment.question.paper.generator.data.loader.DifficultQuestionLoader;
import com.assignment.question.paper.generator.data.loader.EasyQuestionLoader;
import com.assignment.question.paper.generator.data.loader.MediumQuestionLoader;
import com.assignment.question.paper.generator.data.loader.QuestionContext;
import com.assignment.question.paper.generator.model.QuestionLoader;

/**
 * @author jayavardhan.hegde
 *
 */

@Service
public class DataLoaderService {

	@Autowired
	private ApplicationProperties properties;

	public List<QuestionLoader> loadQuestions(String questionType) {
		QuestionContext questionContext;
		List<QuestionLoader> questionLoaderList = new ArrayList<>();
		switch (questionType) {
		case "EASY":
			questionContext = new QuestionContext(new EasyQuestionLoader(properties.getMetaData()));
			questionLoaderList.add(questionContext.loadQuestion());
			break;

		case "MEDIUM":
			questionContext = new QuestionContext(new MediumQuestionLoader(properties.getMetaData()));
			questionLoaderList.add(questionContext.loadQuestion());
			break;

		case "DIFFICULT":
			questionContext = new QuestionContext(new DifficultQuestionLoader(properties.getMetaData()));
			questionLoaderList.add(questionContext.loadQuestion());
			break;

		case "ALL":
			questionContext = new QuestionContext(new EasyQuestionLoader(properties.getMetaData()));
			questionLoaderList.add(questionContext.loadQuestion());

			questionContext = new QuestionContext(new MediumQuestionLoader(properties.getMetaData()));
			questionLoaderList.add(questionContext.loadQuestion());

			questionContext = new QuestionContext(new DifficultQuestionLoader(properties.getMetaData()));
			questionLoaderList.add(questionContext.loadQuestion());

			break;
		default:
			break;
		}
		return questionLoaderList;
	}
}
