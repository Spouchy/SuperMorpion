package application;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController {
	
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
}
