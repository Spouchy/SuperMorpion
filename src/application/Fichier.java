package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.xml.sax.InputSource;

public class Fichier extends File{
	
	public Fichier(String fichier) {
		super(fichier);
	}	
	
	public void ecrireLigne(String text) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this,true));
			
			bufferedWriter.write(text);
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getNumberLine() {
		FileReader input;
		try {
			input = new FileReader(this);
			LineNumberReader count = new LineNumberReader(input);
			
			while (count.skip(Long.MAX_VALUE) > 0)
			{
     
			}
			
			return count.getLineNumber();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;

	}
	
	public double[][][] lireInputOutput() {
		int nbLine = getNumberLine();
		
		double[][] inputs = new double[nbLine][];
		double[][] outputs = new double[nbLine][];
		
	      
		try {
			String thisLine = null;
			String[] inputOutput = null;
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(this));     
			
			int i = 0;
			while ((thisLine = bufferedReader.readLine()) != null) {
				inputOutput = thisLine.split("\t");
				
				double[] ligneInput = Outil.stringToDoubleArray(inputOutput[0], ",");
				double[] ligneOutput = Outil.stringToDoubleArray(inputOutput[1], ",");
				
				inputs[i] = ligneInput;
				outputs[i] = ligneOutput;
				
				i++;
			} 
			bufferedReader.close();
			
			double[][][] result = new double[2][][];
			
			result[0] = inputs;
			result[1] = outputs;
			
			return result;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void afficher() {
		String[] liste = this.list();
		System.out.println(liste[0]);
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(this));
			String line;
			while ((line = br.readLine()) != null) {
			   // process the line.
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
