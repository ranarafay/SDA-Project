package application;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class RegisterWardsController {
	@FXML
	private TextField firstnameTextField;
	@FXML
	private TextField lastnameTextField;
	@FXML
	private TextField phoneNumberTextField;
	@FXML
	private TextField cityTextField;
	@FXML
	private TextField addressTextField;
	@FXML
	private TextField usernameTextField;
	@FXML
	private TextField passwordTextField;
	@FXML
	private TextField confrimPasswordTextField;
	
	@FXML 
	private Label passwordMatchMessageLabel;
	@FXML 
	private Label registrationMessage;
	@FXML 
	private Label usernameMessage;
	
	@FXML
	private Button registerButton;
	@FXML
	private Button backButton;
	
	
	public void registerButtonOnAction() {
		if(firstnameTextField.getText().isBlank() == false && lastnameTextField.getText().isBlank() == false && phoneNumberTextField.getText().isBlank() == false && cityTextField.getText().isBlank() == false && addressTextField.getText().isBlank() == false && usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false && confrimPasswordTextField.getText().isBlank() == false) {
			if(passwordTextField.getText().equals(confrimPasswordTextField.getText())) {
//				registering ward
				registerWard();
			}
			else {
				passwordMatchMessageLabel.setText("Password doesn't match!");
				passwordMatchMessageLabel.setTextFill(Color.RED);
				passwordMatchMessageLabel.setLayoutX(421);;
			}
		}
		else {
			registrationMessage.setText("Input all fields!");
			registrationMessage.setLayoutX(480);
			registrationMessage.setTextFill(Color.RED);
		}
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
	
	public void registerWard() {
		DatabaseConnection connection = new DatabaseConnection();
		Connection connectDb = connection.getConnection();
		
		String verifyLogin = "select count(1) from wards where username = '" + usernameTextField.getText() + "'";
		
		boolean userExist = false;
		
		try {
			Statement statement = connectDb.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLogin);
			
			while (queryResult.next()) {
				if(queryResult.getInt(1) == 1) {
					userExist = true;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			e.getCause();
		}
		
		
		if(userExist == false) {
			String fname = firstnameTextField.getText();
			String lname = lastnameTextField.getText();
			String phno = phoneNumberTextField.getText();
			String city = cityTextField.getText();
			String address = addressTextField.getText();
			String uname = usernameTextField.getText();
			String password = passwordTextField.getText();
			
			String insert = "insert into wards(firstName, lastName, phoneNumber, city, address, username, password) values ('";
			String values = fname + "','" + lname + "','" + phno + "','" + city + "','" + address + "','" + uname + "','" + password + "')";
			
			String insertQuery = insert + values;
			
			try {
				Statement statement = connectDb.createStatement();
				statement.executeUpdate(insertQuery);
				
				registrationMessage.setLayoutX(406);
				registrationMessage.setText("Ward Regitered successfully!");
				registrationMessage.setTextFill(Color.GREEN);
				
				firstnameTextField.setText("");
				lastnameTextField.setText("");
				phoneNumberTextField.setText("");
				cityTextField.setText("");
				addressTextField.setText("");
				passwordTextField.setText("");
				confrimPasswordTextField.setText("");
				usernameTextField.setText("");
				
				usernameMessage.setText("");	
				passwordMatchMessageLabel.setText("");
			}
			catch(Exception e) {
				e.printStackTrace();
				e.getCause();
			}
		}
		else if(userExist == true) {
			usernameMessage.setLayoutX(469);
			usernameMessage.setText("Username exists!");
			usernameMessage.setTextFill(Color.RED);
		}
	}
	
	
}
