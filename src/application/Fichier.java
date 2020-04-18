package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this,true));
			
			bufferedWriter.write(text);
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
