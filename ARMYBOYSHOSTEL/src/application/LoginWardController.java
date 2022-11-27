package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginWardController {
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
	
	public void loginButtonOnAction(ActionEvent event) {
		
		if(usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false) {	
			
			DatabaseConnection connection = new DatabaseConnection();
			Connection connectDb = connection.getConnection();
			
			String verifyLogin = "select count(1) from wards where username = '" + usernameTextField.getText() + "' AND password =  '" + passwordTextField.getText() + "'";
			
			try {
				Statement statement = connectDb.createStatement();
				ResultSet queryResult = statement.executeQuery(verifyLogin);
				
				while (queryResult.next()) {
					
					if(queryResult.getInt(1) == 1) {
						System.out.println("Logged In !");
						
						try {
							String uname = usernameTextField.getText();
							FXMLLoader loader = new FXMLLoader(getClass().getResource("WardOperations.fxml"));
							Parent root = loader.load();
							
							WardOperationsController controller = loader.getController();
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
					else {
						System.out.println("Incorrect!");
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
		else {
			loginMessage.setLayoutX(348);
			loginMessage.setTextFill(Color.RED);
			loginMessage.setText("Enter all fields!");
		}
	}
}
