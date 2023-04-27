/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class CarbonMotorBikeController implements Initializable {

    @FXML
    private TextField distanceTextField;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private Button calculateButton;
    @FXML
    private Label resultLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // Initialize the vehicle combo box with the available options
        typeComboBox.getItems().addAll("SmallMotorBike", "MediumMotorBike", "LargeMotorBike");
    }    

    @FXML
    private void calculateCarbonFootprint(ActionEvent event) throws IOException {
          // Retrieve input values from the text fields and combo box
        String distance = distanceTextField.getText();
        String type = typeComboBox.getValue();
       
        
          // Check if the distance is a valid number
    if (!distance.matches("\\d+(\\.\\d+)?")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Distance value is not valid");
            alert.showAndWait();
        return;
    }
    
    // Check if a motorbike is selected
    if (type == null || type.isEmpty()) {
       Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please select a motorbike");
            alert.showAndWait();
        return;
    }

        // Create the request URL with the input values
        String url = "https://carbonfootprint1.p.rapidapi.com/CarbonFootprintFromMotorBike?distance=" + distance
                + "&type=" + type ;

        // Set the request headers
        Map<String, String> headers = new HashMap<>();
        headers.put("X-RapidAPI-Key", "d5c3c75a76msh2ed002c70caa2e6p192357jsnb9d91e7a01de");
        headers.put("X-RapidAPI-Host", "carbonfootprint1.p.rapidapi.com");

        // Make the API request
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            connection.setRequestProperty(key, value);
        }

        // Parse the response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseBuilder = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            responseBuilder.append(inputLine);
        }
        in.close();
        String response = responseBuilder.toString();

        // Update the result label with the calculated carbon footprint
        resultLabel.setText(response);
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
         
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Estimation.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Emissions Estimation");
                    stage.setScene(scene);
                 
                    stage.show(); 
    }
    
}
