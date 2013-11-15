package ru.arkuzmin.costtracker.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.apache.log4j.Logger;

import ru.arkuzmin.costtracker.common.EMFSingleton;
import ru.arkuzmin.costtracker.common.Globals;

public class Main extends Application {

	private static final Logger logger = Logger.getLogger(Main.class);
	
    public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
        	primaryStage.initStyle(StageStyle.TRANSPARENT);
            AnchorPane page = (AnchorPane) FXMLLoader.load(Main.class.getResource(Globals.FXML_MAIN));
        	Rectangle rect = new Rectangle(680,275);
        	rect.setArcHeight(60.0);
        	rect.setArcWidth(60.0);

        	page.setClip(rect);
            
            Scene scene = new Scene(page, 680, 275);
            scene.setFill(null);
            primaryStage.setScene(scene);
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

