/**
 * Classe concettuale che modella un segnale discreto nel dominio del tempo
 * @author Luca Motta
 * @version 0.0
 */

package it.uniroma3.sdr.model;

import java.util.ArrayList;
import java.util.List;

public class Signal {
	
	List<Complex> values;
	
	public Signal(){
		super();
		this.values=new ArrayList<Complex>();
	}

	public Signal(List<Complex> values) {
		this();
		this.values.addAll(values);
	}
	
	
	/*---- GETTERS & SETTERS ----*/
	
	public List<Complex> getValues() {
		return values;
	}
	
	
	public int getLength(){
		return this.getValues().size();
	}
	
	
	/*---- HASHCODE, EQUALS, TOSTRING ----*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Signal other = (Signal) obj;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Signal [values=" + values + "]";
	}	
}
