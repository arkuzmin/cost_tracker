package ru.arkuzmin.costtracker.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.bean.Category;
import ru.arkuzmin.costtracker.db.bean.Cost;
import ru.arkuzmin.costtracker.db.model.CostService;
import ru.arkuzmin.costtracker.db.model.ServiceFactory;

public class CostsController implements Initializable {

	@FXML
	TableView<Cost> costTable;
	@FXML
	TableColumn<Cost, String> costColName;
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
		initCostTable();
	}

	
	private void setColumnsSizes(double name, double amount, double agent, double category, double date) {
		costColName.prefWidthProperty().bind(costTable.widthProperty().multiply(name));
		costColCategory.prefWidthProperty().bind(costTable.widthProperty().multiply(category));
		costColAgent.prefWidthProperty().bind(costTable.widthProperty().multiply(agent));
		costColDate.prefWidthProperty().bind(costTable.widthProperty().multiply(date));
		costColAmount.prefWidthProperty().bind(costTable.widthProperty().multiply(amount));
	}
	
	private void setColumnsResizable(boolean value) {
		costColName.setResizable(value);
		costColCategory.setResizable(value);
		costColAgent.setResizable(value);
		costColDate.setResizable(value);
		costColAmount.setResizable(value);
	}
	
	private void initCostTable() {
		costColName.setCellValueFactory(new PropertyValueFactory<Cost, String>("name"));
		costColAmount.setCellValueFactory(new PropertyValueFactory<Cost, Double>("amount"));
		costColAgent.setCellValueFactory(new PropertyValueFactory<Cost, Agent>("agent"));
		costColCategory.setCellValueFactory(new PropertyValueFactory<Cost, Category>("category"));
		costColDate.setCellValueFactory(new PropertyValueFactory<Cost, Date>("date"));
		
		setColumnsSizes(0.3, 0.1, 0.20, 0.25, 0.15);
		setColumnsResizable(false);

		refreshCostTable();
	}
	
	public void refreshCostTable() {
		costTable.getItems().clear();
		
		CostService service = ServiceFactory.getCostService();
		List<Cost> list = service.getAllCosts();
		ObservableList<Cost> tableContent = FXCollections.observableList(list);
		
		costTable.setItems(tableContent);
	}
	
	/** Открывает окно для редактирования выбранной затраты */
	public void editCost() throws IOException {
		Cost selectedCost = costTable.getSelectionModel().getSelectedItem();
		
		if (selectedCost != null) {
			URL location = getClass().getResource("/ru/arkuzmin/costtracker/view/fxml/CostEdit.fxml");
			FXMLLoader loader = new FXMLLoader(location);
			
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.setResizable(false);
			stage.setScene(new Scene((AnchorPane) loader.load())); 
			
			
			CostEditController editController = loader.<CostEditController>getController();
			editController.initSelected(selectedCost, CostsController.this);
			
	        stage.show();
		}
	}
	
}
