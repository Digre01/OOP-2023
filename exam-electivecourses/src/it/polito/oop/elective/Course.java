package it.polito.oop.elective;

import java.util.*;
import java.util.stream.Collectors;

public class Course {
	public Course(String name, int availablePositions) {
		this.name = name;
		this.availablePositions = availablePositions;
	}
	String name;
	int availablePositions;
	Long[] chosen  = new Long[4];
	List<Student> students = new LinkedList<>();
	
	
	
	public String getName() {
		return name;
	}
	
	public void chosen(int priority) {
		for(int i = 0; i < 4; i++) {
			if(chosen[i] == null)
				chosen[i] = (long) 0;
		}
			chosen[priority] += 1;
		
	}
	public List<Long> getpriorityList(){
		List<Long> list = new LinkedList<>();
		list.add(chosen[1]);
		list.add(chosen[2]);
		list.add(chosen[3]);
		
		return list;
	}
	
	public void enroll(Student s) {
		if(availablePositions > 0) {
			students.add(s);
			s.setEnrolled(this,true);
			availablePositions--;
		}
	}
	public Boolean isFull() {
		if(availablePositions <= 0)
			return true;
		return false;
	}
	public List<String> getStudents(){
		return students.stream().sorted(Comparator.comparing(Student::getAvg).reversed()).map(Student::getID).collect(Collectors.toList());
	}
	
	

}
