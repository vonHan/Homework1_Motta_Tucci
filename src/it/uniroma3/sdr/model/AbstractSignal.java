/**
 * Classe concettuale che modella un segnale discreto nel dominio del tempo
 * @author Luca Motta
 * @version 0.0
 */

package it.uniroma3.sdr.model;


import java.util.LinkedList;
import java.util.List;

public abstract class AbstractSignal {
	
	private int length;
	private List<Complex> values;
	
	public AbstractSignal(){
		this.values = new LinkedList<Complex>();
	}
	
	public AbstractSignal(LinkedList<Complex> values){
		this();
		this.values.addAll(values);
	}
	

	/* getters & setters */
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
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
