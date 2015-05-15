package it.uniroma3.sdr.controller;

import it.uniroma3.sdr.model.Complex;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ComplexNumbersReader {

	/**
	 * Legge i numeri complessi nel file associato al 
	 * path d'input
	 * @param path del file
	 * @return Lista di numeri complessi
	 * @throws IOException
	 */
	public List<Complex> execute(String path) throws IOException{
		try{
			FileReader reader = new FileReader(path);
			Scanner scanner = new Scanner(reader);
			List<Complex> values = new ArrayList<Complex>();
			Complex tempComplex = null;
			double pRe;
			double pImm;

			while(scanner.hasNextLine()){

				pRe=scanner.nextDouble();
				pImm=scanner.nextDouble();
				tempComplex = new Complex(pRe, pImm);
				values.add(tempComplex);
				scanner.nextLine();
			}

			scanner.close();
			return values;

		}catch(IOException e){
			throw e;
		}	
	}
	

	public static void main(String[] args){
		ComplexNumbersReader lettore = new ComplexNumbersReader();
		try{
			List<Complex> values = lettore.execute("/Users/luke1993/Documents"
					+ "/workspace/Homework1_Motta_Tucci/sequenze/Sequenza_1/output_1.dat");

			for(Complex element : values)
				System.out.println(element.toString());


		}catch(Exception e){
			System.out.println("ERRORE");
		}
	}
}
