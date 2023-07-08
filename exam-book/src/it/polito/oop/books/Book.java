package it.polito.oop.books;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Book {

    /**
	 * Creates a new topic, if it does not exist yet, or returns a reference to the
	 * corresponding topic.
	 * 
	 * @param keyword the unique keyword of the topic
	 * @return the {@link Topic} associated to the keyword
	 * @throws BookException
	 */
	List<Topic> topics = new LinkedList<>();
	List<Chapter> chapters = new LinkedList<>();
	
	
	public Topic getTopic(String keyword) throws BookException {
		
		if(keyword.equals(null))
			throw new BookException();
		
		for(Topic to : topics)
			if(to.getKeyword().equals(keyword))
				return to;
		
		Topic t = new Topic(keyword);
		topics.add(t);
	    return t;
	}

	public Question createQuestion(String question, Topic mainTopic) {
        Question q = new Question(question,mainTopic);
        
		return q;
	}

	public TheoryChapter createTheoryChapter(String title, int numPages, String text) {
        TheoryChapter thc = new TheoryChapter(title,numPages,text);
        chapters.add(thc);
		return thc;
	}

	public ExerciseChapter createExerciseChapter(String title, int numPages) {
		ExerciseChapter exc = new ExerciseChapter(title,numPages);
		chapters.add(exc);
        return exc;
	}

	public List<Topic> getAllTopics() {
        
		return chapters.stream().flatMap(c -> c.getTopics().stream()).distinct().collect(Collectors.toList());
	}

	public boolean checkTopics() {
		Set<Topic> t = chapters.stream().filter(c -> c instanceof TheoryChapter).flatMap(c -> c.getTopics().stream())
				.collect(Collectors.toSet());
		
		Set<Topic> e = chapters.stream().filter(c -> c instanceof ExerciseChapter).flatMap(c -> c.getTopics().stream())
				.collect(Collectors.toSet());
		
		return t.containsAll(e);

	}

	public Assignment newAssignment(String ID, ExerciseChapter chapter) {
         return new Assignment(ID, chapter);
	}
	
    /**
     * builds a map having as key the number of answers and 
     * as values the list of questions having that number of answers.
     * @return
     */
    public Map<Long,List<Question>> questionOptions(){
    	return chapters.stream()
		        .filter(c -> c instanceof ExerciseChapter)
				.flatMap(c -> ((ExerciseChapter) c).questions.stream())
				.collect(Collectors.groupingBy(q -> q.numAnswers(), Collectors.toList()));

    }
}
