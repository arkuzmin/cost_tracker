package ru.arkuzmin.costtracker.controller.utils;

import java.text.SimpleDateFormat;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.bean.Category;
import ru.arkuzmin.costtracker.db.model.AgentService;
import ru.arkuzmin.costtracker.db.model.CategoryService;
import ru.arkuzmin.costtracker.db.model.ServiceFactory;
import eu.schudt.javafx.controls.calendar.DatePicker;

public class ControllerUtils {
	
	public static void initAgents(ChoiceBox<Agent> costAgent) {
		if (costAgent.getItems() != null) {
			costAgent.getItems().clear();	
		}

		AgentService service = ServiceFactory.getAgentService();
		List<Agent> list = service.getAllAgents();
		
		ObservableList<Agent> content = FXCollections.observableList(list);
		costAgent.setItems(content);
	}
	
	public static void initCategories(ChoiceBox<Category> costCategory) {
		if (costCategory.getItems() != null) {
			costCategory.getItems().clear();
		}
		
		CategoryService service = ServiceFactory.getCategoryService();
		List<Category> list = service.getAllCats();
		
		ObservableList<Category> content = FXCollections.observableList(list);
		costCategory.setItems(content);
	}
	
	public static void initDatePicker(DatePicker costDate, GridPane costDateGrid) {
		costDate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		costDate.getCalendarView().todayButtonTextProperty().set("Today");
		costDate.getCalendarView().setShowWeeks(false);
		costDate.getStylesheets().add("ru/arkuzmin/costtracker/view/css/DatePicker.css");
		costDateGrid.add(costDate, 0, 0);
	}
}
