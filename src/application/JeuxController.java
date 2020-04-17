package application;

import java.awt.Label;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class JeuxController {

	PlateauMorpion plateauMorpion = new PlateauMorpion(1);
	
	@FXML
	private Text tourMessage;
	
	public void clickOnCase(ActionEvent event) {
		//int position[] = getPosition(event);
		int i = getPosition(event)[0];
		int j = getPosition(event)[1];
		//String textTmpString = "C'est la case i : "+position[0]+ " et j :" + position[1];
		
		if (plateauMorpion.siCaseVide(i, j)) {
			String idEventString = ((Node) event.getSource()).getId();
			String idImageString = "img_"+idEventString;
			@FXML
			
			
			
			plateauMorpion.placerPion(i, j);
		}
		
		//tourMessage.setText(textTmpString);
		System.out.println("Salut !!! ");
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
