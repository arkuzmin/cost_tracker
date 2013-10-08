package ru.arkuzmin.costtracker.controller.utils;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class CommonController implements Initializable {

	@FXML 
	protected Button closeBtn;
	
	@FXML
	protected Button submitBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initButtons();
		// Инициализация дочерними котроллерами
		init(arg0, arg1);
	}
	
	protected abstract void init(URL arg0, ResourceBundle arg1);
	
	/** 
	 * Выполняет действия по сохранению / подтверждению данных, введенных в экранной форме этого контроллера.
	 */
	public abstract void submit();
	
	/** 
	 * Закрывает окно, за которое отвечает этот контроллер.
	 */
	public void close() {
		Stage currentStage = (Stage) closeBtn.getScene().getWindow();
		currentStage.close();	
	}
	
	/** 
	 * Выполняет инициализацию кнопок:
	 *	-кнопка закрытия окна
	 *	-кнопка сабмита формы
	 */
	protected void initButtons() {
		
	}
	

}
