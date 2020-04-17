package application;

import java.util.ArrayList;

public class PlateauMorpion {
	private static int NB_JOUEUR = 1;
	private static int DIFFICULTE = 0;
	private int tourJoueur = 1;
	private int[][] matricePlateau =
	    {
	        { 0,0,0 } ,
	        { 0,0,0 } ,
	        { 0,0,0 }
	    };

	public PlateauMorpion() {
		
		/*for (int i=0; i<matricePlateau.length; i++) {
			for (int j=0; j<matricePlateau[i].length; j++) {
				matricePlateau[i][j] = 0;
			}
		}*/
	}
	
	public boolean siVictoire(int i,int j) {
		// TODO
		
		return false;
	}
	
	public boolean siFini() {
		// TODO
		
		return false;
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
	
	public static void SET_NB_JOUEUR(int nbJoueur) {
		NB_JOUEUR = nbJoueur;
	}
}
