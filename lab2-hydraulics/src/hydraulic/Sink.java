package hydraulic;

/**
 * Represents the sink, i.e. the terminal element of a system
 *
 */
public class Sink extends Element {
	Element input;
	double flowIn;

	/**
	 * Constructor
	 * @param name name of the sink element
	 */
	public Sink(String name) {
		super(name);
	
	}
	public void setInputSink(Element elem) {
		this.input = elem;
	}
	
	public void setFlowSink(double f) {
		this.flowIn = f;
	}
	
	public void connectSink(Element elem) {
		//setInput(elem);
	}
	
	@Override
	protected void layout(StringBuffer output) {
		// TODO Auto-generated method stub
		output.append("[" + getName() + "]" + "Sink");
		
		
	}
	@Override
	public Element getInput() {
		// TODO Auto-generated method stub
		return this.input;
	}
	public double getFlow() {
		// TODO Auto-generated method stub
		return this.flowIn;
	}
	public double getFlowOut() {
		return Double.NaN;
	}
	
	
}
