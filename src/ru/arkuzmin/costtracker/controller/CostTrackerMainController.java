package ru.arkuzmin.costtracker.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.model.impl.AgentServiceImpl;

public class CostTrackerMainController implements Initializable {

	@FXML
	Button newAgent;
	@FXML
	TextField agentName;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	public void addAgent(ActionEvent event) {
		AgentServiceImpl service = new AgentServiceImpl();
		Agent agent = new Agent();
		agent.setName(agentName.getText());
		service.addAgent(agent);
		agentName.clear();
	}
}
