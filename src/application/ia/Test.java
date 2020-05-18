package application.ia;

import java.awt.Desktop.Action;
import java.io.File;
//
import java.util.HashMap;

import javax.crypto.Cipher;

import application.Fichier;
import application.PlateauMorpion;

public class Test {

	public static void main(String[] args) {
		try {
			//test();
		} 
		catch (Exception e) {
			System.out.println("Test.main()");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static int[] test(Fichier fichier,PlateauMorpion plateauMorpion){
		try {
			int[] layers = new int[]{ 9, 5, 9 };
			
			double error = 0.0 ;
			MultiLayerPerceptron net = new MultiLayerPerceptron(layers, 0.1, new SigmoidalTransferFunction());

			//TRAINING ...
			
			double[][][] training = fichier.lireInputOutput();
			
			if (training != null) {
				
				double[][] inputs = training[0];
				double[][] outputs = training[1];
				
				for(int i = 0; i < inputs.length; i++){
					
					error += net.backPropagate(inputs[i], outputs[i]);
	
					if ( i % 100000 == 0 ) System.out.println("Error at step "+i+" is "+ (error/(double)i));
				}
				error /= inputs.length ;
				System.out.println("Error is "+error);
				
				double[] coup = plateauMorpion.getInput();
				
				double[] coupOutput = net.forwardPropagation(coup);
				
				double max = 0.0;
				
				int indexMax = 0;
				int i = 0;		
				
				int currentLine;
				int currentCol;
				
				int maxLine;
				int maxCol;
				
				for (double d : coupOutput) {
					currentLine = i/3;
					currentCol = i%3;
					
					if (d > max && plateauMorpion.siCaseVide(currentLine, currentCol)) {
						max = d;
						indexMax = i;
					}
					i ++;
				}
				
				int line = indexMax/3;
				int col = indexMax%3;
				
				int coor[] = {line,col};
				
				return coor;
				
				
	
			} else {
				return null;
			}
		} 
		catch (Exception e) {
			System.out.println("Test.test()");
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}

	//CHAMPS ...
	public static HashMap<double[], double[]> mapTrain ;
	public static HashMap<double[], double[]> mapTest ;
	public static HashMap<double[], double[]> mapDev ;
}