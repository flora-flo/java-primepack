/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class EstimationController implements Initializable {

    @FXML
    private Button carButton;
    @FXML
    private Button motorButton;
    @FXML
    private Button flight;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void redirectToCar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CarbonCar.fxml"));
            Parent root = loader.load();
           
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Car Estimation");
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void redirectToMotor(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("CarbonMotorBike.fxml"));
            Parent root = loader.load();
           
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Motorbike Estimation");
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void redirectToFlight(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("CarbonFlight.fxml"));
            Parent root = loader.load();
           
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Flight Estimation");
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("PostFront.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Post Front");
                    stage.setScene(scene);
                 
                    stage.show(); 
    }
    
}
