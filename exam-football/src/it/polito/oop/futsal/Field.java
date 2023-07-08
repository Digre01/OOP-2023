package it.polito.oop.futsal;

import java.util.*;
import it.polito.oop.futsal.Fields.Features;
import it.polito.oop.futsal.Fields.Time;

public class Field implements FieldOption{
	
	private final Features features;
	private int ID = 0;
	private final Map<String,Associate> bookings = new HashMap<>();	

	
	
	public Field(Features features, int ID){
		this.features = features;
		this.ID = ID;
	}
	
	public boolean getIndoor() {
		return features.indoor;
	}
	
	public void addBooking(Time t, Associate a) {
		bookings.put(t.toString(), a);
		
	}
	public Boolean checkBooking(String time) {
		 return bookings.containsKey(time);
	}
	
	

	@Override
	public int getField() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public int getOccupation() {
		// TODO Auto-generated method stub
		return  bookings.size();
	}

	public Boolean checkFeature(Features required) {
		// TODO Auto-generated method stub
		
		if(required.ac == true && features.ac != true)
			return false;
		if(required.indoor == true && features.indoor != true)
			return false;
		if(required.heating == true && features.heating != true)
			return false;
		
		
		return true;
	}
	
}
