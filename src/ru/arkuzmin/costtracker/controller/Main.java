package ru.arkuzmin.costtracker.controller;

import org.apache.log4j.Logger;

import ru.arkuzmin.costtracker.common.EMFSingleton;
import ru.arkuzmin.costtracker.common.Globals;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	private static final Logger logger = Logger.getLogger(Main.class);
	
    public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
        	primaryStage.initStyle(StageStyle.DECORATED);
            AnchorPane page = (AnchorPane) FXMLLoader.load(Main.class.getResource(Globals.FXML_MAIN));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Учет затрат 1.0");
            primaryStage.setResizable(false);
            primaryStage.show();
            
        } catch (Exception e) {
            logger.error("Невозможно запустить приложение", e);
        }
    }
    
    
    @Override 
    public void stop() {
    	if (EMFSingleton.isOpened()) {
        	EMFSingleton.closeEMF();
    	}
    }
    
}

