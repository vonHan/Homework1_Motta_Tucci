package it.uniroma3.sdr.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Noise extends AbstractSignal {

	private double varianza;
	private int length;
	private List<Complex> signal;
	private double pot_rumore;

	public Noise() {
	}

	public Noise(double snr, int length){
		Random campione = null;
		double snr_linearizzato = Math.pow(10, (snr / 10));
		this.pot_rumore = (1 / snr_linearizzato);
		this.length = length;

		this.signal = new ArrayList<Complex>(length);
		for(int i = 0; i < this.length; i++) {
			campione = new Random();
			signal.get(i).setParteReale(campione.nextGaussian() * Math.sqrt(pot_rumore / 2));
		}

		for(int i = 0; i < this.length; i++) {
			campione = new Random();
			signal.get(i).setParteImmaginaria(campione.nextGaussian() * Math.sqrt(pot_rumore / 2));
		}
	}

	public double getVarianza() {
		return varianza;
	}

	public void setVarianza(double varianza) {
		this.varianza = varianza;
	}

	public int getLength() {
		return length;
	}

	public List<Complex> getSignal() {
		return this.signal;
	}

	public void setSignal(ArrayList<Complex> signal) {
		this.signal = signal;
	}

	public double getPot_rumore() {
		return pot_rumore;
	}

	public void setPot_rumore(double pot_rumore) {
		this.pot_rumore = pot_rumore;
	}

//	public double energyNoise() {
//		double result = 0;
//		for(int i = 0; i < this.length; i++) {
//			result += Math.pow(this.signal.get(i).getParteReale(), 2) + 
//					Math.pow(this.signal.get(i).getParteImmaginaria(), 2);
//		}
//		return result / length;		
//	}

}
