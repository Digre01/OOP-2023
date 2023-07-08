package jobOffers;

import java.util.*;

public class Consultant {
	private String name;
	private Set<String> skills;
	
	public Consultant(String name, Set<String> skills) {
		this.name = name;
		this.skills = skills;
	}
	public int getNumberOfSkills() {
		return skills.size();
	}
	public Set<String>  getSkills() {
		// TODO Auto-generated method stub
		return skills;
	}

}
