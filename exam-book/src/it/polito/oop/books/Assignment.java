package it.polito.oop.books;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.*;


public class Assignment {
	String ID;
	ExerciseChapter exc;
	SortedMap<Question,Double> scores = new TreeMap<>();
	
	public Assignment(String ID, ExerciseChapter exc) {
		this.ID = ID;
		this.exc = exc;
	}

    public String getID() {
        return ID;
    }

    public ExerciseChapter getChapter() {
        return exc;
    }

    public double addResponse(Question q,List<String> answers) {
    	Set<String> rightAnswers = q.getCorrectAnswers();
    	Set<String> studentAnswers = answers.stream().collect(Collectors.toSet());
    	
    	int N = studentAnswers.size();
    	int FP = 0;
    	int FN = 0;
    	
    	for(String s : rightAnswers) {
    		if(!studentAnswers.contains(s)) {
    			FN++;
    		}
    	
    	}
    	for(String s: studentAnswers) {
    		if(!rightAnswers.contains(s))
    			FP++;
    	}
    	
    	
        double score = (N - FP - FN)/N;
        scores.put(q,score);
        
        return score;
    }
    
    public double totalScore() {
    	double tot = 0;
        for(double d : scores.values()) {
        	tot += d;
        }
        return tot;
    }

}
