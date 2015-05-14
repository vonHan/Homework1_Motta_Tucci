/**
 * Classe concettuale che modella i numeri complessi e relative operazioni
 * @author Luca Motta
 * @version 0.0 
 */

package it.uniroma3.sdr.model;

public class Complex {
	
	private double parteReale;
	private double parteImmaginaria;
	
	public Complex() {
	}
	
	public Complex(double parteReale, double parteImmaginaria) {
		super();
		this.parteReale = parteReale;
		this.parteImmaginaria = parteImmaginaria;
	}

	
	/*---- GETTERS & SETTERS ----*/
	
	public double getParteImmaginaria() {
		return parteImmaginaria;
	}

	
	public double getParteReale() {
		return parteReale;
	}
	
	public void setParteReale(double parteReale) {
		this.parteReale = parteReale;
	}


	public void setParteImmaginaria(double parteImmaginaria) {
		this.parteImmaginaria = parteImmaginaria;
	}
	
	/*--- OPERAZIONI DI BASE TRA NUMERI COMPLESSI ----*/
	



	public Complex sum(Complex that){
		return new Complex(this.getParteReale()+that.getParteReale(), this.getParteImmaginaria()+that.getParteImmaginaria());
	}
	
	
	public Complex diff(Complex that){
		return new Complex(this.getParteReale()-that.getParteReale(), this.getParteImmaginaria()-that.getParteImmaginaria());
	}
	
	
	public Complex mult(Complex that){
		double parteRe=this.getParteReale()*that.getParteReale() - this.getParteImmaginaria()*that.getParteImmaginaria();
		double parteImm=this.getParteReale()*that.getParteImmaginaria() + this.getParteImmaginaria()*that.getParteReale();
				
		return new Complex(parteRe, parteImm);
	}
	
	
	
	public Complex div(Complex that){
		double parteRe = (this.getParteReale()*that.getParteReale() + this.getParteImmaginaria()*that.getParteImmaginaria()) / (Math.pow(that.getParteReale(), 2) + Math.pow(that.getParteImmaginaria(), 2));
		double parteImm = (this.getParteImmaginaria()*that.getParteReale() - this.getParteReale()*that.getParteImmaginaria()) / (Math.pow(that.getParteReale(), 2) + Math.pow(that.getParteImmaginaria(), 2));
		
		return new Complex(parteRe, parteImm);
	}
	
	
	public Complex coniugato(){
		return new Complex(this.parteReale, - this.parteImmaginaria);
	}
	
	
	public double abs(){
		return Math.sqrt(Math.pow(this.parteReale,2) + Math.pow(this.parteImmaginaria,2));
	}
	
	/**
	 * Restituisce il modulo quadro di questo numero complesso
	 * @return double
	 */
	public double absSquare(){
		return Math.pow(this.parteReale,2) + Math.pow(this.parteImmaginaria,2);
	}

	/*---- HASHCODE. TOSTRING, EQUAL ----*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(parteImmaginaria);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(parteReale);
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
		if (Double.doubleToLongBits(parteImmaginaria) != Double
				.doubleToLongBits(other.parteImmaginaria))
			return false;
		if (Double.doubleToLongBits(parteReale) != Double
				.doubleToLongBits(other.parteReale))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Complex [parteRe=" + parteReale + ", parteImm=" + parteImmaginaria + "]";
	}
	
		
}
