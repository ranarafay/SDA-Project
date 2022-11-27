package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServicesController implements Initializable{

    @FXML
    private Button approveButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Service> servicesTable;

    @FXML
    private TableColumn<Service, String> dateTimeColumn;

    @FXML
    private TableColumn<Service, String> descriptionColumn;

    @FXML
    private TableColumn<Service, String> nameColumn;

    @FXML
    private TableColumn<Service, String> serviceTypeColumn;

    @FXML
    private TableColumn<Service, String> statusColumn;

    ObservableList<Service> serviceList = FXCollections.observableArrayList(
    		new Service("rafay", "type", "give it", "date and time", "pending"),
    		new Service("rafay2", "type2", "give it2", "date2 and2 time2", "pending2")
    );
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
//    	serviceList = Service.getServices();
    	
    	nameColumn.setCellValueFactory(new PropertyValueFactory<Service, String>("Name"));
    	serviceTypeColumn.setCellValueFactory(new PropertyValueFactory<Service, String>("ServiceType"));
    	dateTimeColumn.setCellValueFactory(new PropertyValueFactory<Service, String>("DateTime"));
    	descriptionColumn.setCellValueFactory(new PropertyValueFactory<Service, String>("Description"));
    	statusColumn.setCellValueFactory(new PropertyValueFactory<Service, String>("Status"));
    	
    	servicesTable.setItems(serviceList);	
    }
    
    
    @FXML
    void backButtonOnAction(ActionEvent event) {

    }




}
