package it.polito.oop.books;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Chapter {
	
	int numPages;
	String title;
	Set<Topic> topics = new HashSet<>();
	
	public abstract List<Topic> getTopics();
	
	public Chapter(String title, int numPages) {	
		this.numPages = numPages;
		this.title = title;
	}
	
	 public String getTitle() {
	       return title;
	 }

	 public void setTitle(String newTitle) {
	    	this.title = newTitle;
	 }

	  public int getNumPages() {
	        return numPages;
	  }
	    
	  public void setNumPages(int newPages) {
	    	this.numPages = newPages;
	  }
	  
	 
}
