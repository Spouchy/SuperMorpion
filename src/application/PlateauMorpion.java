package application;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class PlateauMorpion {
	private static int NB_JOUEUR = 1;
	private static int DIFFICULTE = 0;
	private String imgJoueur1 = "file:src/application/images/png/deleteRouge.png";
	private String imgJoueur2 = "file:src/application/images/png/delete.png";
	private int tourJoueur = 1;
	private int[][] matricePlateau =
	    {
	        { 0,0,0 } ,
	        { 0,0,0 } ,
	        { 0,0,0 }
	    };
	
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
	
}
