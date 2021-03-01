/*
 *
 */
package com.assignment.question.paper.generator.data.loader;

import static com.assignment.question.paper.generator.constants.ApplicationConstant.TEXT_EASY;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.assignment.question.paper.generator.model.QuestionLoader;
import com.assignment.question.paper.generator.perstitant.storage.QuestionLibrary;

/**
 * @author jayavardhan.hegde
 *
 */
@Component
public class EasyQuestionLoader extends AbstractQuestionLoader implements QuestionLoaderStratergy {

	private String metaData;

	public EasyQuestionLoader() {

	}

	public EasyQuestionLoader(String metaData) {
		this.metaData = metaData;
	}

	@Override
	public QuestionLoader loadQuestion() {
		QuestionLibrary.multiKeyEasyQuestionsMap.clear();
		String filePath = "src/main/resources/easy-questions.txt";
		Path path = Paths.get(filePath);
		try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
			stream.forEach(content -> readContentAndPersistToStore(content.trim(),
			        QuestionLibrary.multiKeyEasyQuestionsMap, metaData));
			QuestionLibrary.QUESTION_INDEX = 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new QuestionLoader(TEXT_EASY, QuestionLibrary.multiKeyEasyQuestionsMap.size());
	}

	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}
}
