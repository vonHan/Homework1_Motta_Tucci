package it.uniroma3.sdr.controller;

import it.uniroma3.sdr.model.Complex;
import it.uniroma3.sdr.model.EvaluatorSignal;
import it.uniroma3.sdr.model.UsefullSignal;
import java.util.List;


public class Main {

	public static void main(String[] args) {

		try{
			ComplexNumbersReader complexNumbersReader = new ComplexNumbersReader();
			String path = null;
			
			EvaluatorSignal evaluatorSignal = new  EvaluatorSignal();
			List<Complex> signalValues = null;
			UsefullSignal signal = null;
			double snr;
			double treshold;
			List<Double> energiesNoisesList = null;
			double probabilityDetection;
//			List<Complex> signalValues= complexNumbersReader.execute(path);
//			UsefullSignal signal = new UsefullSignal(signalValues);
//			
//			double snr = evaluatorSignal.calculateSNR(signal);
//			
//			List<Double> energiesNoisesList = evaluatorSignal.energiesNoiseSamples(1000, snr, 10000);
//			
//			double treshold = evaluatorSignal.treshold(energiesNoisesList);
//			double probabilityDetection = evaluatorSignal.calculateProbabilityDetection(treshold, signal, 1000);
//			
//			System.out.println(probabilityDetection);
			
			for(int i=1; i<13; i++){
				path = "/Users/luke1993/Documents/workspace/Homework1_Motta_Tucci/sequenze/output_"+i+".dat";
				signalValues= complexNumbersReader.execute(path);
				
				signal = new UsefullSignal(signalValues);
				snr = evaluatorSignal.calculateSNR(signal);
				
				energiesNoisesList = evaluatorSignal.energiesNoiseSamples(1000, snr, 10000);
				treshold = evaluatorSignal.treshold(energiesNoisesList);
				
				probabilityDetection = evaluatorSignal.calculateProbabilityDetection(treshold, signal, 1000);
				
				System.out.println("*** SEQUENZA "+i+" ***");
				System.out.println("-snr: "+snr);
				System.out.println("-soglia: "+treshold);
				System.out.println("-probabilita' di detection: "+treshold+"\n");
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}

		
		

	}

}
