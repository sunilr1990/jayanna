/*
 *
 */
package com.assignment.question.paper.generator.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author jayavardhan.hegde
 *
 */

@Component
public class QuestionPaperUtil {

	// public Map<String, Double> calculateDistributionOfQuestions(Integer
	// totalMarks, Integer[] distributions) {
	// Map<String, Double> distributionMap = new HashMap<String, Double>();
	// int distributionCount = 0;
	// for (Integer distribution : distributions) {
	// double marks = distribution * totalMarks / 100;
	// distributionCount++;
	// if (distributionCount == 1) {
	// // distributionMap.put("EASY", Math.floor(marks));
	// distributionMap.put("EASY", 5 * (Math.ceil(Math.abs(marks / 5))));
	// } else if (distributionCount == 2) {
	// distributionMap.put("MEDIUM", 5 * (Math.ceil(Math.abs(marks / 5))));
	// } else if (distributionCount == 3) {
	// distributionMap.put("DIFFICULT", 5 * (Math.ceil(Math.abs(marks / 5))));
	// }
	// }
	// return distributionMap;
	// }

	public Map<String, Double> calculateDistributionOfQuestions(Integer totalMarks, Integer[] distributions) {
		Map<String, Double> distributionMap = new HashMap<String, Double>();
		distributionMap.put("EASY", (double) (5 * (Math.round(getMarks(totalMarks, distributions[0])/ 5) )));
		//added ternary to eliminate null pointer
			distributionMap.put("DIFFICULT",
					((totalMarks - distributionMap.get("EASY")) / 10) > 1 ? (double)(10 * (Math.round(getMarks(totalMarks, distributions[1])/ 10) )) :0.0);
			distributionMap.put("MEDIUM", ((totalMarks - (distributionMap.get("EASY") + distributionMap.get("DIFFICULT"))) / 5) >= 1 ? (double) (5 * (Math.round((double) totalMarks/5)))
			        - (distributionMap.get("EASY") + distributionMap.get("DIFFICULT")) :0.0);

		return distributionMap;
	}

	/**
	 * @param totalMarks
	 * @param distributions
	 * @return
	 */
	private double getMarks(double totalMarks, double distribution) {
		return totalMarks * (distribution / 100);
	}
}
