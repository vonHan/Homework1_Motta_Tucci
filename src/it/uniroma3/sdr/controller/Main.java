package it.uniroma3.sdr.controller;

import it.uniroma3.sdr.model.Complex;
import it.uniroma3.sdr.model.EvaluatorSignal;
import it.uniroma3.sdr.model.signal.AbstractSignal;
import it.uniroma3.sdr.model.signal.GenericSignal;

import java.util.List;


public class Main {

	public static void main(String[] args) {

		try{
			ComplexNumbersReader complexNumbersReader = new ComplexNumbersReader();
			String path = null;
			
			EvaluatorSignal evaluatorSignal = new  EvaluatorSignal();
			List<Complex> signalValues = null;
			AbstractSignal signal = null;
			List<Double> energiesNoisesList = null;
			double snr;
			double treshold;
			double probabilityDetection;
			
			for(int i=1; i<13; i++){
				path = "sequenze/output_"+i+".dat";
				signalValues= complexNumbersReader.execute(path);
				
				signal = new GenericSignal(signalValues);
				snr = evaluatorSignal.calculateSNR(signal);
				
				energiesNoisesList = evaluatorSignal.makeEnergiesNoises(1000, snr, 10000);
				treshold = evaluatorSignal.treshold(energiesNoisesList);
				
				probabilityDetection = evaluatorSignal.calculateProbabilityDetection(treshold, signal, 1000);
				
				System.out.println("*** SEQUENZA "+i+" ***");
				System.out.println("-snr(dB): "+snr);
				System.out.println("-soglia: "+treshold);
				System.out.println("-probabilita' di detection: "+probabilityDetection+"%\n");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}

		
		

	}

}
