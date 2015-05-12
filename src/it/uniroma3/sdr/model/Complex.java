/**
 * Classe concettuale che modella i numeri complessi e relative operazioni
 * @author Luca Motta
 * @version 0.0 
 */

package it.uniroma3.sdr.model;

public class Complex {
	
	private double parteRe;
	private double parteImm;
	
	
	public Complex(double parteRe, double parteImm) {
		super();
		this.parteImm = parteImm;
		this.parteRe = parteRe;
	}

	
	/*---- GETTERS & SETTERS ----*/
	
	public double getParteImm() {
		return parteImm;
	}

	
	public double getParteRe() {
		return parteRe;
	}
	
	
	/*--- OPERAZIONI DI BASE TRA NUMERI COMPLESSI ----*/
	
	public Complex sum(Complex that){
		return new Complex(this.getParteRe()+that.getParteRe(), this.getParteImm()+that.getParteImm());
	}
	
	
	public Complex diff(Complex that){
		return new Complex(this.getParteRe()-that.getParteRe(), this.getParteImm()-that.getParteImm());
	}
	
	
	public Complex mult(Complex that){
		double parteRe=this.getParteRe()*that.getParteRe() - this.getParteImm()*that.getParteImm();
		double parteImm=this.getParteRe()*that.getParteImm() + this.getParteImm()*that.getParteRe();
				
		return new Complex(parteRe, parteImm);
	}
	
	
	
	public Complex div(Complex that){
		double parteRe = (this.getParteRe()*that.getParteRe() + this.getParteImm()*that.getParteImm()) / (Math.pow(that.getParteRe(), 2) + Math.pow(that.getParteImm(), 2));
		double parteImm = (this.getParteImm()*that.getParteRe() - this.getParteRe()*that.getParteImm()) / (Math.pow(that.getParteRe(), 2) + Math.pow(that.getParteImm(), 2));
		
		return new Complex(parteRe, parteImm);
	}
	
	
	public Complex coniugato(){
		return new Complex(this.parteRe, - this.parteImm);
	}
	
	
	public double abs(){
		return Math.sqrt(Math.pow(this.parteRe,2) + Math.pow(this.parteImm,2));
	}

	/*---- HASHCODE. TOSTRING, EQUAL ----*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(parteImm);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(parteRe);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Complex other = (Complex) obj;
		if (Double.doubleToLongBits(parteImm) != Double
				.doubleToLongBits(other.parteImm))
			return false;
		if (Double.doubleToLongBits(parteRe) != Double
				.doubleToLongBits(other.parteRe))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Complex [parteRe=" + parteRe + ", parteImm=" + parteImm + "]";
	}
	
		
}
