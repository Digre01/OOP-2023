package it.polito.oop.books;

import java.util.*;
import java.util.stream.Collectors;

public class Topic {
	Boolean type; 
	String keyword = null;
	
	SortedMap<String,Topic> subtopics = new TreeMap<>();
	
	public Topic(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
        return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
	    return null;
	}

	public boolean addSubTopic(Topic topic) {
		
		if(subtopics.containsKey(topic.getKeyword()))
			return false;
		
		subtopics.put(topic.getKeyword(), topic);
		
        return false;
	}

	/*
	 * Returns a sorted list of subtopics. Topics in the list *MAY* be modified without
	 * affecting any of the Book topic.
	 */
	public List<Topic> getSubTopics() {
		List<Topic> list = new LinkedList<>(subtopics.values());
        return list;
	}
}
