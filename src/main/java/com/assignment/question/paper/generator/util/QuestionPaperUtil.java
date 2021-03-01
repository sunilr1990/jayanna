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
		distributionMap.put("EASY", (double) (5 * (Math.round((double) getMarks(totalMarks, distributions[0])) / 5)));
		if (totalMarks - (distributionMap.get("EASY")) / 5 > 1) {
			distributionMap.put("MEDIUM",
			        (double) (5 * (Math.round((double) getMarks(totalMarks, distributions[1])) / 5)));
		} else {
			distributionMap.put("MEDIUM", 0.0);
		}
		if (totalMarks - (distributionMap.get("EASY") + distributionMap.get("MEDIUM")) / 5 > 1) {
			distributionMap.put("DIFFICULT", (double) (5 * (Math.round((double) totalMarks)))
			        - (distributionMap.get("EASY") + distributionMap.get("MEDIUM")));
		}

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
