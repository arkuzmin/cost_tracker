package ru.arkuzmin.costtracker.controller.utils;

import java.text.SimpleDateFormat;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import ru.arkuzmin.costtracker.common.Globals;
import ru.arkuzmin.costtracker.db.bean.Agent;
import ru.arkuzmin.costtracker.db.bean.Category;
import ru.arkuzmin.costtracker.db.model.AgentService;
import ru.arkuzmin.costtracker.db.model.CategoryService;
import ru.arkuzmin.costtracker.db.model.ServiceFactory;
import eu.schudt.javafx.controls.calendar.DatePicker;

/** 
 * Вспомогательные методы для контроллеров.
 * @author ArKuzmin
 *
 */
public class ControllerUtils {
	
	/** Возвращает фейкового агента */
	public static Agent getFakeAgent() {
		Agent agent = new Agent();
		agent.setId(Globals.FAKE_ID);
		agent.setName("Любой");
		
		return agent;
	}
	
	/** Возвращает фейковую категорию */
	public static Category getFakeCat() {
		Category cat = new Category();
		cat.setId(Globals.FAKE_ID);
		cat.setName("Любая");
		cat.setDesc("Любая");
		
		return cat;
	}
	
	/** Инициализация выпадающего списка агентов */
	public static void initAgents(ComboBox<Agent> costAgent) {
		if (costAgent.getItems() != null) {
			costAgent.getItems().clear();	
		}

		AgentService service = ServiceFactory.getAgentService();
		List<Agent> list = service.getAllAgents();
		
		ObservableList<Agent> content = FXCollections.observableList(list);
		costAgent.setItems(content);
		
		if (content != null && !content.isEmpty()) {
			costAgent.getSelectionModel().select(0);
		}
	}
	
	/** Инициализация выпадающего списка категорий */
	public static void initCategories(ComboBox<Category> costCategory) {
		if (costCategory.getItems() != null) {
			costCategory.getItems().clear();
		}
		
		CategoryService service = ServiceFactory.getCategoryService();
		List<Category> list = service.getAllCats();
		
		ObservableList<Category> content = FXCollections.observableList(list);
		costCategory.setItems(content);
		
		if (content != null && !content.isEmpty()) {
			costCategory.getSelectionModel().select(0);
		}
	}
	
	public static void initAgentsWithFake(ComboBox<Agent> costAgent) {
		initAgents(costAgent);
		Agent fake = getFakeAgent();
		costAgent.getItems().add(0, fake);
		costAgent.getSelectionModel().select(0);
	}
	
	public static void initCategoriesWithFake(ComboBox<Category> costCategory) {
		initCategories(costCategory);
		Category fake = getFakeCat();
		costCategory.getItems().add(0, fake);
		costCategory.getSelectionModel().select(0);
	}
	
	/** Инициализация DatePicker */
	public static void initDatePicker(DatePicker costDate, GridPane costDateGrid) {
		costDate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		costDate.getCalendarView().todayButtonTextProperty().set("Сегодня");
		costDate.getCalendarView().setShowWeeks(false);
		costDate.getStylesheets().add("ru/arkuzmin/costtracker/view/css/DatePicker.css");
		costDateGrid.add(costDate, 0, 0);
	}
}
