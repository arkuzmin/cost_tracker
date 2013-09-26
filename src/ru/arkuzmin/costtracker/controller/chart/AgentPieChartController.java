package ru.arkuzmin.costtracker.controller.chart;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.arkuzmin.costtracker.common.Globals;
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
	Text totalTxt;
	@FXML
	Text totalCaption;
	
	@FXML
	Text chartHelpTxt;
	
	@FXML
	ComboBox<Agent> agents;
	
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
	
	private void clearChartData() {
		chart.getData().clear();
		totalTxt.setText("");
		chartHelpTxt.setVisible(true);
		totalCaption.setVisible(false);
		totalTxt.setText("");
	}
	
	private void initChart() {
		clearChartData();
		
		ObservableList<PieChart.Data> data = PieChartUtils.createPieChartContentByAgent(agents.getValue(), beginDP.getSelectedDate(), endDP.getSelectedDate());
		
		chartHelpTxt.setVisible(false);
		
		chart.setAnimated(true);
		chart.setData(data);
		chart.setLabelLineLength(10);
		chart.setLegendSide(Side.RIGHT);
		chart.setTitle("Расходы для агента: " + agents.getValue().getName());
	
		double total = 0.0;
		for (PieChart.Data d : data) {
			total += d.getPieValue();
		}
		
		totalCaption.setVisible(true);
		totalTxt.setText(String.valueOf(total) + Globals.RUB_SUFFIX);
	}
	
	public void draw() {
		initChart();
	}
	
}
