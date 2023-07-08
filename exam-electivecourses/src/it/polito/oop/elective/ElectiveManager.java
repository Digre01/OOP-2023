package it.polito.oop.elective;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Manages elective courses enrollment.
 * 
 *
 */
public class ElectiveManager {

    /**
     * Define a new course offer.
     * A course is characterized by a name and a number of available positions.
     * 
     * @param name : the label for the request type
     * @param availablePositions : the number of available positions
     */
	TreeMap<String,Course> courses = new TreeMap<>();
	TreeMap<String,Student> students = new TreeMap<>();
	 private List<Notifier> listeners = new LinkedList<>();
	
    public void addCourse(String name, int availablePositions) {
    	Course c = new Course(name,availablePositions);
    	courses.put(c.getName(),c);
    }
    
    /**
     * Returns a list of all defined courses
     * @return
     */
    public SortedSet<String> getCourses(){
        return new TreeSet<>(courses.keySet());
        //return courses.navigableKeySet();
    }
    
    /**
     * Adds a new student info.
     * 
     * @param id : the id of the student
     * @param gradeAverage : the grade average
     */
    public void loadStudent(String id, 
                                  double gradeAverage){
    	students.put(id,new Student(id,gradeAverage));
    	
        
    }

    /**
     * Lists all the students.
     * 
     * @return : list of students ids.
     */
    public Collection<String> getStudents(){
        return students.keySet();
    }
    
    /**
     * Lists all the students with grade average in the interval.
     * 
     * @param inf : lower bound of the interval (inclusive)
     * @param sup : upper bound of the interval (inclusive)
     * @return : list of students ids.
     */
    public Collection<String> getStudents(double inf, double sup){
        return students.values().stream().filter(x -> x.getAvg() >= inf && x.getAvg() <= sup).map(Student::getID).collect(Collectors.toList());
    }


    /**
     * Adds a new enrollment request of a student for a set of courses.
     * <p>
     * The request accepts a list of course names listed in order of priority.
     * The first in the list is the preferred one, i.e. the student's first choice.
     * 
     * @param id : the id of the student
     * @param selectedCourses : a list of of requested courses, in order of decreasing priority
     * 
     * @return : number of courses the user expressed a preference for
     * 
     * @throws ElectiveException : if the number of selected course is not in [1,3] or the id has not been defined.
     */
    public int requestEnroll(String id, List<String> courses)  throws ElectiveException {
       Student s = students.get(id);
       int priority = 0;
       if(s == null)
    	    throw new ElectiveException("Students does not exist");
       if(!courses.stream().allMatch(this.courses::containsKey))
    	   throw new ElectiveException("There are non-existing courses");
       if(courses.size() > 3 || courses.size() < 1)
    	   throw new ElectiveException("There is an error with the number of courses");
       
       
       for(String c : courses) {
    	   s.enrollCourse(++priority, this.courses.get(c));
    	   this.courses.get(c).chosen(priority);
       }
       listeners.forEach(l->l.requestReceived(id));
       return courses.size();
    }
    
    /**
     * Returns the number of students that selected each course.
     * <p>
     * Since each course can be selected as 1st, 2nd, or 3rd choice,
     * the method reports three numbers corresponding to the
     * number of students that selected the course as i-th choice. 
     * <p>
     * In case of a course with no requests at all
     * the method reports three zeros.
     * <p>
     * 
     * @return the map of list of number of requests per course
     */
    public Map<String,List<Long>> numberRequests(){
    	Map<String,List<Long>> map = new TreeMap<>();
    	
    	for(String c : courses.keySet())
    		map.put(c,courses.get(c).getpriorityList());
    	
    	
    	return map;
    	

    }
    
    
    /**
     * Make the definitive class assignments based on the grade averages and preferences.
     * <p>
     * Student with higher grade averages are assigned to first option courses while they fit
     * otherwise they are assigned to second and then third option courses.
     * <p>
     *  
     * @return the number of students that could not be assigned to one of the selected courses.
     */
    public long makeClasses() {
        return students.values().stream()
        		.sorted(Comparator.comparing(Student::getAvg).reversed())
        		.mapToInt(s -> {
        				for(Course c : s.getCoursesPreference()) {
        						if(!c.isFull()) { 
        							c.enroll(s);
        							 listeners.forEach(l->l.assignedToCourse(s.getID(), c.getName()));
        						}
        						
        					}
        				if(s.isEnrolled())
        					return 1;
        				return 0;
        				}).sum();
        		

    }
    
    
    /**
     * Returns the students assigned to each course.
     * 
     * @return the map course name vs. student id list.
     */
    public Map<String,List<String>> getAssignments(){
        return courses.values().stream()
        		.collect(Collectors.toMap(Course::getName,Course::getStudents));
    }
    
    
    /**
     * Adds a new notification listener for the announcements
     * issues by this course manager.
     * 
     * @param listener : the new notification listener
     */
    public void addNotifier(Notifier listener) {
        
    }
    
    /**
     * Computes the success rate w.r.t. to first 
     * (second, third) choice.
     * 
     * @param choice : the number of choice to consider.
     * @return the success rate (number between 0.0 and 1.0)
     */
    public double successRate(int choice){
    	 return students.values().stream()
    		        .filter(s -> s.isEnrolled())
    		        .filter(s -> s.getCoursesPreference().get(choice) == s.getEnrolledCourse())
    		        .count() / (double)students.size();

    }

    
    /**
     * Returns the students not assigned to any course.
     * 
     * @return the student id list.
     */
    public List<String> getNotAssigned(){
          return students.values().stream()
                .filter(s->!s.isEnrolled())
                .map(Student::getID)
                .collect(Collectors.toList());

    }
    
    
}
