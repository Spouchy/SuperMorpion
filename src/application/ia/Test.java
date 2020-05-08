package application.ia;

import java.awt.Desktop.Action;
import java.io.File;
//
import java.util.HashMap;

import application.Fichier;

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

	public static int[] test(Fichier fichier,double[] coup){
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
				//
				System.out.println("Learning completed!");
	
				//TEST ...
				
				for (double d : coup) {
					System.out.println("Action : "+ d);
				}
				
				double[] coupOutput = net.forwardPropagation(coup);
	
				for (double d : coupOutput) {
					System.out.println("Prediction : "+ d);
				}
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