package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LoginController {
	
	@FXML
	private Label labelLogin;
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordTextField;
	@FXML
	private Button LoginBtn;
	@FXML
	private Button backButton;
	@FXML
	private Label loginMessage;
	
	
	public void backButtonOnAction() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("PrimaryStage.fxml"));
			Stage window = (Stage) backButton.getScene().getWindow();
			window.setScene(new Scene(root));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loginButtonOnAction() {
		
		if(usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false) {	
			validateLogin();
		}
		else {
			loginMessage.setLayoutX(348);
			loginMessage.setTextFill(Color.RED);
			loginMessage.setText("Enter all fields!");
		}
	}
	
	public void validateLogin() {
		DatabaseConnection connection = new DatabaseConnection();
		Connection connectDb = connection.getConnection();
		
		String verifyLogin = "select count(1) from useraccount where username = '" + usernameTextField.getText() + "' AND password =  '" + passwordTextField.getText() + "'";
		
		try {
			Statement statement = connectDb.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLogin);
			
			while (queryResult.next()) {
				
				if(queryResult.getInt(1) == 1) {
//					labelLogin.setText("Logged In babe!");
					
					AdministratorOperationsForm();
				}
				else {
					loginMessage.setLayoutX(300);
					loginMessage.setTextFill(Color.RED);
					loginMessage.setText("Incorrect username or password!");
					
					usernameTextField.setText("");
					passwordTextField.setText("");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			e.getCause();
		}
	}
	
	public void AdministratorOperationsForm() {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("AdministratorOperations.fxml"));
				Stage window = (Stage) LoginBtn.getScene().getWindow();
				window.setScene(new Scene(root));
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
}
