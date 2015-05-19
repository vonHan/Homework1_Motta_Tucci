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
		
//	/**
//	 * da finire questo Costruttore
//	 * @param length
//	 */
//	public UsefullSignal (int length) {
//		super.setValues(new LinkedList<Complex>());
//		for(int i = 0; i < length; i++) {
//			Complex complex = new Complex();
//			double v = Math.random();
//			
//			if(v < 0.5)
//				complex.setParteReale(1 / Math.sqrt(2));
//			else
//				complex.setParteReale(- 1 / Math.sqrt(2));
//			double p = Math.random();
//			
//			if(p < 0.5)
//				complex.setParteImmaginaria(1 / Math.sqrt(2));
//			else
//				complex.setParteImmaginaria(- 1 / Math.sqrt(2));
//			super.getValues().add(complex);
//		}
//	}
//	
	
	
	
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