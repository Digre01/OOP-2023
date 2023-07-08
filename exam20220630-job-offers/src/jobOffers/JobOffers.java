package jobOffers; 
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class JobOffers  {

//R1
	
	Set<String> skillSet = new HashSet<>();
	TreeMap<String,Position> positions = new TreeMap<>();
	TreeMap<String,Candidate> candidates = new TreeMap<>();
	TreeMap<String,Consultant> consultants = new TreeMap<>();
	List<Application> applications = new LinkedList<>();
	List<Rating> ratings = new LinkedList<>();
	
	
	public int addSkills (String... skills) {
		for(String s : skills)
			skillSet.add(s);
			
			
		return skillSet.size();
	}
	
	public int addPosition (String position, String... skillLevels) throws JOException {
		Map<String,Integer> skillMap = new TreeMap<>();
		String[] tmp = new String[2];
		
		if(positions.containsKey(position))
			throw new JOException("Duplicated Position");
		
		for(String s: skillLevels) {
			tmp = s.split(":");
			int skillLevel = Integer.parseInt(tmp[1]);
			
			if(!skillSet.contains(tmp[0]))
				throw new JOException("Undefined skill");
			
			if(skillLevel > 8 || skillLevel < 4)
				throw new JOException("Position Error");
			
			
			skillMap.put(tmp[0], Integer.parseInt(tmp[1]));
			
		}
		Position p = new Position(position,skillMap);
		positions.put(position, p);
		
		return p.getAvg();
	}
	
//R2	
	public int addCandidate (String name, String... skills) throws JOException {
		Set<String> candidateSkills = new HashSet<>();
		
		for(String s : skills) {
			if(!skillSet.contains(s))
				throw new JOException("Undefined skill");
			if(candidates.containsKey(name))
				throw new JOException("Duplicated Candidate");
			candidateSkills.add(s);
		}
		Candidate c = new Candidate(name,candidateSkills);
		candidates.put(name, c);
		
		return c.getNumberOfSkill();
	}
	
	public List<String> addApplications (String candidate, String... positions) throws JOException {
		Position pos = null;
		Candidate can = this.candidates.get(candidate);
		
		
		
		if(!candidates.containsKey(candidate))
			throw new JOException("Undefined Candidate");
		
		
		for(String p: positions) {
			
			if(!this.positions.containsKey(p))
				throw new JOException("Undefined Position");
			
			pos = this.positions.get(p);
			
			if(!pos.getSkills().equals(can.getSkills())) 
				throw new JOException("Not all skill are ok");
			
			Application a = new Application(candidate,p);
			applications.add(a);
			
		}
			
		
		List<String> list = applications.stream().map(Application::toString)
				.collect(Collectors.toList());
		
		Collections.sort(list);
		return list;
	} 
	
	public TreeMap<String, List<String>> getCandidatesForPositions() {
		TreeMap<String, List<String>> map = applications.stream()
				.sorted (Comparator.comparing(Application::getCandidate))
				.collect(Collectors.groupingBy (Application::getPosition, TreeMap::new, 
						Collectors.mapping(Application::getCandidate, Collectors.toList())));
				//System.out.println(map);
				return map;

	}
	
	
//R3
	public int addConsultant (String name, String... skills) throws JOException {
		Set<String> list = new HashSet<>();
		
		if(consultants.containsKey(name))
			throw new JOException("Already existing consultant");
		
		for(String s : skills) {
			if(!skillSet.contains(s))
				throw new JOException("Undefined skill");
			list.add(s);
		}
		Consultant c = new Consultant(name,list);
		consultants.put(name, c);
		
		
		return c.getNumberOfSkills();
	}
	
	public Integer addRatings (String consultant, String candidate, String... skillRatings)  throws JOException {
		TreeMap<String,Integer> ratingMap = new TreeMap<>();
		String[] tmp = new String[2];
		Consultant con = consultants.get(consultant);
		Candidate can = candidates.get(candidate);
		
		if(!con.getSkills().containsAll(can.getSkills()))
				throw new JOException("Not all skills included");
		
		
		if(!consultants.containsKey(consultant))
			throw new JOException("Undefined Consultant");
		
		if(consultants.containsKey(candidate))
			throw new JOException("Already existing consultant");
		
		for(String s : skillRatings) {
			tmp = s.split(":");
			int skillLevel = Integer.parseInt(tmp[1]);
			
			if(!skillSet.contains(tmp[0]))
				throw new JOException("Undefined skill");
			
			if(skillLevel > 10 || skillLevel < 4)
				throw new JOException("Position Error");
			
			ratingMap.put(tmp[0], skillLevel);
			
			
		}
		
		Rating r = new Rating(consultant,candidate,ratingMap);
		ratings.add(r);
	
		
		return r.getAverageRating();
	}
	
//R4
	public List<String> discardApplications() {
		List<String> discarded = new LinkedList();
		TreeMap<String,Integer> skillRatingsMap;
		
		for(Application a : applications) {
			Candidate can = candidates.get(a.getCandidate());
			Position pos = positions.get(a.getPosition());
			
			can.
		}
		
		
		return null;
	}
	
	 
	public List<String> getEligibleCandidates(String position) {
		return null;
	}
	

	
}

		
