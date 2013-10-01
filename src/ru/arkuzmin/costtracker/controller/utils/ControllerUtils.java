package ru.arkuzmin.costtracker.controller.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import ru.arkuzmin.costtracker.common.Globals;
import ru.arkuzmin.costtracker.common.ListSizes;
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
	
	public static void initListSizes(ComboBox<ListSizes> list) {
		if (list.getItems() != null) {
			list.getItems().clear();
		}
		
		ObservableList<ListSizes> content = FXCollections.observableList(ListSizes.getList());
		list.setItems(content);
		
		if (content != null && !content.isEmpty()) {
			list.getSelectionModel().select(ListSizes.HUNDRED);
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
		costDate.setDateFormat(new SimpleDateFormat("yyyy.MM.dd"));
		costDate.getCalendarView().todayButtonTextProperty().set("Сегодня");
		costDate.getCalendarView().setShowWeeks(false);
		costDate.getStylesheets().add("ru/arkuzmin/costtracker/view/css/DatePicker.css");
		costDateGrid.add(costDate, 0, 0);
	}
	
	/**
	 * Инициализация DatePicker конкретной датой.
	 * @param costDate - datepicker
	 * @param costDateGrid - его расположение
	 * @param date - конкретная дата
	 */
	public static void initDatePicker(DatePicker costDate, GridPane costDateGrid, Date date) {
		initDatePicker(costDate, costDateGrid);
		costDate.setSelectedDate(date);
	}
	
	/** 
	 * Возвращает дату, отличающуюся на заданное количество дней.
	 * @param days - количество дней
	 * @return новая дата
	 */
	public static Date getOtherDate(int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, days);
		return c.getTime();
	}
	
	/**
	 * Устанавливает координаты для отображения дочернего окна в центре родительского.
	 * @param child - дочернее окно
	 * @param parent - родительское окно
	 */
	public static void centerStage(Stage child, Stage parent) {
		child.setX(parent.getX() + parent.getWidth() / 2 - child.getWidth() / 2);
		child.setY(parent.getY() + parent.getHeight() / 2 - child.getHeight() / 2);
	}
	
	public static <S> void setDateCellFormat(TableColumn<S, Date> column) {
		column.setCellFactory(new Callback<TableColumn<S, Date>, TableCell<S, Date>>() {
			  @Override
			  public TableCell<S, Date> call(TableColumn<S, Date> param) {
			      return new TableCell<S, Date>() {
			          @Override
			          protected void updateItem(Date item, boolean empty) {
			              super.updateItem(item, empty);
			              if (!empty) {
			                setText(Globals.format.format(item));
			              } else {
			                setText(null);
			              }
			          }
			      };
			  }
		});
	}
}
