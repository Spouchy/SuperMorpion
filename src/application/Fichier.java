package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

public class Fichier extends File{
	
	public Fichier(String fichier) {
		super(fichier);
	}	
	
	public void ecrireLigne(String text) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this));
			
			bufferedWriter.write(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void afficher() {
		String[] liste = this.list();
		System.out.println(liste[0]);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
		   // process the line.
		}
		br.close();
	}
	
}
