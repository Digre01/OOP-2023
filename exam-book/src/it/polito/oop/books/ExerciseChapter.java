package it.polito.oop.books;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



public class ExerciseChapter extends Chapter{
	
	
	public ExerciseChapter(String title, int numPages) {
		 super(title,numPages);
		
	}
	Set<Question> questions = new HashSet<>();
	
    public List<Topic> getTopics() {
    	List<Topic> list = topics.stream().sorted(Comparator.comparing(Topic::getKeyword)).collect(Collectors.toList());
		return list;
      
	};
    

	public void addQuestion(Question question) {
		questions.add(question);
		addTopic(question.mainTopic);
		
	}
	public void addTopic(Topic topic) {
    	if(!topics.contains(topic)) {
			topics.add(topic);
			
		}
	}
}
