package hydraulic;


/**
 * Represents the generic abstract element of an hydraulics system.
 * It is the base class for all elements.
 *
 * Any element can be connect to a downstream element
 * using the method {@link #connect(Element) connect()}.
 * 
 * The class is abstract since it is not intended to be instantiated,
 * though all methods are defined to make subclass implementation easier.
 */
public abstract class Element {
	
	private final int maxIn=10;
	private final int maxOut=10;
	
	
	protected double maxFlow;
	private String name;
	
	
	/**
	 * getter method for the name of the element
	 * 
	 * @return the name of the element
	 *
	 */
	
	public Element(String name) {
		this.name = name;
	}

	
	public String getName() {
		return name;
	}
	
	/**
	 * Connects this element to a given element.
	 * The given element will be connected downstream of this element
	 * 
	 * In case of element with multiple outputs this method operates on the first one,
	 * it is equivalent to calling {@code connect(elem,0)}. 
	 * 
	 * @param elem the element that will be placed downstream
	 */
	public void connect(Element elem) {
		// does nothing by default
		
		if(this.getClass() == Sink.class) {
			Sink si = (Sink) this;
			si.connectSink(elem);
		}
		
		if(this.getClass() == Tap.class) {
			Tap t = (Tap) this;
			t.connectTap(elem);
			
		}
		if(this.getClass() == Source.class) {
			Source s = (Source) this;
			s.connectSource(elem);
			
		}
		
		
	}
	

	
	/**
	 * Connects a specific output of this element to a given element.
	 * The given element will be connected downstream of this element
	 * 
	 * @param elem the element that will be placed downstream
	 * @param index the output index that will be used for the connection
	 */
	public void connect(Element elem, int index){
	
		if(this.getClass() == Split.class) {
			Split s = (Split) this;
			s.connectSplit(elem, index);
			
		}
		
		if(this.getClass() == Multisplit.class) {
			Multisplit m = (Multisplit) this;
			m.connectSplit(elem, index);
		}
	}
	
	public void setInput(Element elem) {
		
		if(this.getClass() == Sink.class) {
			Sink s = (Sink) this;
			s.setInputSink(elem);
		}
		if(this.getClass() == Split.class) {
			Split s = (Split) this;
			s.setInputSplit(elem);
		}
		if(this.getClass() == Tap.class) {
			Tap t = (Tap) this;
			t.setInputTap(elem);
			
		}
		if(this.getClass() == Source.class) {
			//NONE
		}
		
	}
	
	/**
	 * Retrieves the single element connected downstream of this element
	 * 
	 * @return downstream element
	 */
	public Element getOutput(){
		

       //da fare 
		if(this instanceof Sink) {
			//NONE
		}
		if(this instanceof Tap) {
			Tap t = (Tap) this;
			return t.getOutputTap();
		}
		if(this instanceof Source) {
			Source s = (Source) this;
			return s.getOutputSource();
		}
		
		
		
		
		
		return null;
	}

	/**
	 * Retrieves the elements connected downstream of this element
	 * 
	 * @return downstream element
	 */
	public Element[] getOutputs(){
		
		if(this instanceof Split) {
			Split s = (Split) this;
			return s.getOutputsSplit();
		}
		if(this instanceof Multisplit) {
			Multisplit m = (Multisplit) this;
			return m.getOutputsSplit();
		}
		
		
		
		
		
		
		return null;
	}
	
	/**
	 * Defines the maximum input flow acceptable for this element
	 * 
	 * @param maxFlow maximum allowed input flow
	 */
	public void setMaxFlow(double maxFlow) {
		this.maxFlow = maxFlow;
	}
	
	
	public void startSimulate(SimulationObserver observer, boolean enableMaxFlowCheck) {
		// TODO Auto-generated method stub
		double f;
		Source s = (Source) this;
		f = s.getFlowOut();
		
		if(enableMaxFlowCheck && f > maxFlow) 
			observer.notifyFlow("Source",s.getName(),Double.NaN,maxFlow);
		else 
			observer.notifyFlow("Source",s.getName(),Double.NaN, s.getFlowOut());
		
		Element x = s.getOutputSource();
		x.continueSimulate(observer,f,enableMaxFlowCheck);
		
	}
	
	public void continueSimulate(SimulationObserver observer, double f, boolean enableMaxFlowCheck) {
		Element out;
		Element[] outs;
		Element x = this;
		
		
		
		if(x instanceof Sink) {
			Sink si = (Sink) x;
			si.setFlowSink(f);
			if(enableMaxFlowCheck && f > maxFlow) {
				observer.notifyFlowError("Sink",si.getName(),f,maxFlow);
			}else {
				observer.notifyFlow("Sink",si.getName(),f, observer.NO_FLOW);
			}
			
			
		}
		
		if(x instanceof Tap) {
			Tap t = (Tap) x;
			t.setFlowTapIn(f);
			
			
			if(t.getOpen() == true)
				t.setFlowTapOut(f);
			else {
				t.setFlowTapOut(0);
			}
			
			if(enableMaxFlowCheck && f > maxFlow) {
					observer.notifyFlowError("Tap",t.getName(),f,maxFlow);
			}else{
					observer.notifyFlow("Tap",t.getName(),f,t.getFlowOut());
					out = t.getOutput();
					if(out != null) {
						out.continueSimulate(observer,t.getFlowOut(),enableMaxFlowCheck);
				}
			}
			
		}
		
		
		
		
		if(x instanceof Split) {
			
			if(x instanceof Multisplit) {
				Multisplit m = (Multisplit) x;
				m.setFlowInSplit(f);
				double[] flows = m.getOutFlows();
				
				if(enableMaxFlowCheck && f > maxFlow) {
					observer.notifyFlowError("Multisplit",m.getName(),f,maxFlow);
				}else{
					observer.notifyFlow("Multisplit",m.getName(),f, flows); //da cambiare
				}
				
				outs = m.getOutputsSplit();
				double[] props = m.getProps();
				for(int i = 0; i < outs.length; i++ )
					if(outs[i] != null)
						outs[i].continueSimulate(observer,f*props[i],enableMaxFlowCheck);
				
				
				
			}
			else {
		
				Split sp = (Split) x;
				sp.setFlowInSplit(f);
				
				if(enableMaxFlowCheck && f > maxFlow) {
					observer.notifyFlowError("Split",sp.getName(),f,maxFlow);
				}
				else {
					observer.notifyFlow("Split",sp.getName(),f,f/2,f/2);
				}
					
				outs = sp.getOutputsSplit();
				for(Element o : outs)
					if(o != null)
						o.continueSimulate(observer,f/2,enableMaxFlowCheck);
			}
			
		}
		
		
		
		
	
	
	}
	
	public void printOutputs(Element el[]) {
		for(Element e : el)
			System.out.println(e.getName());
		
	}
	
	
		
	
		protected abstract void layout(StringBuffer output);
		public abstract Element getInput();
			// TODO Auto-generated method stub
		


	
	
	
	
}
