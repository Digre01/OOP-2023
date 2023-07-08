package jobOffers;

import java.util.Map;
import java.util.TreeMap;
import java.util.*;

public class Candidate {
	String name;
	Set<String> skills = new HashSet<>();
	Boolean discarded = false;
	
	public Candidate(String name, Set<String> skills) {
		this.name = name;
		this.skills = skills;
	}

	public int getNumberOfSkill() {
		// TODO Auto-generated method stub
		return skills.size();
	}
	public Set<String> getSkills(){
		return skills;
	}
	public String getCandidate() {
		return name;
	}
	

}
