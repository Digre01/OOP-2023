package it.polito.oop.futsal;

import java.util.List;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;



/**
 * Represents a infrastructure with a set of playgrounds, it allows teams
 * to book, use, and  leave fields.
 *
 */
public class Fields {
	    
    public static class Features {
        public final boolean indoor; // otherwise outdoor
        public final boolean heating;
        public final boolean ac;
        public Features(boolean i, boolean h, boolean a) {
            this.indoor=i; this.heating=h; this.ac = a;
        }
    }
    public static class Time{
		int hour;
    	int minute;
    	Time(String time){
    		String[] parti = time.split(":");
    		hour = Integer.parseInt(parti[0]);
    		minute = Integer.parseInt(parti[1]);
    	}
    	@Override
    	public String toString() {
    		return String.format("%02d:%02d", hour, minute);
    	}
    	boolean between(Time a, Time c) {
    		return hour >=a.hour && hour <c.hour;
    	}
    	public boolean aligned(Time otherTime) {
			return this.minute == otherTime.minute;
		}

    	
    }
    
    SortedMap<Integer,Field> fields = new TreeMap<>();
    SortedMap<Integer,Associate> associates = new TreeMap<>();
    int numField = 0;
    int id = 0;
    Time openingTime;
    Time closingTime;
    
	
    public void defineFields(Features... features) throws FutsalException {
    	
    	for(Features f : features) {
    		if(!f.indoor) {
    			if(f.ac || f.heating) 
    				throw new FutsalException("not indoor field having heating or ac");
    		}
    		Field c = new Field(f,++numField);
    		fields.put(numField,c);
    	}

    }
    
    public long countFields() {
        return fields.size();
    }

    public long countIndoor() {
        return fields.values().stream().filter(x -> x.getIndoor() == true).count();
    }
    
    public String getOpeningTime() {
        return openingTime.toString();
    }
    
    public void setOpeningTime(String time) {
    	openingTime = new Time(time);
    }
    
    public String getClosingTime() {
        return closingTime.toString();
    }
    
    public void setClosingTime(String time) {
    	closingTime = new Time(time);
    }

    public int newAssociate(String first, String last, String mobile) {
        Associate a = new Associate(first,last,mobile);
        associates.put(++id, a);
    	return id;
    }
    
    public String getFirst(int associate) throws FutsalException {
        return associates.get(id).getFirst();
    }
    
    public String getLast(int associate) throws FutsalException {
        return associates.get(id).getLast();
    }
    
    public String getPhone(int associate) throws FutsalException {
        return associates.get(id).getPhoneNumber();
    }
    
    public int countAssociates() {
        return associates.size();
    }
    
    public void bookField(int field, int associate, String time) throws FutsalException {
    	Field c = fields.get(field);
    	Associate a = associates.get(associate);
    	Time richiesta = new Time(time);
    	
    	if(a == null || c == null)
    		throw new FutsalException("Invalid Field or associate");
    	
    	if(!validTime(new Time(time)))
    		throw new FutsalException("Invalid Time");
    	
    	if(!richiesta.aligned(openingTime)) 
    		throw new FutsalException("Not aligned with opening time");
    	
    	if(!richiesta.between(openingTime, closingTime))
    		throw new FutsalException("Not in opening time");
    	
    	c.addBooking(richiesta,a);
    	a.addBooking(richiesta,c);
    	
    	
    	
    	
    }
    boolean validTime(Time t) {
		if (t.hour > 23 || t.hour < 0 || t.minute > 59 || t.minute < 0)
			return false;
		return true;
	}
    
    
    public boolean isBooked(int field, String time) {
    	Field c = fields.get(field);
    	if(c == null)
    		return false;
    	Time t = new Time(time);
        return c.checkBooking(time);
    }
    

    public int getOccupation(int field) {
    	Field c = fields.get(field);
    	if(c == null)
    		return 0;
    	
    	
        return c.getOccupation();
    }
    
    public List<FieldOption> findOptions(String time, Features required){
        return fields.values().stream().filter(x -> x.checkFeature(required))
        	                  .filter(x -> !x.checkBooking(time))
        	                  .sorted(Comparator.comparing(FieldOption::getOccupation).reversed()
        	                		  .thenComparing(FieldOption::getField))
        	                  .collect(Collectors.toList());
    }
    
    public long countServedAssociates() {
        return associates.values().stream().filter(x -> x.getBooking() > 0).count();
    }
    
    public Map<Integer,Long> fieldTurnover() {
        return fields.values().stream().collect(Collectors.toMap(Field::getField,c -> (long) c.getOccupation()));
    }
    
    public double occupation() {
    	int totalBookings = fields.values().stream()
				 .mapToInt(Field::getOccupation)
				 .sum();
    	
    	int timeSlots = closingTime.hour - openingTime.hour;
        return totalBookings / ((double)timeSlots * fields.size());
    }
    
 }
