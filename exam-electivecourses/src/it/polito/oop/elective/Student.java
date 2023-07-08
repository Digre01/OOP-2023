package it.polito.oop.elective;
import java.util.*;
import java.util.stream.Collectors;

public class Student {
	public Student(String id, double average) {
		super();
		this.id = id;
		this.average = average;
	}
	String id;
	double average;
	int n_courses = 0;
	Boolean enrolled = false;
	Course course_enrolled = null;
	SortedMap<Integer,Course> courses = new TreeMap<>();
	
	public String getID() {
		return id;
	}
	public double getAvg() {
		return average;
	}
	public void enrollCourse(int priority,Course course) {
			courses.put(priority,course);
	}
	public List<Course> getCoursesPreference(){
		return  courses.values().stream().collect(Collectors.toList());
	}
	public void setEnrolled(Course c, Boolean b) {
		enrolled = b;
		course_enrolled = c;
	}
	public Boolean isEnrolled() {
		return enrolled;
	}
	public Course getEnrolledCourse() {
		
		return course_enrolled;
	}
	


}
