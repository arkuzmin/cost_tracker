package ru.arkuzmin.costtracker.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CostTrackerMainController implements Initializable {

	@FXML
	Label addCostLbl;
	@FXML
	Label editCostLbl;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	public void addCost() throws IOException {
		URL location = getClass().getResource("/ru/arkuzmin/costtracker/view/fxml/NewCost.fxml");
		FXMLLoader loader = new FXMLLoader(location);
			
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.setResizable(false);
		stage.setScene(new Scene((AnchorPane) loader.load())); 
	
	    stage.show();
	}
	
	public void editCost() throws IOException {
		URL location = getClass().getResource("/ru/arkuzmin/costtracker/view/fxml/Costs.fxml");
		FXMLLoader loader = new FXMLLoader(location);
			
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.setResizable(false);
		stage.setScene(new Scene((AnchorPane) loader.load())); 
	
	    stage.show();
	}
	
	

}
