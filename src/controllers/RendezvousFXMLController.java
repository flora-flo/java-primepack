/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entites.Ordonnance;
import entites.Rendezvous;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.RendezvousService;

/**
 * FXML Controller class
 *
 * @author expert
 */
public class RendezvousFXMLController implements Initializable {

    @FXML
    private TextField txtduree;

    @FXML
    private DatePicker txtdate;

    @FXML
    private Button btnajout;
    /**
     * Initializes the controller class.
     */
  RendezvousService rendezvousService=new RendezvousService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void ajoutRendezvous(ActionEvent event) throws SQLException {
    
           Rendezvous r = new Rendezvous();
        r.setDuree(Integer.valueOf(txtduree.getText()));
        r.setDate(Date.valueOf(txtdate.getValue()));
       
        

        rendezvousService.ajoutRendezvous(r);
    }
}
