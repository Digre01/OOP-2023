package hydraulic;
import hydraulic.*;
import static hydraulic.SimulationObserver.NO_FLOW;

/**
 * Represents a source of water, i.e. the initial element for the simulation.
 *
 * Lo status of the source is defined through the method
 * {@link #setFlow(double) setFlow()}.
 */
public class Source extends Element {
	private Element output;
	private double flowIn = NO_FLOW;
	private double flowOut = NO_FLOW;

	/**
	 * constructor
	 * @param name name of the source element
	 */
	public Source(String name) {
		super(name);
	}
	
	
	
	public void connectSource(Element elem) {
		this.output = elem;
	}
	

	/**
	 * Define the flow of the source to be used during the simulation
	 *
	 * @param flow flow of the source (in cubic meters per hour)
	 */
	public void setFlow(double flow){
		this.flowOut = flow;
	}
	public double getFlowOut() {
		return this.flowOut;
	}
	
	public Element getOutputSource() {
		return this.output; 
	}



	@Override
	protected void layout(StringBuffer buffer) {
		// TODO Auto-generated method stub
		buffer.append("[" + getName() + "]" + "Source");
		if(this.getOutputSource() != null) {
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
		return null;
	}



	public void setFlowSink(double f) {
		// TODO Auto-generated method stub
		this.flowIn = f;
	}
	
	




	
	

}
