package hydraulic;

/**
 * Represents a multisplit element, an extension of the Split that allows many outputs
 * 
 * During the simulation each downstream element will
 * receive a stream that is determined by the proportions.
 */

public class Multisplit extends Split {
	private int numOut;
	//private double flowIn;
	private double[] flows = new double[51];
	private double[] proportions; 
	 
	/**
	 * Constructor
	 * @param name the name of the multi-split element
	 * @param numOutput the number of outputs
	 */
	public Multisplit(String name, int numOutput) {
		super(name);
		this.numOut = numOutput;
	}
	
	
	
	/**
	 * Define the proportion of the output flows w.r.t. the input flow.
	 * 
	 * The sum of the proportions should be 1.0 and 
	 * the number of proportions should be equals to the number of outputs.
	 * Otherwise a check would detect an error.
	 * 
	 * @param proportions the proportions of flow for each output
	 */
	public void setProportions(double... proportions) {
		this.proportions = proportions;
	}
	
	public double[] getOutFlows() {
		for(int i = 0; i < numOut; i++)
			flows[i] = flowIn*proportions[i];
		
		
		return flows;
		
	}
	
	@Override
	protected void layout(StringBuffer buffer) {
		// TODO Auto-generated method stub
		buffer.append("[" + getName() + "]" + this.getClass().getSimpleName() + "+->");
		if(this.getOutputs() != null) {
			int bufferLength = buffer.length();
			
			for(int i = 0; i < this.getOutputs().length; i++) {
				if(i > 0) {
					buffer.append("\n");
					buffer.append(" ".repeat(bufferLength));
					buffer.append("|\n");
				}
				buffer.append("+->");
				this.getOutputs()[i].layout(buffer);
				
			}
			
			
		}
		
	}
	public double[] getProps() {
		return proportions;
	}
	
}
