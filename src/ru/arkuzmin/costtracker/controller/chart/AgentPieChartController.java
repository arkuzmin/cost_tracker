package ru.arkuzmin.costtracker.controller.chart;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.model.chart.PieChartUtils;

public class AgentPieChartController implements Initializable {

	@FXML
	PieChart chart;
	
	@FXML
	Button closeBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initChart();
	}

	public void close() {
		Stage stage = (Stage) closeBtn.getScene().getWindow();
		stage.close();
	}
	
	private void initChart() {
		Agent agent = new Agent();
		agent.setId(12);
		chart.setAnimated(true);
		chart.setData(PieChartUtils.createPieChartContentByAgent(agent));
		chart.setLabelLineLength(10);
		chart.setLegendSide(Side.RIGHT);
	}
	
}
