module ARMYBOYSHOSTEL {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
