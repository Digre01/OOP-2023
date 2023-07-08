package clinic;
import java.util.*;

public class Doctor extends Patient {
	
	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	String specialization; 
	Integer id;
	List<Patient> patients = new LinkedList<>();
	
	public void addPatient(Patient p) {
		patients.add(p);
	}
	public List<Patient> getPatients() {
		return patients;
	}
	
	public String getData() {
		int n = this.getPatients().size();
		
		String n_str = String.valueOf(n);
		while(n_str.length() < 3) {
			n_str = "0"+n_str;
		}
		return n_str + ": " + this.getId() + " " + this.getLast() + " " + this.getFirst(); 
		
	}
	
	public Doctor(String first, String last, String ssn, Integer id, String specialization) {
		super(last,first,ssn);
		this.id = id;
		this.specialization = specialization;
		
	}

}
