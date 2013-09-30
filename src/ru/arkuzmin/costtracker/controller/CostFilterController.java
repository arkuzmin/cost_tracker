package ru.arkuzmin.costtracker.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import ru.arkuzmin.costtracker.controller.utils.ControllerUtils;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.bean.Category;
import ru.arkuzmin.costtracker.model.dto.CostFilter;
import eu.schudt.javafx.controls.calendar.DatePicker;

public class CostFilterController implements Initializable {

	@FXML 
	TextField nameTxt;
	
	@FXML
	ComboBox<Agent> agents;
	
	@FXML
	ComboBox<Category> cats;
	
	@FXML
	TextField amountTxt;
	
	@FXML
	GridPane startDtGrid;
	DatePicker startDtPicker;
	
	@FXML
	GridPane endDtGrid;
	DatePicker endDtPicker;
	
	@FXML 
	Button saveBtn;
	
	@FXML
	Button resetBtn;
	
	CostFilter filter;
	
	public void initData(CostFilter filter) {
		this.filter = filter;
		initFieldsWithData();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		initFields();
	}

	
	/** Начальная инициализация полей фильтра */
	private void initFields() {
		ControllerUtils.initAgentsWithFake(agents);
		ControllerUtils.initCategoriesWithFake(cats);
	}
	
	/** Инициализирует поля фильтра данными */
	private void initFieldsWithData() {
		if (filter != null) {
			nameTxt.setText(filter.getName() == null ? "" : filter.getName());
			startDtPicker.setSelectedDate(filter.getStartDt());
			endDtPicker.setSelectedDate(filter.getEndDt());
			amountTxt.setText(String.valueOf(filter.getAmount()));
			
			for (int i = 0; i < agents.getItems().size(); i++) {
				if (agents.getItems().get(i).getId() == filter.getAgent().getId()) {
					agents.getSelectionModel().select(i);
					break;
				}
			}
			
			for (int i = 0; i < cats.getItems().size(); i++) {
				if (cats.getItems().get(i).getId() == filter.getCat().getId()) {
					cats.getSelectionModel().select(i);
					break;
				}
			}
		}
	}
	
	
	
}
