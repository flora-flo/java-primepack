/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entites.Animal;
import entites.Ordonnance;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.OrdonnanceService;

/**
 * FXML Controller class
 *
 * @author expert
 */
public class OrdonnnaceFXMLController implements Initializable {

    @FXML
    private TextField txt_desc;
    @FXML
    private TextField txt_trait;
    @FXML
    private Button btnajouter;
    @FXML
    private DatePicker txt_date;

    /**
     * Initializes the controller class.
     */
     OrdonnanceService ordonnanceService=new OrdonnanceService();
    @Override
     
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
    @FXML
    private void ajoutOrdonnance(ActionEvent event) throws SQLException {
    
           Ordonnance o = new Ordonnance();
        o.setDescription(txt_desc.getText());
        o.setTraitement(txt_trait.getText());
        o.setDate(Date.valueOf(txt_date.getValue()));
        

        ordonnanceService.ajoutOrdonnance(o);
    }
    
    
    
    
   
    
}
