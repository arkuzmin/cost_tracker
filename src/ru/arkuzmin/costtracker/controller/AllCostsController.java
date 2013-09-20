package ru.arkuzmin.costtracker.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.bean.Category;
import ru.arkuzmin.costtracker.db.bean.Cost;
import ru.arkuzmin.costtracker.db.model.CostService;
import ru.arkuzmin.costtracker.db.model.impl.CostServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AllCostsController implements Initializable {

	@FXML
	TableView<Cost> costTable;
	@FXML
	TableColumn<Cost, String> costColName;
	@FXML
	TableColumn<Cost, String> costColDesc;
	@FXML
	TableColumn<Cost, Agent> costColAgent;
	@FXML
	TableColumn<Cost, Category> costColCategory;
	@FXML
	TableColumn<Cost, Double> costColAmount;
	@FXML
	TableColumn<Cost, Date> costColDate;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configureCostTable();
	}

	private void configureCostTable() {
		costColName.setCellValueFactory(new PropertyValueFactory<Cost, String>("name"));
		costColDesc.setCellValueFactory(new PropertyValueFactory<Cost, String>("desc"));
		costColAmount.setCellValueFactory(new PropertyValueFactory<Cost, Double>("amount"));
		costColAgent.setCellValueFactory(new PropertyValueFactory<Cost, Agent>("agent"));
		costColCategory.setCellValueFactory(new PropertyValueFactory<Cost, Category>("category"));
		costColDate.setCellValueFactory(new PropertyValueFactory<Cost, Date>("date"));
		
		costTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		CostService service = new CostServiceImpl();
		List<Cost> list = service.getAllCosts();
		ObservableList<Cost> tableContent = FXCollections.observableList(list);
		
		costTable.setItems(tableContent);
	}
	
}
