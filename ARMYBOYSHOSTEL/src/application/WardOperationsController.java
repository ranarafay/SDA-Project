package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WardOperationsController {
	@FXML
	private Button backButton;
	@FXML
	private Button requestServiceButton;
	@FXML
	private Label username;
	
	public void setUsername(String uname) {
		username.setText(uname);
	}
	
	public void backButtonOnAction() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("loginWards.fxml"));
			Stage window = (Stage) backButton.getScene().getWindow();
			window.setScene(new Scene(root));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void requestServiceButtonOnAction(ActionEvent event) {
		try {
			String uname = username.getText();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RequestService.fxml"));
			Parent root = loader.load();
			
			RequestServiceController controller = loader.getController();
			controller.setUsername(uname);
			
			Stage stage;
			Scene scene = new Scene(root);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void requestLeaveButtonOnAction(ActionEvent event) {
		try {
			String uname = username.getText();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RequestLeave.fxml"));
			Parent root = loader.load();
			
			RequestLeaveController controller = loader.getController();
			controller.setUsername(uname);
			
			Stage stage;
			Scene scene = new Scene(root);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
