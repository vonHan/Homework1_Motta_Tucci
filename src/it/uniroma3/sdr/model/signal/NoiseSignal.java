package it.uniroma3.sdr.model.signal;


import it.uniroma3.sdr.model.Complex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoiseSignal extends AbstractSignal {
	private int length;
	private double pot_rumore;

	public NoiseSignal() {
		super();
	}

	public NoiseSignal(double snr, int length){
		Random campione = null;
		double snr_linearizzato = Math.pow(10, (snr / 10));
		this.pot_rumore = (1 / snr_linearizzato);
		this.length = length;

		List<Complex> samplesValues = new ArrayList<Complex>();
		Complex sample = null;
		double parteReale;
		double parteImmaginaria;
		
		for(int i = 0; i < this.length; i++) {
			campione = new Random();
			parteReale = campione.nextGaussian() * Math.sqrt(pot_rumore / 2);
			parteImmaginaria = campione.nextGaussian() * Math.sqrt(pot_rumore / 2);
			sample = new Complex(parteReale, parteImmaginaria);
			samplesValues.add(sample);
		}
		this.setValues(samplesValues);
	}
}
