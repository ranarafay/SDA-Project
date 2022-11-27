package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PrimaryStageController {
	@FXML
	private Button administratorLoginButton;
	@FXML
	private Button wardLoginButton;
	@FXML
	private Button closeButton;
	
	public void closeButtonOnAction() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
	
	public void adminiadministratorLoginButtonOnAction() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
			Stage window = (Stage) administratorLoginButton.getScene().getWindow();
			window.setScene(new Scene(root));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void wardLoginButtonOnAction() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("loginWards.fxml"));
			Stage window = (Stage) wardLoginButton.getScene().getWindow();
			window.setScene(new Scene(root));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
