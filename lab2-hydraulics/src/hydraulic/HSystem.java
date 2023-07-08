package hydraulic;

import java.util.Arrays;

/**
 * Main class that acts as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */


public class HSystem {
	
	final static int MAX_ELEM = 100;
	
	private Element[] elementCollection = new Element[MAX_ELEM];
	private int numOfElements = 0;
	
// R1
	/**
	 * Adds a new element to the system
	 * @param elem the new element to be added to the system
	 */
	public void addElement(Element elem){
		//TODO: to be implemented
		if(elem != null) {
			elementCollection[numOfElements++] = elem;
		}
		
	}
	
	/**
	 * returns the element added so far to the system
	 * @return an array of elements whose length is equal to 
	 * 							the number of added elements
	 */
	public Element[] getElements(){

		return Arrays.copyOf(elementCollection, numOfElements); //copy elementCollection in an array of only numOfElements space
	}

// R4
	/**
	 * starts the simulation of the system
	 */
	
	
	
	public void simulate(SimulationObserver observer){
		//TODO: to be implemented
		
		double flowTOT = Double.NaN;
		
		for(Element x : elementCollection) {
			if(x instanceof Source) {
				Source s = (Source) x;
				 x.startSimulate(observer,false);
				
			}
		}
		
			
		
	}
	
	
	
	
		
	public int getnumOfElements() {
		return numOfElements;
	}
	

// R6
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout(){
		//TODO: to be implemented
		StringBuffer output = new StringBuffer();
		
		
		
		
		
			
			for(Element x : elementCollection) {
				if(x instanceof Source) {
					Source s = (Source) x;
					 x.layout(output);
					
				}
			}
		
		
		
		
		
		return output.toString();
}
	

// R7
	/**
	 * Deletes a previously added element with the given name from the system
	 */
	public boolean deleteElement(String name) {
		Element toDelete = null;
		
		for(Element x : elementCollection) {
			if(x != null) {
				if(x.getName().equals(name)){
					toDelete = x;
					break;
					
				}
			}
		}
		if(toDelete instanceof Split) {
			Split sp = (Split) toDelete; 
			
			if(!sp.isDeletable()) {
				return false;
			}
		}
		
		toDelete.getInput().connect(toDelete.getOutput()); //ingresso dell'elemento da cancellare viene collegato all'uscita dell'elemento da cancellare
		
		Element[] newElements = new Element[numOfElements];
		int i = 0;
		
		for(Element e: elementCollection) {
			if(e != null) {
				newElements[i++] = e;
			} else {
				break;
			}
			
			
		}
		
		
		
		
		return true;
		
	}
	
	
	

// R8
	/**
	 * starts the simulation of the system; if {@code enableMaxFlowCheck} is {@code true},
	 * checks also the elements maximum flows against the input flow
	 */
	public void simulate(SimulationObserver observer, boolean enableMaxFlowCheck) {
		//TODO: to be implemented
		
		double flowTOT = Double.NaN;
		
		for(Element x : elementCollection) {
			if(x instanceof Source) {
				Source s = (Source) x;
				 x.startSimulate(observer,enableMaxFlowCheck);
				
			}
		}
		
		
	}
}
