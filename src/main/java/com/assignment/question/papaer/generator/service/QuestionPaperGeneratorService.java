/*
 *
 */
package com.assignment.question.papaer.generator.service;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.collections4.map.MultiKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.question.paper.generator.config.AppConfig;
import com.assignment.question.paper.generator.constants.ApplicationConstant;
import com.assignment.question.paper.generator.constants.ErrorConstant;
import com.assignment.question.paper.generator.exception.QuestionLibraryException;
import com.assignment.question.paper.generator.model.Question;
import com.assignment.question.paper.generator.model.QuestionPaper;
import com.assignment.question.paper.generator.perstitant.storage.QuestionLibrary;
import com.assignment.question.paper.generator.util.QuestionPaperUtil;
import com.assignment.question.paper.generator.validator.ValidatorUtil;

/**
 * @author jayavardhan.hegde
 *
 */

@Service
public class QuestionPaperGeneratorService {

	@Autowired
	private QuestionPaperUtil questionPaperUtil;

	@Autowired
	private QuestionPaper questionPaper;

	@Autowired
	private AppConfig config;

	@Autowired
	private ValidatorUtil validatorUtil;

	public QuestionPaper generateQuestionPapaer(Integer totalMarks, String stratergy, Integer[] distributions) {
		// Validate if the distributions are proper
		validatorUtil.isDistributionProper(distributions);
		validatorUtil.isEnoughMarksAvailableToGeneratePaper(totalMarks);
		Set<Question> questionPaperSet = new HashSet<Question>();
		if (distributions.length != 0) {
			Map<String, Double> distributionMap = questionPaperUtil.calculateDistributionOfQuestions(totalMarks,
			        distributions);
			// questionPaperSet.addAll(extractQuestionsBasedOnMarks(distributionMap.get("DIFFICULT"),
			// QuestionLibrary.difficultQuestionList));
			//
			// questionPaperSet.addAll(
			// extractQuestionsBasedOnMarks(distributionMap.get("MEDIUM"),
			// QuestionLibrary.mediumQuestionList));
			questionPaperSet.addAll(extractQuestionsForMarksDistribution(distributionMap.get("EASY"),
			        QuestionLibrary.multiKeyEasyQuestionsMap, "EASY"));
			questionPaperSet.addAll(extractQuestionsForMarksDistribution(distributionMap.get("MEDIUM"),
			        QuestionLibrary.multiKeyMediumQuestionsMap, "MEDIUM"));
			questionPaperSet.addAll(extractQuestionsForMarksDistribution(distributionMap.get("DIFFICULT"),
			        QuestionLibrary.multiKeyDifficultQuestionsMap, "DIFFICULT"));
			// questionPaperSet.addAll(
			// extractQuestionsBasedOnMarks(distributionMap.get("EASY"),
			// QuestionLibrary.easyQuestionList));
		}
		// get the total no of marks in the set == 70
		// -- delta = 30 == 30/5 = 6
		// reminder in range of 10 > easy
		// reminder in range of 11 tp 20 > medium + difficult
		// Pick any item from the set and increase the marks for that object
		questionPaper.setTotalMarks(totalMarks);
		questionPaper.setTotalQuestions(questionPaperSet.size());
		questionPaper.setDuration(ApplicationConstant.DURATION);
		questionPaper.setInstructions(ApplicationConstant.INSTRUCTIONS);
		questionPaper.setQuestions(questionPaperSet);
		return questionPaper;
	}

	public QuestionPaper generateQuestionPapaerSet(Integer totalMarks, String stratergy, Integer[] distributions) {
		// Validate if the distributions are proper
		validatorUtil.isDistributionProper(distributions);
		validatorUtil.isEnoughMarksAvailableToGeneratePaper(totalMarks);
		Map<String, Double> distributionMap = questionPaperUtil.calculateDistributionOfQuestions(totalMarks,
		        distributions);

		return null;
	}

	private Set<Question> extractQuestionsBasedOnMarks(Double marks, List<Question> questionList) {
		Set<Question> questionPaperSet = new HashSet<Question>();
		if (marks == 0) {
			return questionPaperSet;
		}
		Integer generatedMarks = 0;
		try {
			while (generatedMarks < marks) {
				Random rand = new Random();
				int randomIndex = rand.nextInt(questionList.size());
				Question question = questionList.get(randomIndex);
				generatedMarks = generatedMarks + question.getMarks();
				questionPaperSet.add(question);
			}
			return questionPaperSet;
		} catch (Exception e) {
			throw new QuestionLibraryException(ErrorConstant.QUESTION_LIBRARY_EMPTY,
			        config.messageSource().getMessage(ErrorConstant.QUESTION_LIBRARY_EMPTY, null, Locale.ENGLISH));
		}
	}

	private Set<Question> extractQuestionsForMarksDistribution(Double marks, MultiKeyMap questionMap, String type) {
		Set<Question> questionPaperSet = new HashSet<Question>();
		Double noOfQuestions = marks / 5;
		Integer generatedMarks = 1;
		while (generatedMarks <= noOfQuestions) {
			Question question = (Question) questionMap.get(generatedMarks, type);
			questionPaperSet.add(question);
			generatedMarks++;
		}
		return questionPaperSet;
	}

}
