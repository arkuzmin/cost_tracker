package ru.arkuzmin.costtracker.controller;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ru.arkuzmin.costtracker.controller.utils.ControllerUtils;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.bean.Category;
import ru.arkuzmin.costtracker.db.bean.Cost;
import ru.arkuzmin.costtracker.db.model.CostService;
import ru.arkuzmin.costtracker.db.model.ServiceFactory;
import eu.schudt.javafx.controls.calendar.DatePicker;

public class NewCostController implements Initializable {

	@FXML
	TextArea costName;
	@FXML
	TextArea costDesc;
	@FXML
	ComboBox<Agent> costAgent;
	@FXML
	ComboBox<Category> costCategory;
	@FXML 
	TextField costAmount;
	@FXML
	GridPane costDateGrid;
	DatePicker costDate;
	
	@FXML
	Button closeBtn;
	@FXML 
	Button saveBtn;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ControllerUtils.initAgents(costAgent);
		ControllerUtils.initCategories(costCategory);
		costDate = new DatePicker(Locale.getDefault());
		ControllerUtils.initDatePicker(costDate, costDateGrid);
	}
	
	private void clearAll() {
		costName.clear();
		costDesc.clear();
		costAmount.clear();
	}
	
	public void save() {
		CostService service = ServiceFactory.getCostService();
		
		Cost cost = new Cost();
		cost.setName(costName.getText());
		cost.setDesc(costDesc.getText());
		cost.setAmount(costAmount.getText());
		cost.setDate(costDate.getSelectedDate());
		
		int agentId = costAgent.getValue().getId();
		int catId = costCategory.getValue().getId();
		
		service.addCost(cost, agentId, catId);

		clearAll();
	}
	
	public void close() {
		closeStage();
	}
	
	private void closeStage() {
		Stage currentStage = (Stage) closeBtn.getScene().getWindow();
		currentStage.close();	
	}
	

}
