package application;

import java.awt.Label;
import java.time.Year;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Text;

public class JeuxController {

	PlateauMorpion plateauMorpion = new PlateauMorpion();
	
	@FXML
	private Text tourMessage;
	@FXML
	private ImageView tourImage;
	
	@FXML
	private ImageView img_zeroZero;
	@FXML
	private ImageView img_zeroUn;
	@FXML
	private ImageView img_zeroDeux;
	@FXML
	private ImageView img_unZero;
	@FXML
	private ImageView img_unUn;
	@FXML
	private ImageView img_unDeux;
	@FXML
	private ImageView img_deuxZero;
	@FXML
	private ImageView img_deuxUn;
	@FXML
	private ImageView img_deuxDeux;
	
	
	
	public void clickOnCase(ActionEvent event) {
		int i = getPosition(event)[0];
		int j = getPosition(event)[1];
		
		if (plateauMorpion.siCaseVide(i, j)) {
			plateauMorpion.placerPion(i, j);
			refraichirAffichage(i, j);
			if (plateauMorpion.siVictoire(i, j)) {
				// TODO
			} else if (plateauMorpion.siFini()) {
				// TODO
			}
			plateauMorpion.joueurSuivant();
			Image image = new Image(plateauMorpion.getImagePion());
			tourImage.setImage(image);
		}
	}
	
	public void refraichirAffichage(int i,int j) {
		
		if (i == 0 ) {
			if (j == 0) {
				if (plateauMorpion.getImagePion(i, j) != null) {
					Image image = new Image(plateauMorpion.getImagePion(i, j));
					img_zeroZero.setImage(image);
				}
			} else if (j == 1) {
				if (plateauMorpion.getImagePion(i, j) != null) {
					Image image = new Image(plateauMorpion.getImagePion(i, j));
					img_zeroUn.setImage(image);
				}
			} else if (j == 2) {
				if (plateauMorpion.getImagePion(i, j) != null) {
					Image image = new Image(plateauMorpion.getImagePion(i, j));
					img_zeroDeux.setImage(image);
				}
			}
		} else if (i == 1 ) {
			if (j == 0) {
				if (plateauMorpion.getImagePion(i, j) != null) {
					Image image = new Image(plateauMorpion.getImagePion(i, j));
					img_unZero.setImage(image);
				}
			} else if (j == 1) {
				if (plateauMorpion.getImagePion(i, j) != null) {
					Image image = new Image(plateauMorpion.getImagePion(i, j));
					img_unUn.setImage(image);
				}
			} else if (j == 2) {
				if (plateauMorpion.getImagePion(i, j) != null) {
					Image image = new Image(plateauMorpion.getImagePion(i, j));
					img_unDeux.setImage(image);
				}
			}
		} else if (i == 2 ) {
			if (j == 0) {
				if (plateauMorpion.getImagePion(i, j) != null) {
					Image image = new Image(plateauMorpion.getImagePion(i, j));
					img_deuxZero.setImage(image);
				}
			} else if (j == 1) {
				if (plateauMorpion.getImagePion(i, j) != null) {
					Image image = new Image(plateauMorpion.getImagePion(i, j));
					img_deuxUn.setImage(image);
				}
			} else if (j == 2) {
				if (plateauMorpion.getImagePion(i, j) != null) {
					Image image = new Image(plateauMorpion.getImagePion(i, j));
					img_deuxDeux.setImage(image);
				}
			}
		}
	}
	
	public int[] getPosition(ActionEvent event) {
		String idEventString = ((Node) event.getSource()).getId();
		int result[] = {0,0};
		
		if (idEventString.equals("zeroZero")) {
			result[0] = 0;
			result[1] = 0;
			return result;
		} else if (idEventString.equals("zeroUn")) {
			result[0] = 0;
			result[1] = 1;
			return result;
		} else if (idEventString.equals("zeroDeux")) {
			result[0] = 0;
			result[1] = 2;
			return result;
		} else if (idEventString.equals("unZero")) {
			result[0] = 1;
			result[1] = 0;
			return result;
		} else if (idEventString.equals("unUn")) {
			result[0] = 1;
			result[1] = 1;
			return result;
		} else if (idEventString.equals("unDeux")) {
			result[0] = 1;
			result[1] = 2;
			return result;
		} else if (idEventString.equals("deuxZero")) {
			result[0] = 2;
			result[1] = 0;
			return result;
		} else if (idEventString.equals("deuxUn")) {
			result[0] = 2;
			result[1] = 1;
			return result;
		} else if (idEventString.equals("deuxDeux")) {
			result[0] = 2;
			result[1] = 2;
			return result;
		}
		return null;
		
	}
}
