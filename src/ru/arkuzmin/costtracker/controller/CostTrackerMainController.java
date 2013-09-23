package ru.arkuzmin.costtracker.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.bean.Category;
import ru.arkuzmin.costtracker.db.bean.Cost;
import ru.arkuzmin.costtracker.db.model.AgentService;
import ru.arkuzmin.costtracker.db.model.CategoryService;
import ru.arkuzmin.costtracker.db.model.CostService;
import ru.arkuzmin.costtracker.db.model.impl.AgentServiceImpl;
import ru.arkuzmin.costtracker.db.model.impl.CategoryServiceImpl;
import ru.arkuzmin.costtracker.db.model.impl.CostServiceImpl;
import eu.schudt.javafx.controls.calendar.DatePicker;

public class CostTrackerMainController implements Initializable {

	AgentService agentService;
	CostService costService;
	CategoryService catService;
	
	
	/** ADD NEW COST */
	@FXML
	TextField costName;
	@FXML
	TextArea costDesc;
	@FXML
	ChoiceBox<Category> costCat;
	@FXML 
	ChoiceBox<Agent> costAgent;	
	@FXML
	TextField costAmount;
	@FXML
	GridPane costDateGrid;
	DatePicker costDate;
	@FXML 
	Button addNewCost;
	
	/** AGENTS */
	@FXML
	TextField agentName;
	@FXML
	Button addNewAgent;
	@FXML 
	TableView<Agent> agentTable;
	@FXML
	TableColumn<Agent, String> agentNameCol;
	@FXML
	Button deleteAgent;
	
	/** CATS */
	@FXML 
	TextField catName;
	@FXML
	TextArea catDesc;
	@FXML
	Button addNewCategory;
	@FXML
	TableView<Category> catTable;
	@FXML
	TableColumn<Category, String> catNameCol;
	@FXML
	TableColumn<Category, String> catDescCol;
	@FXML
	Button deleteCat;
	
	
	private void initCatsTable() {
		CategoryService service = getCatService();
		List<Category> list = service.getAllCats();
		ObservableList<Category> content = FXCollections.observableList(list);
		catTable.setItems(content);
		catTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		catNameCol.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
		catDescCol.setCellValueFactory(new PropertyValueFactory<Category, String>("desc"));
	}
	
	public void deleteCats() {
		CategoryService service = getCatService();
		List<Category> list = catTable.getSelectionModel().getSelectedItems();
		for (Category cat : list) {
			service.deleteCategory(cat);
		}
		
		initCategories();
		initCatsTable();
	}
	
	public void addCategory() {
		CategoryService service = getCatService();
		Category cat = new Category();
		cat.setName(catName.getText());
		cat.setDesc(catName.getText());
		service.addCategory(cat);
		
		initCategories();
		initCatsTable();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initDatePicker();
		initAgents();
		initCategories();
		initAgentsTable();
		initCatsTable();
	}
	
	public void deleteAgents() {
		AgentService service = getAgentService();
		List<Agent> selected = agentTable.getSelectionModel().getSelectedItems();
		for (Agent agent : selected) {
			service.deleteAgent(agent);
		}
		initAgents();
		initAgentsTable();
	}
	
	public void addAgent() {
		AgentService service = getAgentService();
		Agent agent = new Agent();
		agent.setName(agentName.getText());
		service.addAgent(agent);
		initAgents();
		initAgentsTable();
	}
	
	private void initAgentsTable() {
		AgentService service = getAgentService();
		List<Agent> list = service.getAllAgents();
		ObservableList<Agent> content = FXCollections.observableList(list);
		agentTable.setItems(content);
		agentTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		agentNameCol.setCellValueFactory(new PropertyValueFactory<Agent, String>("name"));
	}
	
	private void initAgents() {
		costAgent.getItems().clear();
		AgentService service = getAgentService();
		List<Agent> list = service.getAllAgents();
		ObservableList<Agent> content = FXCollections.observableList(list);
		if (content.size() > 0) {
			costAgent.setItems(content);
			costAgent.getSelectionModel().select(0);
		}
	}
	
	private void initCategories() {
		costCat.getItems().clear();
		CategoryService service = getCatService();
		List<Category> list = service.getAllCats();
		ObservableList<Category> content = FXCollections.observableList(list);
		if (content.size() > 0) {
			costCat.setItems(content);
			costCat.getSelectionModel().select(0);
		}
	}
	
	private void initDatePicker() {
		costDate = new DatePicker(Locale.getDefault());
		costDate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		costDate.getCalendarView().todayButtonTextProperty().set("Today");
		costDate.getCalendarView().setShowWeeks(false);
		costDate.getStylesheets().add("ru/arkuzmin/costtracker/view/css/DatePicker.css");
		costDateGrid.add(costDate, 0, 0);
	}

	private void clearTxtFields() {
		costName.clear();
		costDesc.clear();
		costAmount.clear();
	}

	public void addCost(ActionEvent event) {
		CostService service = getCostService();
		Cost cost = new Cost();
		cost.setName(costName.getText());
		cost.setDesc(costDesc.getText());
		cost.setAmount(costAmount.getText());
		cost.setDate(costDate.getSelectedDate());
		
		int agentId = costAgent.getValue().getId();
		int catId = costCat.getValue().getId();
		
		service.addCost(cost, agentId, catId);
		clearTxtFields();
	}

	private CostService getCostService() {
		if (costService == null) {
			costService = new CostServiceImpl();
		}
		return costService;
	}
	
	private AgentService getAgentService() {
		if (agentService == null) {
			agentService = new AgentServiceImpl();
		}
		return agentService;
	}
	
	private CategoryService getCatService() {
		if (catService == null) {
			catService = new CategoryServiceImpl();
		}
		return catService;
	}
}
