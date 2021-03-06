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
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.apache.log4j.Logger;

import ru.arkuzmin.costtracker.common.Globals;
import ru.arkuzmin.costtracker.controller.utils.ControllerUtils;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.bean.Category;
import ru.arkuzmin.costtracker.db.bean.Cost;
import ru.arkuzmin.costtracker.db.model.CostService;
import ru.arkuzmin.costtracker.db.model.ServiceFactory;
import ru.arkuzmin.costtracker.model.dto.CostFilter;

public class CostsController implements Initializable {

	private static final Logger logger = Logger.getLogger(CostsController.class);
	
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
	
	@FXML 
	Button setFilterBtn;
	
	@FXML
	Button editBtn;
	
	@FXML
	Button deleteBtn;
	
	@FXML
	Button findBtn;
	
	CostFilter filter;
	
	public void saveFilter(CostFilter filter) {
		this.filter = filter;
		refreshCostTable();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.filter = new CostFilter();
		initCostTable();
		editBtn.setDisable(true);
		deleteBtn.setDisable(true);
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
		costTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		costColName.setCellValueFactory(new PropertyValueFactory<Cost, String>("name"));
		costColAmount.setCellValueFactory(new PropertyValueFactory<Cost, Double>("amount"));
		costColAgent.setCellValueFactory(new PropertyValueFactory<Cost, Agent>("agent"));
		costColCategory.setCellValueFactory(new PropertyValueFactory<Cost, Category>("category"));
		costColDate.setCellValueFactory(new PropertyValueFactory<Cost, Date>("date"));
		ControllerUtils.setDateCellFormat(costColDate);
		
		
		setColumnsSizes(0.3, 0.1, 0.20, 0.25, 0.15);
		setColumnsResizable(false);

		refreshCostTable();
	}
	
	/** 
	 * Обновляет содержимое таблицы расходов.
	 */
	public void refreshCostTable() {
		costTable.getItems().clear();
		
		CostService service = ServiceFactory.getCostService();
		List<Cost> list = service.getFilteredCosts(filter);
		ObservableList<Cost> tableContent = FXCollections.observableList(list);
		
		costTable.setItems(tableContent);
	}

	/**
	 * Открывает окно для редактирования выбранной затраты.
	 */
	public void editCost() {
		Cost selectedCost = costTable.getSelectionModel().getSelectedItem();
		
		try {
			if (selectedCost != null) {
				URL location = getClass().getResource(Globals.FXML_EDIT_COST);
				FXMLLoader loader = new FXMLLoader(location);
				
				Stage stage = new Stage(StageStyle.UTILITY);
				stage.setResizable(false);
				stage.setScene(new Scene((AnchorPane) loader.load())); 
				stage.centerOnScreen();
				
				CostEditController editController = loader.<CostEditController>getController();
				editController.initSelected(selectedCost, CostsController.this);
				
		        stage.show();
			}
		} catch (IOException e) {
			logger.error("Невозможно открыть окно редактирования затраты", e);
		}
	}
	
	
	/** 
	 * Удаляет выбранные записи.
	 */
	public void delete() {
		ObservableList<Cost> list = costTable.getSelectionModel().getSelectedItems();
		CostService service = ServiceFactory.getCostService();
		for (Cost cost : list) {
			service.deleteCost(cost);
		}
		refreshCostTable();
	}
	
	/** 
	 * Открывает окно для редактирования выбранной записи.
	 */
	public void edit() {
		editCost();
	}
	
	/** 
	 * Открывает новое окно для задания фильтра поиска 
	 */
	public void setFilter() {
		try {
			URL location = getClass().getResource(Globals.FXML_COST_FILTER);
			FXMLLoader loader = new FXMLLoader(location);
	
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.setResizable(false);
			stage.setTitle(Globals.COSTS_FILTER_WIND_NAME);
			stage.setScene(new Scene((AnchorPane) loader.load())); 
			stage.centerOnScreen();
			
			CostFilterController controller = loader.<CostFilterController>getController();
			controller.initData(CostsController.this, filter);
	        stage.show();
			
		} catch (IOException e) {
			logger.error("Возникла ошибка при попытке открытия окна с фильтром", e);
		}
	}
	
	/** 
	 * Обновляет содержимое таблицы в соответствии с заданным фильтром поиска.
	 */
	public void find() {
		refreshCostTable();
	}
	
	/** 
	 * Корректировка доступности кнопок.
	 */
	public void correctBtns() {
		ObservableList<Cost> list = costTable.getSelectionModel().getSelectedItems();
		if (list.size() > 1) {
			deleteBtn.setDisable(false);
			editBtn.setDisable(true);
		} else if (list.size() == 1) {
			deleteBtn.setDisable(false);
			editBtn.setDisable(false);
		} else {
			deleteBtn.setDisable(true);
			editBtn.setDisable(true);
		}
	}
}
