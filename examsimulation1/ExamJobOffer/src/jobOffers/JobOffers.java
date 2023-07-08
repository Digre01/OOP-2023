package jobOffers; 
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class JobOffers  {
	public List<String> skillsList = new LinkedList<>();
	public List<Position> positionsList = new LinkedList<>();
	public List<Candidate> candidates = new LinkedList();
	public List<Consulant> consulants = new LinkedList();
	
	

//R1
	public int addSkills (String... skills) {
		for(String s : skills) {
			if(!skillsList.contains(s))
				skillsList.add(s);
		}
		return skillsList.size();
	}
	
	public int addPosition (String position, String... skillLevels) throws JOException {
		String[] tmp;
		int tot = 0;
		int cnt = 0;
		
		if(!positionsList.contains(position)) {
			for(String s : skillLevels) {
				tmp = s.split(":");
				if(Integer.parseInt(tmp[1]) >= 4 && Integer.parseInt(tmp[1]) <= 8) {
					tot += Integer.parseInt(tmp[1]);
					cnt += 1;
				}
				else {
					throw new JOException("Out of range");
				}
				
			}
			positionsList.add(new Position(position,Arrays.asList(skillLevels)));
			return tot/cnt;
		}
		throw new JOException("Duplicated Position");
		

		
	}
	
//R2	
	public int addCandidate (String name, String... skills) throws JOException {
		for(String s : skills) {
			if (skillsList.contains(s) == false)
				throw new JOException("Skill not present in the list");
		}
		
		
		if(!candidates.stream().map(Candidate::getName).anyMatch(x -> x.contains(name))) {
			candidates.add(new Candidate(name,Arrays.asList(skills)));
			return skills.length;
		}
		throw new JOException("Duplicated Candidate");
	}
	
	public List<String> addApplications (String candidate, String... positions) throws JOException {
		List<String> application = new LinkedList<>();
		List<String> tmp = new LinkedList<>();
		
		if(candidates.contains(candidate)) {
			for(String s : positions) {
				for(Candidate c : candidates) {
					if(c.getName().equals(candidate)) 
						if(c.checkSkills(Arrays.asList(positions))) {
							application.add(candidate+":"+s);
							c.addPosition(positionsList,s);
						}
							
					
				}
					
			}
			
			application.stream().forEach(System.out::println);
			application.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());

			return application;
			
		}
		throw new JOException("suus");
	} 
	
	public TreeMap<String, List<String>> getCandidatesForPositions() {
	
		SortedMap<String, List<String>> map = new TreeMap<>();
		for(Position p : positionsList) {
			map.put(p.getName(), p.getCandidatesName());
		}
		return (TreeMap<String, List<String>>) map;
		
	}
	
	
//R3
	public int addConsultant (String name, String... skills) throws JOException {
		for(String s : skills) {
			if (skillsList.contains(s) == false)
				throw new JOException("Skill not present in the list");
		}
		
		
		if(!consulants.containsKey(name)) {
			consulants.add(name,Arrays.asList(skills));
			return consultantMap.get(name).size();
		}
		throw new JOException("Duplicated Consultant");
	}
	
	
	public Integer addRatings (String consultant, String candidate, String... skillRatings)  throws JOException {
		return -1;
	}
	
//R4
	public List<String> discardApplications() {
		return null;
	}
	
	 
	public List<String> getEligibleCandidates(String position) {
		return null;
	}
	

	
}

		
