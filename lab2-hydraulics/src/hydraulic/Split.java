package hydraulic;

import java.util.Arrays;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends Element {
	private Element input;
	private Element outputs[] = new Element[1000];
	
	private int numOfOutputs = 0;
	protected double flowIn;
	private double flowOut[];
	
	
	

	/**
	 * Constructor
	 * @param name name of the split element
	 */
	public Split(String name) {
		super(name);
	}
	
    public void connectSplit(Element elem, int index) {
		this.outputs[index] = elem;
		numOfOutputs++;
		elem.setInput(this);
	}
	public double getFlowIn() {
		return flowIn;
	}
    
    public void setInputSplit(Element elem) {
		this.input = elem;
	}
    
    public void setFlowInSplit(double flow) {
    	this.flowIn = flow;
    }
    public Element[] getOutputsSplit() {
    	return Arrays.copyOf(outputs,numOfOutputs);
    }
    
    
    public boolean isDeletable() {
    	int countOutputs = 0;
    	
		for(Element e : outputs) {
				if(e != null) {
					countOutputs++;
				}
				if(countOutputs >= 2) {
					return false;
				
				}
			
		}
		
		return true;
	}

   
   

	@Override
	protected void layout(StringBuffer buffer) {
		// TODO Auto-generated method stub
		buffer.append("[" + getName() + "]" + this.getClass().getSimpleName() + "+->");
		if(outputs != null) {
			int bufferLength = buffer.length();
			
			for(int i = 0; i < outputs.length; i++) {
				if(i > 0) {
					buffer.append("\n");
					buffer.append(" ".repeat(bufferLength));
					buffer.append("|\n");
				}
				buffer.append("+->");
				outputs[i].layout(buffer);
				
			}
			
			
		}
		
	}

	@Override
	public Element getInput() {
		// TODO Auto-generated method stub
		return this.input;
	}
	
	
}
