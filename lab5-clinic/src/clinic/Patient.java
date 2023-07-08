package clinic;

public class Patient {
	public Patient(String last, String first, String ssn) {
		this.last = last;
		this.first = first;
		this.ssn = ssn;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	String last;
	String first;
	String ssn;
	Doctor doc = null;
	
	public Doctor getDoc() {
		return doc;
	}
	public void setDoc(Doctor doc) {
		this.doc = doc;
	}
	
	

}
