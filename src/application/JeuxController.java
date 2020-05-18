package application;

import java.awt.Event;
import java.awt.Label;
import java.io.IOException;
import java.time.Year;

import application.ia.Test;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
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
        jeuxAutomatique(null);
        
        if (!PlateauMorpion.IS_IN_TRAINING()) {
        	windowTraining.setVisible(false);
        }
    }
	
	public void backMenu(ActionEvent event) {
	    changeScene(event, "/application/Menu.fxml");
	}
	
	public void changeScene(ActionEvent event, String path) {
		Parent page;
		try {
			page = FXMLLoader.load(getClass().getResource(path));
			Scene scene = new Scene(page);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public void goMessageFin(ActionEvent event) {
		changeScene(event, "/application/FinJeux.fxml");
	}
	
	public void closeApplication(ActionEvent event) {
		System.exit(0);
	}
	
	public void clickOnCase(ActionEvent event) {
		if (plateauMorpion.siJeuxEnCours) {
			int i = getPosition(event)[0];
			int j = getPosition(event)[1];
			
			if (plateauMorpion.siCaseVide(i, j)) {
				placerPionLogique(event, i, j);			
			}
			
			jeuxAutomatique(event); 
		}
	}
	
	public void jeuxAutomatique(ActionEvent event) {
		if (plateauMorpion.siTourBot()) {
			int position[] = plateauMorpion.tourBot();
			
			int i = position[0];
			int j = position[1];
			
			placerPionLogique(event, i, j);
			
		}
	}
	
	private void enregistrementDonneeIA(int i, int j) {
		if (PlateauMorpion.IS_IN_TRAINING()) {
			messageTraining.setText(plateauMorpion.writeData(i, j));
			
		}
	}
	
	private void placerPionLogique(ActionEvent event, int i, int j) {
		enregistrementDonneeIA(i, j);
		plateauMorpion.placerPion(i, j);
		refraichirAffichage(i, j);
		
		if (plateauMorpion.siVictoire(i, j)) {
			PlateauMorpion.SET_MESSAGE_FIN(plateauMorpion.messageVictoire());
			goMessageFin(event);
		} else if (plateauMorpion.siFini()) {
			PlateauMorpion.SET_MESSAGE_FIN(plateauMorpion.messagePartieFini());
			goMessageFin(event);
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
	
	public void help(ActionEvent event) {
		int[] coor = plateauMorpion.help();
		int i = coor[0];
		int j = coor[1];
		
		String imageJoueurString = plateauMorpion.getImagePion();
		
		if (i == 0 ) {
			if (j == 0) {
				if (plateauMorpion.siCaseVide(i, j)) {
					fadePionHelp(img_zeroZero, imageJoueurString);
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
		fadePion.setDuration(Duration.millis(1000));
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
	
	public void fadePionHelp(ImageView pion, String imageString) {
		Image image = new Image(imageString);
		pion.setImage(image);
		pion.setVisible(false);
		FadeTransition fadeInPion = new FadeTransition();
		fadeInPion.setDuration(Duration.millis(1000));
		fadeInPion.setNode(pion);
		fadeInPion.setFromValue(0);
		fadeInPion.setToValue(0.7);
		FadeTransition fadePion = new FadeTransition();
		fadePion.setDuration(Duration.millis(3000));
		fadePion.setNode(pion);
		fadePion.setFromValue(0.7);
		fadePion.setToValue(0);
		fadePion.play();	
	}
	
	
	
	public void translatePion(ImageView pion) {
		TranslateTransition translatePion = new TranslateTransition();
		translatePion.setDuration(Duration.millis(50));
		translatePion.setNode(pion);
		translatePion.setToX(2);
		translatePion.setToY(2);
		translatePion.setAutoReverse(true);
		translatePion.setCycleCount(20);
		translatePion.play();	
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
