package application;

import java.io.IOException;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FinJeuxController {
	
	@FXML
	private Text messageFin;
	
	@FXML
    public void initialize() {
        messageFin.setText(PlateauMorpion.GET_MESSAGE_FIN());
        scaleText(messageFin);
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
	
	public void closeApplication(ActionEvent event) {
		System.exit(0);
	}
	
	public void restartGame(ActionEvent event) {
		changeScene(event, "/application/Jeux.fxml");
	}
	
	public void scaleText(Text message) {
		ScaleTransition scaleMessage = new ScaleTransition(Duration.millis(300), message);
		scaleMessage.setByX(0.1);
		scaleMessage.setByY(0.1);
		scaleMessage.setCycleCount(10);
		scaleMessage.setAutoReverse(true);
		scaleMessage.play();
	}
}
