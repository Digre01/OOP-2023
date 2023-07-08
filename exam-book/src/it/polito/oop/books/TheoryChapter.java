package it.polito.oop.books;

import java.util.*;
import java.util.stream.Collectors;


public class TheoryChapter extends Chapter{
	
	public TheoryChapter(String title, int numPages, String text) {
		super(title,numPages);
		this.text = text;
		
	}

	String text;
	Set<Topic> topics = new HashSet<>();
	

    public String getText() {
		return text;
	}

    public void setText(String newText) {
    	this.text = newText;
    }
    
    public List<Topic> getTopics() {
		List<Topic> list = topics.stream().sorted(Comparator.comparing(Topic::getKeyword)).collect(Collectors.toList());
		return list;
		//return lst;

   }

    
    public void addTopic(Topic topic) {
    	if(!topics.contains(topic)) {
			topics.add(topic);
			for(Topic t: topic.getSubTopics())
				addTopic(t);
    	}
   }
}





