package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
 
    @Override
    public void start(Stage primaryStage) throws Exception {
    	AnchorPane root = FXMLLoader.load(getClass().getResource("Cryptor.fxml"));
    	primaryStage.setTitle("RSA-155");
    	primaryStage.setScene(new Scene(root));
    	primaryStage.show();
    }

	
	public static void main(String[] args) {
		launch(args);
	}
}
