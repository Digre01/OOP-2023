package it.polito.oop.futsal;

import java.util.HashMap;
import java.util.Map;

import it.polito.oop.futsal.Fields.Time;

public class Associate {
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Associate(String first, String last, String phoneNumber) {
		super();
		this.first = first;
		this.last = last;
		this.phoneNumber = phoneNumber;
	}
	String first;
	String last;
	String phoneNumber;
	private final Map<Time,Field> bookings = new HashMap<>();	
	
	public void addBooking(Time t, Field c) {
		bookings.put(t, c);
	}
	public int getBooking() {
		return bookings.size();
	}

}
