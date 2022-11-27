package application;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RequestServiceController implements Initializable{
	@FXML
	private Button backButton;
	@FXML
	private Button submitButton;
	@FXML
	private Label username;
	@FXML
	private Label requestServiceMessage;
	@FXML
	private ComboBox<String> serviceType;
	@FXML
	private TextArea descriptionBox;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		serviceType.setItems(FXCollections.observableArrayList("Room Cleaning", "Carpentry", "Electric", "Other"));
	}
	
	public void setUsername(String uname) {
		username.setText(uname);
	}
	
	public void backButtonOnAction(ActionEvent event) {
		try {
			String uname = username.getText();
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
	
	public void submitButtonOnAction() {
		DatabaseConnection connection = new DatabaseConnection();
		Connection connectDb = connection.getConnection();
		
		if(serviceType.getValue() != null && descriptionBox.getText() != null) {
			String query = "select * from wards where username =  " + "'" + username.getText() + "'" + "";
			
			Statement stmt;
			int id = 0;
			String name = null;
			try {
				stmt = connectDb.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				while (rs.next()) {
					id = rs.getInt(1);
					name = rs.getString(2) + " " + rs.getString(3);
//					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String insert = "insert into services(wardId, name, serviceType, description, dateTime, status) values";
			String values = "('" + id + "','" + name + "','" + serviceType.getValue() + "','" + descriptionBox.getText() + "'," + " now()," + " 'pending')";
			String insertQuery = insert + values;

			try {
				Statement statement = connectDb.createStatement();
				statement.executeUpdate(insertQuery);	
				
				requestServiceMessage.setText("Request sent successfully!");
				requestServiceMessage.setTextFill(Color.GREEN);
				requestServiceMessage.setLayoutX(434);
				
				serviceType.setValue("");
				serviceType.setPromptText("Select service");
				descriptionBox.setText("");
			}
			catch(Exception e) {
				e.printStackTrace();
				e.getCause();
			}
		}
		else {
			requestServiceMessage.setText("Input all fields!");
			requestServiceMessage.setTextFill(Color.RED);
			requestServiceMessage.setLayoutX(493);
		}
		
		
	}
}
