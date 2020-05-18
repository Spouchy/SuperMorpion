package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.spi.LocaleNameProvider;

import application.ia.Test;
import javafx.scene.image.Image;

public class PlateauMorpion {
	private Fichier fichier;
	private static String messageFin;
	private int n;
	private static int NB_JOUEUR = 1;
	private static int DIFFICULTE = 0;
	private static boolean IN_TRAINING = false;
	public boolean siJeuxEnCours;
	private String imgJoueur1 = "file:src/application/images/spiderpng2.png";
	private String imgJoueur2 = "file:src/application/images/batman_png2.png";
	private int tourJoueur = 1;
	private int[][] matricePlateau =
	    {
	        { 0,0,0 } ,
	        { 0,0,0 } ,
	        { 0,0,0 }
	    };
	
	public PlateauMorpion() {
		siJeuxEnCours = true;
		n = 3;
		fichier = new Fichier("data.txt");
		
		Random random = new Random();
		tourJoueur = random.nextInt(2) + 1;
	}
	
	public boolean siVictoire(int i,int j) {
		int chaine = 0;
		int keyJoueur = tourJoueur;
		
		// Verification horizontale ( ligne )
		for (int h = 0; h < n; h++) {
			if (matricePlateau[i][h] == keyJoueur) {
				chaine ++;
				if (chaine >= n) {
					siJeuxEnCours = false;
					return true;
				}
			}
		}
		
		chaine = 0;
		
		// Verification verticale ( colonne ) 
		for (int v = 0; v < n; v++) {
			if (matricePlateau[v][j] == keyJoueur) {
				chaine ++;
				if (chaine >= n) {
					siJeuxEnCours = false;
					return true;
				}
			}
		}
		
		chaine = 0;
		
		// Verification diagonal
		if ( i == j) {
			for (int d = 0; d < n; d++) {
				if (matricePlateau[d][d] == keyJoueur) {
					chaine ++;
					if (chaine >= n) {
						siJeuxEnCours = false;
						return true;
					}
				}
			}
		}
		
		chaine = 0;
		
		// Verification anti-diagonal
		if ( i + j == n - 1) {
			for (int ad = 0; ad < n; ad++) {
				if (matricePlateau[ad][(n-1)-ad] == keyJoueur) {
					chaine ++;
					if (chaine >= n) {
						siJeuxEnCours = false;
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public String messageVictoire() {
		return "Joueur ["+tourJoueur+"] à gagné la partie !";
	}
	
	public String messagePartieFini() {
		return "La partie est finie !";
	}
	
	public boolean siFini() {
		for (int h = 0; h < n; h++) {
			for (int v = 0; v < n; v++) {
				if (matricePlateau[h][v] == 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean siCaseVide(int i,int j) {
		if (matricePlateau[i][j] == 0) {
			return true;
		}
		
		return false;
	}
	
	public void placerPion(int i,int j) {
		matricePlateau[i][j] = tourJoueur;
	}
	
	public void joueurSuivant() {
		if (tourJoueur == 1) {
			tourJoueur = 2;
		} else {
			tourJoueur = 1;
		}
	}
	
	public String getImagePion() {
		if (tourJoueur == 1) {
			return imgJoueur1;
		} else if (tourJoueur == 2) {
			return imgJoueur2;
		}
		return null;
	}
	
	public String getImagePion(int i,int j) {
		if (matricePlateau[i][j] == 1) {
			return imgJoueur1;
		} else if (matricePlateau[i][j] == 2) {
			return imgJoueur2;
		}
		return null;
	}
	
	public static void SET_NB_JOUEUR(int nbJoueur) {
		NB_JOUEUR = nbJoueur;
	}
	
	public static int GET_NB_JOUEUR() {
		return NB_JOUEUR;
	}
	
	public String messageTourJoueur() {
		return "Joueur ["+ tourJoueur + "] à toi de jouer !";
	}

	public boolean siTourBot() {
		if (siJeuxEnCours && NB_JOUEUR == 1 && tourJoueur == 2 ) {
			return true;
		}
		return false;
	}

	public int[] tourBot() {
		// IA
		int[] position = Test.test(fichier, this);
		
		if (position != null && siCaseVide(position[0], position[1])) {
			return position;
		}
		
		// Si IA disfonctionne alors hazard
		int result[] = {0,0};
		
		int i = 0;
		int j = 0;
		
		Random random = new Random();
		boolean isGood = false;
		
		while (!isGood) {
			i = random.nextInt(3);
			j = random.nextInt(3);
			
			if (siCaseVide(i, j)) {
				isGood = true;
			}
		}
		
		result[0] = i;
		result[1] = j;
		return result;
	}
	
	public static void SET_IN_TRAINING(boolean inTraining) {
		IN_TRAINING = inTraining;
	}
	
	public static boolean IS_IN_TRAINING() {
		return IN_TRAINING;
	}
	
	public double[] getInput() {
		double input[] = {0,0,0,0,0,0,0,0,0};
		
		int secondIndex = 0;
		for (int h = 0; h < n; h++) {
			for (int v = 0; v < n; v++) {
				if (matricePlateau[h][v] != tourJoueur && matricePlateau[h][v] != 0) {
					input[secondIndex] = -1;
				} else if (matricePlateau[h][v] == tourJoueur) {
					input[secondIndex] = 1;
				}
				
				secondIndex ++;
			}
		}
		
		return input;
	}
	
	public String writeData(int i, int j) {
		if (IN_TRAINING) {
			double output[] = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
			
			int index = n * i + j;
			output[index] = 1;
			
			double[] input = getInput();
			
			
			String ligne = Outil.doubleArrayToString(input)+"\t"+ Outil.doubleArrayToString(output)+"\n";
			System.out.println(ligne);
			fichier.ecrireLigne(ligne);
			
			return "Les données :\n\t"+ligne+"\n sont sauvergardé";
						
		}
		return null;
	}

	public String supprimerTraining() {
		fichier.delete();
		return "Fichier " +fichier.getName()+ " à bien était supprimer";
		
	}
	
	public static void SET_MESSAGE_FIN(String string) {
		PlateauMorpion.messageFin = string;
	}
	
	public static String GET_MESSAGE_FIN() {
		return PlateauMorpion.messageFin;
	}

	public int[] help() {
		int[] coord = Test.test(fichier, this);
		return coord;
	}
	
}
