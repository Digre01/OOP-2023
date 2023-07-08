package jobOffers;

import java.util.*;

public class Position {
	Map<String,Integer> skillMap = new TreeMap<>();
	String[] tmp = new String[2];
	String name;
	
	public Position(String position, Map<String,Integer> skills) {
		this.name = position;
		this.skillMap = skills;
	}
	public int getAvg() {
		int tot = 0;
		
		for(Integer i : skillMap.values()) {
			tot += i;
		}
		
		return tot/(skillMap.size());
	}
	public Set<String> getSkills(){
		return skillMap.keySet();
	}

}
