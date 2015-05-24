package it.uniroma3.sdr.model.signal;

import it.uniroma3.sdr.model.Complex;

import java.util.List;

public class GenericSignal extends AbstractSignal {
	

	public GenericSignal(){
		super();
	}
	
	public GenericSignal(List<Complex> values) {
		super(values);
	}	
	
	@Override
	public boolean equals(Object o){
		GenericSignal s = (GenericSignal)o;
		boolean b = true;
		if(super.getValues().size() != s.getLength())
			return false;
		
		for(int i = 0; i < s.getLength(); i++){
			b = b && (super.getValues().get(i).equals(s.getValues().get(i)));
		}
		return b;
	}
}