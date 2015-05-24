/**
 * Classe concettuale che modella un segnale discreto nel dominio del tempo
 * @author Luca Motta
 * @version 0.0
 */

package it.uniroma3.sdr.model.signal;


import it.uniroma3.sdr.model.Complex;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractSignal {
	
	private List<Complex> values;
	
	public AbstractSignal(){
		this.values = new LinkedList<Complex>();
	}
	
	public AbstractSignal(List<Complex> values){
		this();
		this.values.addAll(values);
	}
	
	/**
	 * calcola l'energia del segnale in questione
	 * @return
	 */
	public double getEnergy() {
		double result = 0;
		
		for(Complex sample : this.values){
			result += sample.absPow();
		}
		
		return result / this.values.size();		
	}
	
	/* getters & setters */
	public int getLength() {
		return this.values.size();
	}

	public List<Complex> getValues() {
		return values;
	}

	public void setValues(List<Complex> values) {
		this.values = values;
	}
	
	public String toString(){
		String s = "";
		for(int i = 0; i < this.getLength(); i++)
			s = s + this.values.get(i).toString() + ", ";
		return s;
	}

	@Override
	public int hashCode(){
		return this.values.hashCode();
	}

}
