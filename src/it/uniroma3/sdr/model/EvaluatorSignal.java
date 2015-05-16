package it.uniroma3.sdr.model;
import it.uniroma3.sdr.controller.ComplexNumbersReader;
import it.uniroma3.sdr.controller.Statistics;

import java.util.*;
public class EvaluatorSignal {

	
	/**
	 * calcola la soglia
	 * @param probabilityFalseAlarm
	 * @param snr
	 * @return
	 */
	public double treshold(List<Double> energies) {
		Statistics statistics = new Statistics();
		double probabilityFalseAlarm = 0.001;
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
	
	
	/**
	 * riceve un <strong>signal</strong> in input e se possibile lo partiziona in
	 * segnali della dimensione <strong>sizePartition</strong>
	 * @param signal
	 * @param sizePartition
	 * @return lista in cui sono presenti le partizioni di dimensione <strong>sizePartition</strong>
	 * 			di <strong>signal</strong>, caso in cui la dimensione di signal non sia un
	 * 			multiplo di sizePartition ritornerà <strong>null</strong>
	 */
	public List<UsefullSignal> partitionedSignal(AbstractSignal signal, int sizePartition){
		List<UsefullSignal> partionedSignal = new LinkedList<UsefullSignal>();
		List<Complex> signalValues = signal.getValues();
		if(signalValues.size() % sizePartition != 0)
			return null;
		
		int counter = 0;
		UsefullSignal partitionTemp = new UsefullSignal(sizePartition);

		for(Complex element : signalValues) {
			if(counter == sizePartition){
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
		for(UsefullSignal element : partitionedSignalValues){
			energies.add(element.getEnergy());
			System.out.println("Energy i: "+ element.getEnergy());
		}

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
	public double calculateProbabilityDetection(double treshold, AbstractSignal signal, int sizePartition){
		List<Double> energies = this.getEnergiesFromPartitionedSignal(this.partitionedSignal(signal, sizePartition));
		int counter = 0;
		int energiesSize = energies.size();
		
		for(int i = 0; i < energiesSize; i++){
			if(energies.get(i) > treshold)
				counter++;
		}
		return (counter/energiesSize)*100;
	}
	
	
	/**
	 * Calcola l'SNR Signal-to-Noise Ratio di un segnale
	 * in decibel
	 * 
	 * @return double SNR
	 */
	public double calculateSNR(AbstractSignal signal){
		return 10*Math.log10(1/(signal.getEnergy()-1));
	}
	
	
	/**
	 * metodo che restituisce una lista di energie relative ad
	 * un numero di rumori <strong>numberOfNoises</strong> nelle quali viene generato di
	 * volta in volta un nuovo rumore con parametri <strong>snr</strong> e <strong>length</strong>
	 * @param numberOfNoises
	 * @param snr
	 * @param length
	 * 		   
	 */
	public List<Double> energiesNoiseSamples(int numberOfNoises, double snr, int length){
		List<Double> energies = new LinkedList<Double>();
		NoiseSignal noise = null;
		
		for(int i = 0; i < numberOfNoises; i++){
			noise = new NoiseSignal(snr, length);
			energies.add(new Double(noise.getEnergy()));
		}
		return energies;
	}
	
	public static void main(String[] args){
		try{
			ComplexNumbersReader lettoreNumeriComplessi = new ComplexNumbersReader();
			String path = null;
			
			EvaluatorSignal evaluatorSignal = new EvaluatorSignal();
			List<Complex> sequenceValues = null;
			UsefullSignal signalTemp = null;
			
			for(int i=1; i<13; i++){
				path = "/Users/luke1993/Documents"
						+ "/workspace/Homework1_Motta_Tucci/sequenze/output_"+i+".dat";
				sequenceValues = lettoreNumeriComplessi.execute(path);
				signalTemp = new UsefullSignal(sequenceValues);
				System.out.println(evaluatorSignal.calculateSNR(signalTemp));
			}
			
		}catch(Exception e){
			System.out.println("Impossibile leggere segnale da file");
		}

	}
}
