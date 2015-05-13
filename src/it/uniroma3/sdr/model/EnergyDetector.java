package it.uniroma3.sdr.model;

import it.uniroma3.sdr.controller.Statistics;

import java.util.List;

public class EnergyDetector {
	
	/**
	 * calcola la soglia
	 * @param probabilityFalseAlarm
	 * @param snr
	 * @return
	 */
	public double treshold(List<Double> energies) {
		Statistics statistics = new Statistics();
		double probabilityFalseAlarm = 1 / energies.size();
		double valoreMedio = statistics.valoreMedio(energies);
		double treshold = 0;
		double varianza = statistics.varianza(energies);
		try {
			treshold =  valoreMedio + (Math.sqrt(2 * varianza) * statistics.InvErf(1 - 2 * probabilityFalseAlarm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return treshold;
	}
}
