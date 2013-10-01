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

public class CostEditController implements Initializable {

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
	Button cancelBtn;
	@FXML
	Button saveBtn;
	
	private Cost selectedCost;
	
	private CostsController parent;
	
	public void initSelected(Cost cost, CostsController parent) {
		selectedCost = cost;
		this.parent = parent;
		initFields();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		costDate = new DatePicker(Locale.getDefault());
		ControllerUtils.initDatePicker(costDate, costDateGrid);
		ControllerUtils.initAgents(costAgent);
		ControllerUtils.initCategories(costCategory);
	}
	
	private void initFields() {
		if (selectedCost != null) {
			costName.setText(selectedCost.getName());
			costDesc.setText(selectedCost.getDesc());
			costAgent.setValue(selectedCost.getAgent());
			
			for (int i = 0; i < costAgent.getItems().size(); i++) {
				if (costAgent.getItems().get(i).getId() == selectedCost.getAgent().getId()) {
					costAgent.getSelectionModel().select(i);
					break;
				}
			}
			
			for (int i = 0; i < costCategory.getItems().size(); i++) {
				if (costCategory.getItems().get(i).getId() == selectedCost.getCategory().getId()) {
					costCategory.getSelectionModel().select(i);
					break;
				}
			}
			
			costCategory.getSelectionModel();
			costAmount.setText(String.valueOf(selectedCost.getAmount()));
			costDate.setSelectedDate(selectedCost.getDate());
		}
	}
	
	public void save() {
		CostService service = ServiceFactory.getCostService();
		
		Cost cost = new Cost();
		cost.setId(selectedCost.getId());
		cost.setName(costName.getText());
		cost.setDesc(costDesc.getText());
		cost.setAmount(costAmount.getText());
		cost.setDate(costDate.getSelectedDate());
		
		int agentId = costAgent.getValue().getId();
		int catId = costCategory.getValue().getId();
		
		service.updateCost(cost, agentId, catId);
		
		parent.refreshCostTable();
		closeStage();
	}
	
	public void cancel() {
		closeStage();
	}
	
	private void closeStage() {
		Stage currentStage = (Stage) cancelBtn.getScene().getWindow();
		currentStage.close();	
	}

}
