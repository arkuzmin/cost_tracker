package ru.arkuzmin.costtracker.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ru.arkuzmin.costtracker.common.CommonUtils;
import ru.arkuzmin.costtracker.common.ListSizes;
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
	ComboBox<ListSizes> listSize;
	
	@FXML 
	Button saveBtn;
	
	@FXML
	Button resetBtn;
	
	@FXML
	Button closeBtn;
	
	CostFilter filter;
	
	CostsController parent;
	
	private static final Logger logger = Logger.getLogger(CostFilterController.class);
	
	public void initData(CostsController parent, CostFilter filter) {
		this.filter = filter;
		this.parent = parent;
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
		ControllerUtils.initListSizes(listSize);
		
		startDtPicker = new DatePicker();
		endDtPicker = new DatePicker();
		ControllerUtils.initDatePicker(startDtPicker, startDtGrid, ControllerUtils.getOtherDate(-7));
		ControllerUtils.initDatePicker(endDtPicker, endDtGrid, new Date());
		
		nameTxt.setText("");
		amountTxt.setText("");
	}
	
	/** Инициализирует поля фильтра данными */
	private void initFieldsWithData() {
		if (filter != null) {
			nameTxt.setText(filter.getName() == null ? "" : filter.getName());
			startDtPicker.setSelectedDate(filter.getStartDt());
			endDtPicker.setSelectedDate(filter.getEndDt());
			amountTxt.setText(filter.getAmount() == null ? "" : String.valueOf(filter.getAmount()));
			
			if (filter.getAgent() != null) {
				for (int i = 0; i < agents.getItems().size(); i++) {
					if (agents.getItems().get(i).getId() == filter.getAgent().getId()) {
						agents.getSelectionModel().select(i);
						break;
					}
				}
			}

			if (filter.getCat() != null) {
				for (int i = 0; i < cats.getItems().size(); i++) {
					if (cats.getItems().get(i).getId() == filter.getCat().getId()) {
						cats.getSelectionModel().select(i);
						break;
					}
				}
			}
			
			listSize.setValue(filter.getListSize());
		}
	}
	
	public void close() {
		((Stage) closeBtn.getScene().getWindow()).close();
	}
	
	public void reset() {
		initFields();
		saveFilter();
	}
	
	public void save() {
		saveFilter();
		close();
	}
	
	private void saveFilter() {
		if (filter != null) {
			filter.setName(nameTxt.getText());
			filter.setAgent(agents.getValue());
			filter.setCat(cats.getValue());
			filter.setAmount(CommonUtils.strToDoubleSafe(amountTxt.getText()));
			filter.setStartDt(startDtPicker.getSelectedDate());
			filter.setEndDt(endDtPicker.getSelectedDate());
			filter.setListSize(listSize.getValue());
			parent.saveFilter(filter);
		} else {
			logger.error("Невозможно сохранить выбранный фильтр. filter == null");
		}
	}
	
}
