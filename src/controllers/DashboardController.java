/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author expert
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    public void animal() throws IOException {
          Parent fmxlLoader = FXMLLoader. load(getClass().getResource("/gui/AfficherAnimal.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fmxlLoader);
        
    }

    
    public void ordonnance() throws IOException {
         Parent fmxlLoader = FXMLLoader.load(getClass().getResource("/gui/AfficherOrdonnanceFXML.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fmxlLoader);
    }
    
}
