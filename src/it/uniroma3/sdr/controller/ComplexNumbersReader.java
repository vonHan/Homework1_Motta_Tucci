package it.uniroma3.sdr.controller;

import it.uniroma3.sdr.model.Complex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class ComplexNumbersReader {
	
	/**
	 * Legge i numeri complessi nel file associato al 
	 * path d'input
	 * @param path del file
	 * @return Lista di numeri complessi
	 */
	public List<Complex> execute(String path) {
		BufferedReader reader = null;
		List<Complex> readSignal = new LinkedList<Complex>();

		try {
			reader = new BufferedReader(new FileReader(new File(path)));

			String line;
			while ((line = reader.readLine()) != null) {
				String[] reImm = line.split(" |\\t");
				Complex complex = new Complex(Double.parseDouble(reImm[0]), Double.parseDouble(reImm[1]));
				readSignal.add(complex);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return readSignal;
	}


}
