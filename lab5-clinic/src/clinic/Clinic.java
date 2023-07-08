package clinic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.regex.*;

import java.util.*;


/**
 * Represents a clinic with patients and doctors.
 * 
 */
public class Clinic {
	List<Patient> patients = new LinkedList<>();
	List<Doctor> doctors = new LinkedList<>();


	/**
	 * Add a new clinic patient.
	 * 
	 * @param first first name of the patient
	 * @param last last name of the patient
	 * @param ssn SSN number of the patient
	 */
	public void addPatient(String first, String last, String ssn) {
		patients.add(new Patient(first,last,ssn));
	}


	/**
	 * Retrieves a patient information
	 * 
	 * @param ssn SSN of the patient
	 * @return the object representing the patient
	 * @throws NoSuchPatient in case of no patient with matching SSN
	 */
	public String getPatient(String ssn) throws NoSuchPatient {
		
		
		for(Patient p : patients)
			if(p.getSsn().equals(ssn))
				return p.getFirst() + " " + p.getLast() + " " + "("+p.getSsn()+")";
		
		for(Doctor p : doctors)
			if(p.getSsn().equals(ssn))
				return p.getFirst() + " " + p.getLast() + " " + "("+p.getSsn()+")";
		
		
		NoSuchPatient n = new NoSuchPatient();
		throw n;
	}
	
	

	/**
	 * Add a new doctor working at the clinic
	 * 
	 * @param first first name of the doctor
	 * @param last last name of the doctor
	 * @param ssn SSN number of the doctor
	 * @param docID unique ID of the doctor
	 * @param specialization doctor's specialization
	 */
	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
		doctors.add(new Doctor(first,last,ssn,docID,specialization));

	}

	/**
	 * Retrieves information about a doctor
	 * 
	 * @param docID ID of the doctor
	 * @return object with information about the doctor
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public String getDoctor(int docID) throws NoSuchDoctor {
		
		for(Doctor d : doctors)
			if(d.getId() == docID)
				return d.getLast() + " " + d.getFirst() + " " + "("+d.getSsn()+") " + "[" +d.getId() +"]:"+ " " + d.getSpecialization();
		NoSuchDoctor  n = new NoSuchDoctor ();
		throw n;
	}
	
	/**
	 * Assign a given doctor to a patient
	 * 
	 * @param ssn SSN of the patient
	 * @param docID ID of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
		Patient pat = null;
		Doctor doc = null;
		
		for(Patient p : patients)
			if(p.getSsn().equals(ssn))
				pat = p;
		
		if(pat == null) {
			NoSuchPatient n = new NoSuchPatient();
			throw n;
		}
		
		for(Doctor d : doctors)
			if(d.getId() == docID)
				doc = d;
		
		if(doc == null) {
			NoSuchDoctor  e = new NoSuchDoctor ();
			throw e;
		}
		
		pat.setDoc(doc);
		doc.addPatient(pat);
		

	}

	/**
	 * Retrieves the id of the doctor assigned to a given patient.
	 * 
	 * @param ssn SSN of the patient
	 * @return id of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor has been assigned to the patient
	 */
	public int getAssignedDoctor(String ssn) throws NoSuchPatient, NoSuchDoctor {
		Patient pat = null;
		Doctor doc = null;
		
		for(Patient p : patients)
			if(p.getSsn().equals(ssn))
				pat = p;
		
		if(pat == null) {
			NoSuchPatient n = new NoSuchPatient();
			throw n;
		}
		
		Doctor doc_t = pat.getDoc();
		
		if(doc_t == null) {
			NoSuchDoctor  e = new NoSuchDoctor();
			throw e;
		}
		
		int docID = doc_t.getId();
		for(Doctor d : doctors)
			if(d.getId() == docID)
				doc = d;
		
		if(doc == null) {
			NoSuchDoctor  e = new NoSuchDoctor();
			throw e;
		}
		
		return docID;
		
	}
	
	/**
	 * Retrieves the patients assigned to a doctor
	 * 
	 * @param id ID of the doctor
	 * @return collection of patient SSNs
	 * @throws NoSuchDoctor in case the {@code id} does not match any doctor 
	 */
	public Collection<String> getAssignedPatients(int id) throws NoSuchDoctor {
		Doctor doc = null;
		
		for(Doctor d : doctors)
			if(d.getId() == id)
				doc = d;
		
		if(doc == null) {
			NoSuchDoctor  e = new NoSuchDoctor ();
			throw e;
		}
		
		return doc.getPatients().stream().map(Patient::getSsn).collect(Collectors.toList());
		
	}
	
	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and speciality.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method should be able
	 * to ignore the row and skip to the next one.<br>

	 * 
	 * @param reader reader linked to the file to be read
	 * @throws IOException in case of IO error
	 */
	public int loadData(Reader reader) throws IOException {
		return loadData(reader,null);
	}


	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and speciality.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method calls the
	 * {@link ErrorListener#offending} method passing the line itself,
	 * ignores the row, and skip to the next one.<br>
	 * 
	 * @param reader reader linked to the file to be read
	 * @param listener listener used for wrong line notifications
	 * @throws IOException in case of IO error
	 */
	public int loadData(Reader reader, ErrorListener listener) throws IOException {
		Pattern re = Pattern.compile("(?:P|M *; *(?<id>[0-9]+)) *; *(?<first>[^;]+) *; *(?<last>[^;]+) *; *(?<ssn>[0-9a-zA-Z]+)(?: *; *(?<spec>[^;]+))?");
		int n = 0;
		
		try(BufferedReader r = new BufferedReader(reader)) {
			String line;
			while((line = r.readLine()) != null) {
				Matcher m = re.matcher(line);
				if(m.find()) {
					try{
						if(m.group("id")  == null) {
							this.addPatient(m.group("first"),m.group("last"),m.group("ssn"));
						} //if doesnt find the group id, is a patient
						else {
							this.addDoctor(m.group("first"),m.group("last"),m.group("ssn"),Integer.parseInt(m.group("id")),m.group("spec"));
						}
						n++;
					}
					catch(NumberFormatException e) {
						if(listener != null) {
							listener.offending(line);
						}
						
					}
				}
				else {
					if(listener != null) {
						listener.offending(line);
					}
				}
			}
			
			
		}
		
			
	
		return n;
	}
	
	/**
	 * Retrieves the collection of doctors that have no patient at all.
	 * The doctors are returned sorted in alphabetical order
	 * 
	 * @return the collection of doctors' ids
	 */
	public Collection<Integer> idleDoctors(){
		return doctors.stream().filter(x -> x.getPatients().isEmpty()).sorted(Comparator.comparing(Doctor::getLast)).map(Doctor::getId).collect(Collectors.toList());
	}

	/**
	 * Retrieves the collection of doctors having a number of patients larger than the average.
	 * 
	 * @return  the collection of doctors' ids
	 */
	public Collection<Integer> busyDoctors(){
		double average = doctors.stream().mapToInt(x->x.getPatients().size()).average().orElse(0);
		
		return doctors.stream().filter(x -> x.getPatients().size() > average).sorted(Comparator.comparing(Doctor::getLast)).map(Doctor::getId).collect(Collectors.toList());
	}

	/**
	 * Retrieves the information about doctors and relative number of assigned patients.
	 * <p>
	 * The method returns list of strings formatted as "{@code ### : ID SURNAME NAME}" where {@code ###}
	 * represent the number of patients (printed on three characters).
	 * <p>
	 * The list is sorted by decreasing number of patients.
	 * 
	 * @return the collection of strings with information about doctors and patients count
	 */
	public Collection<String> doctorsByNumPatients(){
		return doctors.stream().sorted(Comparator.comparingInt((Doctor x) -> x.getPatients().size()).reversed()).map(Doctor::getData).collect(Collectors.toList());
	}
	
	/**
	 * Retrieves the number of patients per (their doctor's)  speciality
	 * <p>
	 * The information is a collections of strings structured as {@code ### - SPECIALITY}
	 * where {@code SPECIALITY} is the name of the speciality and 
	 * {@code ###} is the number of patients cured by doctors with such speciality (printed on three characters).
	 * <p>
	 * The elements are sorted first by decreasing count and then by alphabetic speciality.
	 * 
	 * @return the collection of strings with speciality and patient count information.
	 */
	public Collection<String> countPatientsPerSpecialization(){
		return patients.stream().map(Patient::getDoc).filter(x -> x != null).collect(Collectors.groupingBy(Doctor::getSpecialization,Collectors.counting()))
		.entrySet().stream().sorted(Comparator.comparing(Map.Entry<String,Long>::getValue,Comparator.reverseOrder()).thenComparing(Comparator.comparing(Map.Entry<String,Long>::getKey)))
				.map(e -> String.format("%3d - %s",e.getValue(),e.getKey())).collect(Collectors.toList());
	}

}
