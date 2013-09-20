package ru.arkuzmin.costtracker.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.model.impl.AgentServiceImpl;
import ru.arkuzmin.costtracker.view.Main;

public class CostTrackerMainController implements Initializable {

	@FXML
	Button newAgent;
	@FXML
	TextField agentName;
	@FXML
	ChoiceBox<Agent> choice;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	//	configureChoice();
	}

	public void addAgent(ActionEvent event) {
		AgentServiceImpl service = new AgentServiceImpl();
		Agent agent = new Agent();
		agent.setName(agentName.getText());
		service.addAgent(agent);
		agentName.clear();
		configureChoice();
	}

	private void configureChoice() {
		AgentServiceImpl service = new AgentServiceImpl();
		List<Agent> list = service.getAllAgents();
		ObservableList<Agent> tableContent = FXCollections.observableList(list);
		choice.setItems(tableContent);
	}
	

	public void showCosts() throws IOException {
		 AnchorPane page = (AnchorPane) FXMLLoader.load(Main.class.getResource("AllCosts.fxml"));
         Scene scene = new Scene(page);
         Stage secStage = new Stage();
         secStage.setScene(scene);
         secStage.show();
	}

}
