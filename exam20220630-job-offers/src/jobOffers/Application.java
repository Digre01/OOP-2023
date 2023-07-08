package jobOffers;

import java.util.*;

public class Application {
	String candidate;
	String position;
	
	
	public Application (String candidate, String position) {
		this.candidate = candidate;
		this.position = position;
	}
	public String getPosition() {
		return position;
		} //used in method reference
	public String getCandidate() {
		return candidate;
	}
	public String toString() {
		return candidate + ":" + position;
	}

	
}
