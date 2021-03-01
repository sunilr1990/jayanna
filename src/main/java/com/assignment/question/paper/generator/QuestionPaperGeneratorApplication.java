package com.assignment.question.paper.generator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.assignment.question.paper.generator.model.Question;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;

@SpringBootApplication
@ComponentScan(basePackages = { "com.assignment.question.paper.generator.controller",
        "com.assignment.question.papaer.generator.service", "com.assignment.question.paper.generator.data.loader",
        "com.assignment.question.paper.generator.model", "com.assignment.question.paper.generator.util",
        "com.assignment.question.paper.generator.validator", "com.assignment.question.paper.generator.config" })
public class QuestionPaperGeneratorApplication {
	static int count = 1;

	public static void main(String[] args) {
		SpringApplication.run(QuestionPaperGeneratorApplication.class, args);

		// Integer val = 2;
		// 10 * (Math.ceil(Math.abs(val / 100);
		System.out.println(5 * (Math.round((double) 2 / 5)));
		System.out.println(5 * (Math.round((double) 4 / 5)));
		System.out.println(5 * (Math.round((double) 9 / 5)));
		System.out.println(5 * (Math.round((double) 17.5 / 5)));
		System.out.println(5 * (Math.round((double) 22 / 5)));

		Table<String, String, Question> table = HashBasedTable.create();

		// MultiKeyMap multiKeyMap = new MultiKeyMap();
		MultiKeyMap<?, Object> multiKeyMap1 = MultiKeyMap.multiKeyMap(new LinkedMap<>());
		MultiValuedMap<String, Question> map = new ArrayListValuedHashMap<>();

		Multimap<String, Question> transactions = ArrayListMultimap.create();

		String filePath = "src/main/resources/easy-questions.txt";
		Path path = Paths.get(filePath);
		try {
			try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
				stream.forEach(content -> readContent(content, table, multiKeyMap1, transactions));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("|||| Before" + multiKeyMap1.toString());
		multiKeyMap1.clear();
		System.out.println("|||| After" + multiKeyMap1.toString());
		System.out.println("*****" + multiKeyMap1.get("EASY"));
		System.out.println(multiKeyMap1.get(1, "EASY", "waves"));
		// System.out.println("----" + rowMap.toString());
		// System.out.println("****" + rowMap.get("waves"));
		int finalMarks = 15;
		int value = finalMarks / 5;
		int generated = 0;
		Collection<Question> rowMap = transactions.get("EASY");
		while (generated < value) {
			generated = generated + 1;
		}
		// Random rand = new Random();
		// int randomIndex = rand.nextInt(QuestionLibrary.easyQuestionList.size());
		// // int randomIndex = (int) (Math.random() *
		// // QuestionLibrary.easyQuestionList.size());
		// System.out.println(randomIndex);
		// Question first = QuestionLibrary.easyQuestionList.get(randomIndex);
		// paper.append(first.toString()).append("|||");
		// generatedMarks = generatedMarks + first.getMarks();
		// }
		// System.out.println("Total marks was " + finalMarks + " Generated marks was "
		// + generatedMarks);
		// System.out.println(paper.toString());
	}

	public static void readContent(String content, Table<String, String, Question> table,
	        MultiKeyMap<?, Object> multiKeyMap, Multimap<String, Question> map) {
		String[] fileContent = content.split(",");
		Question question = new Question(fileContent[0], fileContent[1], fileContent[2], fileContent[3],
		        Integer.valueOf(fileContent[4]));
		// table.put(String.valueOf(count) + " " + question.getDifficulty(),
		// question.getTopic(), question);
		multiKeyMap.put(new MultiKey(count, question.getDifficulty()), question);
		multiKeyMap.put(new MultiKey(count, question.getDifficulty(), question.getTopic()), question);
		// map.put("EASY", question);
		count++;
		// return question;
	}
}
