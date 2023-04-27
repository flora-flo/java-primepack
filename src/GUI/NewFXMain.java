/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author MSI
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
     
        try {
             
         //   FXMLLoader loader = new FXMLLoader(getClass().getResource("Estimation.fxml"));  
        //    FXMLLoader loader = new FXMLLoader(getClass().getResource("CarbonMotorBike.fxml"));  
          //  FXMLLoader loader = new FXMLLoader(getClass().getResource("CarbonFlight.fxml"));  
        //  FXMLLoader loader = new FXMLLoader(getClass().getResource("CarbonCar.fxml"));  
     //   FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowCommentBack.fxml"));  
    FXMLLoader loader = new FXMLLoader(getClass().getResource("postFront.fxml"));
  // FXMLLoader loader = new FXMLLoader(getClass().getResource("PostBack.fxml"));
         //FXMLLoader loader = new FXMLLoader(getClass().getResource("PostAdd.fxml"));
    //   loader.setRoot(new AnchorPane());
         Parent root = loader.load();
         Scene scene = new Scene(root);
         primaryStage.getIcons().add(new Image("/image/logo.png"));
         primaryStage.setTitle("Show Post");
         primaryStage.setScene(scene);
         primaryStage.show();
       
           
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
