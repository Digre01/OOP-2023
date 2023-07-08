package it.polito.oop.books;

import java.util.Set;
import java.util.*;

public class Question {
	String question = null;
	Topic mainTopic = null;
	int numAnswers = 0;
	SortedMap<String,Boolean> answers = new TreeMap<>();
	
	public Question(String question, Topic mainTopic) {
		this.question = question;
		this.mainTopic = mainTopic;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public Topic getMainTopic() {
		return mainTopic;
	}

	public void addAnswer(String answer, boolean correct) {
		answers.put(answer, correct);
		this.numAnswers++;
	}
	
    @Override
    public String toString() {
        return question + "(" + mainTopic.getKeyword() + ")";
    }

	public long numAnswers() {
	    return numAnswers;
	}

	public Set<String> getCorrectAnswers() {
		Set<String> correctAnswers = new HashSet<>();
		
		for(String s : answers.keySet()) {
			if(answers.get(s).equals(true)) {
				correctAnswers.add(s);
			}
		}
		
		return correctAnswers;
	}

	public Set<String> getIncorrectAnswers() {
		Set<String> wrongAnswers = new HashSet<>();
		
		for(String s : answers.keySet()) {
			if(answers.get(s).equals(false)) {
				wrongAnswers.add(s);
			}
		}
		return wrongAnswers;
	}
}
