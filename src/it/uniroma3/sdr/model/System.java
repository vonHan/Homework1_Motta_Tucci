package it.uniroma3.sdr.model;
import java.util.*;
public class System {

	/**
	 * riceve un <strong>signal</strong> in input e se possibile lo partiziona in
	 * segnali della dimensione <strong>sizePartition</strong>
	 * @param signal
	 * @param sizePartition
	 * @return
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
		
		for(int i = 0; i < energies.size(); i++)
			if(energies.get(i) > treshold)
				counter++;
		return  (counter / energies.size()) * 100;
	}

}
