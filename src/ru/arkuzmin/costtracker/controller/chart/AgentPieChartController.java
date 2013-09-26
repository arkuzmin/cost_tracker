package ru.arkuzmin.costtracker.controller.chart;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ru.arkuzmin.costtracker.controller.utils.ControllerUtils;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.model.chart.PieChartUtils;
import eu.schudt.javafx.controls.calendar.DatePicker;

public class AgentPieChartController implements Initializable {

	@FXML
	PieChart chart;
	
	@FXML
	Button closeBtn;
	
	@FXML
	Button drawBtn;
	
	@FXML
	GridPane beginGrid;
	DatePicker beginDP;
	
	@FXML 
	GridPane endGrid;
	DatePicker endDP;
	
	@FXML
	ChoiceBox<Agent> agents;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ControllerUtils.initAgents(agents);
		
		beginDP = new DatePicker();
		endDP = new DatePicker();
		ControllerUtils.initDatePicker(beginDP, beginGrid);
		ControllerUtils.initDatePicker(endDP, endGrid);
	}

	public void close() {
		Stage stage = (Stage) closeBtn.getScene().getWindow();
		stage.close();
	}
	
	private void initChart() {
		chart.getData().clear();
		
		chart.setAnimated(true);
		chart.setData(PieChartUtils.createPieChartContentByAgent(agents.getValue(), beginDP.getSelectedDate(), endDP.getSelectedDate()));
		chart.setLabelLineLength(10);
		chart.setLegendSide(Side.RIGHT);
		chart.setTitle("Расходы для агента: " + agents.getValue().getName());
	}
	
	public void draw() {
		initChart();
	}
	
}
