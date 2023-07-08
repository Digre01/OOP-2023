package jobOffers;

import java.util.List;
import java.util.stream.Collectors;

public class Position {
	
	public Position(String name, List<String> skillsLevel) {
		this.name = name;
		this.skillsLevel = skillsLevel;
		this.candidates = candidates;
	}
	private String name;
	private List<Candidate> candidates;
	private List<String> skillsLevel;
	
	public String getName() {
		return name;
	}
	public void addCandidate(Candidate c) {
		candidates.add(c);
	}
	public List<String> getCandidatesName(){
		return candidates.stream().map(Candidate::getName).collect(Collectors.toList());
	}
	
	
}
