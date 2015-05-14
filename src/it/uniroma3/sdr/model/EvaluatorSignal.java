package it.uniroma3.sdr.model;
import it.uniroma3.sdr.controller.LettoreNumeriComplessi;

import java.util.*;
public class EvaluatorSignal {

	/**
	 * riceve un <strong>signal</strong> in input e se possibile lo partiziona in
	 * segnali della dimensione <strong>sizePartition</strong>
	 * @param signal
	 * @param sizePartition
	 * @return lista in cui sono presenti le partizioni di dimensione <strong>sizePartition</strong>
	 * 			di <strong>signal</strong>, caso in cui la dimensione di signal non sia un
	 * 			multiplo di sizePartition ritornerà <strong>null</strong>
	 */
	public List<UsefullSignal> partitionedSignal(UsefullSignal signal, int sizePartition){
		List<UsefullSignal> partionedSignal = new LinkedList<UsefullSignal>();
		List<Complex> signalValues = signal.getValues();
		
		if(signalValues.size() % sizePartition != 0)
			return null;
		
		int counter = 0;
		UsefullSignal partitionTemp = new UsefullSignal(sizePartition);

		for(Complex element : signalValues) {
			if(counter == sizePartition - 1){
				partionedSignal.add(partitionTemp);
				partitionTemp = new UsefullSignal(sizePartition);
				counter=0;
			}
			partitionTemp.getValues().add(element);
			counter++;
		}
		return partionedSignal;
	}

	/**
	 * dato il <strong>partitionedSignalValues</strong> ne calcola l'energia per
	 * ciascuno dei partizionamenti
	 * @param partitionedSignalValues
	 * @return
	 */
	public List<Double> getEnergiesFromPartitionedSignal(List<UsefullSignal> partitionedSignalValues) {
		List<Double> energies = new ArrayList<Double>(partitionedSignalValues.size());
		for(UsefullSignal element : partitionedSignalValues)
			energies.add(element.getEnergy());
		return energies;
	}

	/**
	 * confronta le energie dei partizionamenti ottenuti dal segnale partizionato
	 * con la <strong>treshold</strong> per calcolare la probabilità di detection
	 * in un valore percentuale
	 * @param treshold
	 * @param signal
	 * @param sizePartition
	 * @return
	 */
	public double calculateProbabilityDetection(double treshold, UsefullSignal signal, int sizePartition){
		List<Double> energies = this.getEnergiesFromPartitionedSignal(this.partitionedSignal(signal, sizePartition));
		int counter = 0;
		int energiesSize = energies.size();
		
		for(int i = 0; i < energiesSize; i++){
			if(energies.get(i) > treshold)
				counter++;
		}

		return  (counter / energies.size()) * 100;
	}
	
	
	/**
	 * Calcola l'SNR Signal-to-Noise Ratio di un segnale
	 * 
	 * @return double SNR
	 */
	public double calculateSNR(List<Complex> sequenceValues){
		double energieTotal = 0;

		for(Complex sample: sequenceValues){
			energieTotal = sample.absSquare() + energieTotal;
		}

		return 1/(energieTotal -1);
	}
	
	public static void main(String[] args){
		try{
			LettoreNumeriComplessi lettoreNumeriComplessi = new LettoreNumeriComplessi();
			String path = "/Users/luke1993/Documents"
					+ "/workspace/Homework1_Motta_Tucci/sequenze/output_1.dat";
			
			EvaluatorSignal evaluatorSignal = new EvaluatorSignal();
			List<Complex> sequenceValues = null;
			
			for(int i=1; i<13; i++){
				path = "/Users/luke1993/Documents"
						+ "/workspace/Homework1_Motta_Tucci/sequenze/output_"+i+".dat";
				sequenceValues = lettoreNumeriComplessi.leggiNumeriComplessi(path);
				System.out.println(evaluatorSignal.calculateSNR(sequenceValues));
			}
			
		}catch(Exception e){
			System.out.println("Impossibile leggere segnale da file");
		}

	}
}
