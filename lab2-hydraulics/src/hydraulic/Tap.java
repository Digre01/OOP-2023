package hydraulic;

/**
 * Represents a tap that can interrupt the flow.
 * 
 * The status of the tap is defined by the method
 * {@link #setOpen(boolean) setOpen()}.
 */

public class Tap extends Element {
	
	private Element input;
	private Element output;
	private boolean open = false;
	
	
	private double flowIn;
	private double flowOut;
	/**
	 * Constructor
	 * @param name name of the tap element
	 */
	public Tap(String name) {
		super(name);
	}

	/**
	 * Set whether the tap is open or not. The status is used during the simulation.
	 *
	 * @param open opening status of the tap
	 */
	
	public void setInputTap(Element elem) {
		this.input = elem;
	}
	public void connectTap(Element elem) {
		this.output = elem;
		elem.setInput(this);
	}
	
	
	public void setOpen(boolean open){
		this.open = open;
	}
	
	public void setFlowTapIn(double f) {
		this.flowIn = f;
	}
	public void setFlowTapOut(double f) {
		this.flowOut = f;
	}
	
	
	public double getFlowIn() {
		return this.flowIn;
	}
	public double getFlowOut() {
		return this.flowOut;
	}
	
	public boolean getOpen() {
		return this.open;
	}
	
	public Element getOutputTap() {
		return this.output;
	}

	@Override
	protected void layout(StringBuffer buffer) {
		// TODO Auto-generated method stub
		
		buffer.append("[" + getName() + "]" + "Tap");
		if(this.getOutputTap() != null) {
			buffer.append("->");
			output.layout(buffer); //continue
		}
		else {
			buffer.append("*");
		}
		
	}

	@Override
	public Element getInput() {
		// TODO Auto-generated method stub
		return this.input;
	}
	
	
}
