package application;

import java.awt.Event;
import java.awt.Label;
import java.io.IOException;
import java.time.Year;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JeuxController {

	PlateauMorpion plateauMorpion = new PlateauMorpion();
	
	@FXML
	private Text tourMessage;
	@FXML
	private ImageView tourImage;
	@FXML
	private Pane windowTraining;
	@FXML
	private Text messageTraining;
	
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
	
	@FXML
    public void initialize() {
        setupTour();
        jeuxAutomatique();
        
        if (!PlateauMorpion.IS_IN_TRAINING()) {
        	windowTraining.setVisible(false);
        }
    }
	
	public void backMenu(ActionEvent event) throws IOException {
	    Parent menu_page = FXMLLoader.load(getClass().getResource("/application/Menu.fxml"));
        Scene scene_menu = new Scene(menu_page);
        Stage menu_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        menu_Stage.setScene(scene_menu);
        menu_Stage.show();
	}
	
	public void closeApplication(ActionEvent event) {
		System.exit(0);
	}
	
	public void clickOnCase(ActionEvent event) {
		if (plateauMorpion.siJeuxEnCours) {
			int i = getPosition(event)[0];
			int j = getPosition(event)[1];
			
			if (plateauMorpion.siCaseVide(i, j)) {
				placerPionLogique(i, j);			
			}
			
			jeuxAutomatique(); 
		}
	}
	
	public void jeuxAutomatique() {
		if (plateauMorpion.siTourBot()) {
			int position[] = plateauMorpion.tourBot();
			
			int i = position[0];
			int j = position[1];
			
			placerPionLogique(i, j);
			
		}
	}
	
	private void enregistrementDonneeIA(int i, int j) {
		if (PlateauMorpion.IS_IN_TRAINING()) {
			messageTraining.setText(plateauMorpion.writeData(i, j));
			
		}
	}
	
	private void placerPionLogique(int i, int j) {
		enregistrementDonneeIA(i, j);
		plateauMorpion.placerPion(i, j);
		refraichirAffichage(i, j);
		
		if (plateauMorpion.siVictoire(i, j)) {
			tourMessage.setText(plateauMorpion.messageVictoire());
		} else if (plateauMorpion.siFini()) {
			tourMessage.setText(plateauMorpion.messagePartieFini());
		} else {
			plateauMorpion.joueurSuivant();
			
			// Setup prochain tour
			setupTour();
		}
	}
	
	public void deleteTrainingData(ActionEvent event) {
		messageTraining.setText(plateauMorpion.supprimerTraining());
	}
	
	public void restartGame(ActionEvent event) {
		plateauMorpion = new PlateauMorpion();
		fadeAllPion();
		//netoyerAffichage();
		
		initialize();
	}
	
	public void setupTour() {
		tourMessage.setText(plateauMorpion.messageTourJoueur());
		Image image = new Image(plateauMorpion.getImagePion());
		tourImage.setImage(image);
	}
	
	public void netoyerAffichage() {
		img_zeroZero.setImage(null);
		img_zeroUn.setImage(null);
		img_zeroDeux.setImage(null);
		img_unZero.setImage(null);
		img_unUn.setImage(null);
		img_unDeux.setImage(null);
		img_deuxZero.setImage(null);
		img_deuxUn.setImage(null);
		img_deuxDeux.setImage(null);
	}
	
	public void fadeAllPion() {
		fadePion(img_zeroZero, false);
		fadePion(img_zeroUn, false);
		fadePion(img_zeroDeux, false);
		fadePion(img_unZero, false);
		fadePion(img_unUn, false);
		fadePion(img_unDeux, false);
		fadePion(img_deuxZero, false);
		fadePion(img_deuxUn, false);
		fadePion(img_deuxDeux, true);
	}
	
	public void fadeInAllPion() {
		fadeIn(img_zeroZero);
		fadeIn(img_zeroUn);
		fadeIn(img_zeroDeux);
		fadeIn(img_unZero);
		fadeIn(img_unUn);
		fadeIn(img_unDeux);
		fadeIn(img_deuxZero);
		fadeIn(img_deuxUn);
		fadeIn(img_deuxDeux);
	}
	
		
	public void fadePion(ImageView pion,boolean isLast) {
		FadeTransition fadePion = new FadeTransition();
		fadePion.setDuration(Duration.millis(500));
		fadePion.setNode(pion);
		fadePion.setFromValue(1);
		fadePion.setToValue(0);
		if (isLast) {
			fadePion.setOnFinished((ActionEvent event) -> {
				netoyerAffichage();
				fadeInAllPion();
			});
		}	
		fadePion.play();	
	}
	

	
	public void fadeIn(ImageView pion) {
		FadeTransition fadePion = new FadeTransition();
		fadePion.setDuration(Duration.millis(10));
		fadePion.setNode(pion);
		fadePion.setFromValue(0);
		fadePion.setToValue(1);
		fadePion.play();
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
