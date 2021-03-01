/*
 *
 */
package com.assignment.question.paper.generator.perstitant.storage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.map.MultiKeyMap;

import com.assignment.question.paper.generator.model.Question;

/**
 * @author jayavardhan.hegde
 *
 */

public class QuestionLibrary {

	public static Integer TOTAL_MARKS_AVAILABLE = 0;

	public static Integer QUESTION_INDEX = 1;

	public static List<Question> easyQuestionList = new ArrayList<Question>();

	public static List<Question> mediumQuestionList = new ArrayList<Question>();

	public static List<Question> difficultQuestionList = new ArrayList<Question>();

	public static MultiKeyMap multiKeyEasyQuestionsMap = new MultiKeyMap();

	public static MultiKeyMap multiKeyMediumQuestionsMap = new MultiKeyMap();

	public static MultiKeyMap multiKeyDifficultQuestionsMap = new MultiKeyMap();

}
