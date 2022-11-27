package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Service {
	private String Name;
	private String ServiceType;
	private String Description;
	private String DateTime;
	private String  Status;
	
	public Service(String name, String serviceType, String description, String dateTime, String status) {
		this.Name = name;
		this.ServiceType = serviceType;
		this.Description = description;
		this.DateTime = dateTime;
		this.Status = status;
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getServiceType() {
		return ServiceType;
	}
	public void setServiceType(String serviceType) {
		this.ServiceType = serviceType;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		this.Description = description;
	}
	public String getDateTime() {
		return DateTime;
	}
	public void setDateTime(String dateTime) {
		this.DateTime = dateTime;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		this.Status = status;
	}
	
	public static ObservableList<Service> getServices(){
		
		DatabaseConnection connection = new DatabaseConnection();
		Connection connectDb = connection.getConnection();
		
		String query = "select * from services";
		
		int id;
		String name;
		String serviceType;
		String description;
		String dateTime;
		String status;
		
		ObservableList<Service> serviceList = FXCollections.observableArrayList();
		
		Statement stmt;
		try {
			stmt = connectDb.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				id = rs.getInt(1);
				name = rs.getString(2);
				serviceType = rs.getString(3);
				description = rs.getString(4);
				dateTime = rs.getString(5);
				status = rs.getString(6);
				
				Service newService = new Service(name, serviceType, description, dateTime, status);
				serviceList.add(newService);
				
				System.out.println(newService.getName() + " " + newService.getServiceType() + " " + newService.getDescription() + " " + newService.getDateTime() + " " + newService.getStatus());
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return serviceList;
	}



}
