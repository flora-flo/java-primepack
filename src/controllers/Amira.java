/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.MyDB;

/**
 *
 * @author expert
 */
public class Amira extends Application {
    
        
      public static Stage primaryStage;
   //  public static Stage stage = null;
   

    @Override
    public void start(Stage primaryStage) throws Exception {
    
        

       // Amira.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/gui/dashboard.fxml"));

        Scene scene = new Scene(root);
       // primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        launch();
        // TODO code application logic here
        MyDB mc = MyDB.getInstance();
    }
    
}
