/*
 *
 */
package com.assignment.question.paper.generator.data.loader;

import static com.assignment.question.paper.generator.constants.ApplicationConstant.TEXT_MEDIUM;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.assignment.question.paper.generator.model.QuestionLoader;
import com.assignment.question.paper.generator.perstitant.storage.QuestionLibrary;

/**
 * @author jayavardhan.hegde
 *
 */
public class MediumQuestionLoader extends AbstractQuestionLoader implements QuestionLoaderStratergy {

	private String metaData;

	public MediumQuestionLoader() {

	}

	public MediumQuestionLoader(String metaData) {
		this.metaData = metaData;
	}

	@Override
	public QuestionLoader loadQuestion() {
		QuestionLibrary.multiKeyMediumQuestionsMap.clear();
		String filePath = "src/main/resources/medium-questions.txt";
		Path path = Paths.get(filePath);
		try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
			stream.forEach(content -> readContentAndPersistToStore(content.trim(),
			        QuestionLibrary.multiKeyMediumQuestionsMap, metaData));
			QuestionLibrary.QUESTION_INDEX = 1;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new QuestionLoader(TEXT_MEDIUM, QuestionLibrary.multiKeyMediumQuestionsMap.size());
	}
}
