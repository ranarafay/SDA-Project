package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdministratorOperationsController {
	@FXML
	private Button registerWardsButton;
	@FXML
	private Button closeButton;
	@FXML
	private Button backButton;
	
	public void closeButtonOnAction() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
	
	public void backButtonOnAction() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
			Stage window = (Stage) backButton.getScene().getWindow();
			window.setScene(new Scene(root));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void registerWardsButtonOnAction() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("registerWards.fxml"));
			Stage window = (Stage) registerWardsButton.getScene().getWindow();
			window.setScene(new Scene(root));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
