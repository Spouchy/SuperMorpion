package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import java.io.IOException;


public class MenuController {
	
	@FXML
	private ToggleButton nbJoueur1;
	@FXML
	private ToggleButton nbJoueur2;
	@FXML
	private CheckBox training;
	
	@FXML
    public void initialize() {
		if (PlateauMorpion.IS_IN_TRAINING() == true) {
			training.setSelected(true);
			disbledJoueurAndCocheTraining();
		}
		if (PlateauMorpion.GET_NB_JOUEUR() == 2) {
			nbJoueur2.setSelected(true);
		}
    }
	
	public void closeApplicationButton(ActionEvent event) {
		System.exit(0);
	}
	
	
	public void launchGame(ActionEvent event) throws IOException {
	    Parent jeux_page = FXMLLoader.load(getClass().getResource("/application/Jeux.fxml"));
        Scene scene_jeux = new Scene(jeux_page);
        Stage jeux_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        jeux_Stage.setScene(scene_jeux);
        jeux_Stage.show();
	}
	
	
	public void choixNbJoueur(ActionEvent event) {
		String idEventString = ((Node) event.getSource()).getId();
		
		if (idEventString.equals("nbJoueur2")) {
			PlateauMorpion.SET_NB_JOUEUR(2);
		} else {
			PlateauMorpion.SET_NB_JOUEUR(1);
		}
	}
	
	/*public void choixDifficulte(ActionEvent event) {
		String idEventString = ((Node) event.getSource()).getId();
		PlateauMorpion plateauMorpion = new PlateauMorpion();
		switch (idEventString) {
		case "1":
			PlateauMorpion.SET_DIFFICULTE(1);
			break;
			
		case "2":
			PlateauMorpion.SET_DIFFICULTE(2);
			break;
			
		case "3":
			PlateauMorpion.SET_DIFFICULTE(3);
			break;
			
		case "4":
			PlateauMorpion.SET_DIFFICULTE(4);
			break;

		default:
			PlateauMorpion.SET_DIFFICULTE(1)
			break;
		}
	}*/
	
	
	public void bloqueUnJoueur(ActionEvent event) {
		CheckBox checkBoxTraining = ((CheckBox) event.getSource());
		if (checkBoxTraining.isSelected()) {
			disbledJoueurAndCocheTraining();
		}
		else {
			nbJoueur1.setDisable(false);
			PlateauMorpion.SET_IN_TRAINING(false);
		}
	}
	
	public void disbledJoueurAndCocheTraining() {
		nbJoueur1.setDisable(true);
		nbJoueur2.setSelected(true);
		PlateauMorpion.SET_NB_JOUEUR(2);
		PlateauMorpion.SET_IN_TRAINING(true);
	}
	
}
