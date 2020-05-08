package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Outil {

	public static String doubleArrayToString(double[] array)
    {
        String data = "";
        
        for(int i=0; i < array.length;i++) {
        	if (i != array.length - 1) {
        		data += array[i]+",";
        	} else {
        		data += array[i];
			}
            
        }
        
        return data;
    }
	
	public static double[] stringToDoubleArray(String string, String sep)
    {
        String[] splitString = string.split(sep);
        
        int arraySize = splitString.length;
        double[] result = new double[arraySize];
        
        
        for(int i=0; i < arraySize;i++) {
        	result[i] = Double.valueOf(splitString[i]);
        }
        
        return result;
    }
	
}
