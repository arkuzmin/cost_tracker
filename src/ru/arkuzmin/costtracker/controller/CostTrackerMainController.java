package ru.arkuzmin.costtracker.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.model.impl.AgentServiceImpl;

public class CostTrackerMainController implements Initializable {

	@FXML
	Button newAgent;
	@FXML
	TextField agentName;
	@FXML
	TableView<Agent> table;
	@FXML
	TableColumn<Agent, String> colName;
	@FXML
	TableColumn<Agent, Button> colDel;
	@FXML
	TableColumn<Agent, Button> colUpd;
	@FXML
	ChoiceBox<Agent> choice;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		configureTable();
		configureChoice();
	}

	public void addAgent(ActionEvent event) {
		AgentServiceImpl service = new AgentServiceImpl();
		Agent agent = new Agent();
		agent.setName(agentName.getText());
		service.addAgent(agent);
		agentName.clear();
		configureTable();
		configureChoice();
	}

	private void configureChoice() {
		AgentServiceImpl service = new AgentServiceImpl();
		List<Agent> list = service.getAllAgents();
		ObservableList<Agent> tableContent = FXCollections.observableList(list);
		choice.setItems(tableContent);
	}
	
	private void configureTable() {
		colName.setCellValueFactory(new PropertyValueFactory<Agent, String>("name"));
		
		colName.setPrefWidth(40);
		colDel.setPrefWidth(20);
		colUpd.setPrefWidth(20);

		colName.setMinWidth(40);
		colDel.setMinWidth(20);
		colUpd.setMinWidth(20);

		colName.setMaxWidth(750);
		colDel.setMaxWidth(500);
		colUpd.setMaxWidth(500);

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		AgentServiceImpl service = new AgentServiceImpl();
		List<Agent> list = service.getAllAgents();
		ObservableList<Agent> tableContent = FXCollections.observableList(list);
		
		table.setItems(tableContent);
	}

}
