package jobOffers;

import java.util.*;

public class Candidate {
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}





	public List<String> getSkills() {
		return skills;
	}





	public void setSkills(List<String> skills) {
		this.skills = skills;
	}





	public List<Position> getPosition() {
		return positions;
	}





	public void setPosition(List<Position> positions) {
		this.positions = positions;
	}





	private String name;
	private List<String> skills;
	private List<Position> positions;
	
	public Candidate(String name, List<String> skills) {
		this.name = name;
		this.skills = skills;
	}


	
	
	
	public boolean containsSkill(String skill) {
		for(String s: skills) {
			if(s.equals(skill))
				return true;
		}
		return false;
		
	}
	
	public boolean checkSkills(List<String> positionNames) {
		return skills.containsAll(positionNames);
		
		
	}
	public void addPosition(List<Position> positionsList, String positionName) {
		for(Position p: positionsList) {
			if(p.getName().equals(positionName)) {
				positions.add(p);
				p.addCandidate(this);
			}
				
				
		}
		
	}
	
	
	

}
