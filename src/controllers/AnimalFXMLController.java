/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entites.Animal;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.AnimalService;

/**
 * FXML Controller class
 *
 * @author expert
 */
public class AnimalFXMLController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfrace;
    @FXML
    private TextField tfgenre;
    @FXML
    private TextField tfage;
    @FXML
    private TextField tfdesc;
    @FXML
    private Button btnajouter;

    public void setTfnom(String tfnom) {
        this.tfnom.setText(tfnom);
    }

    public void setTfrace(String tfrace) {
        this.tfrace.setText(tfrace);
    }

    public void setTfgenre(String tfgenre) {
        this.tfgenre.setText(tfgenre);
    }

    public void setTfage(String tfage) {
        this.tfage.setText(tfage) ;
    }

    public void setTfdesc(String tfdesc) {
        this.tfdesc.setText(tfdesc) ;
    }
    
    @FXML
    private AnchorPane root;
    AnimalService animalService  = new AnimalService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutAnimal(ActionEvent event) throws SQLException {
             Animal a = new Animal();
        a.setNom(tfnom.getText());
        a.setRace(tfrace.getText());
        a.setGenre(tfgenre.getText());
        a.setAge(tfage.getText());
        a.setDescription(tfdesc.getText());

        animalService.ajoutAnimal(a);
    }
   @FXML
    void tesr() throws IOException {
        Parent fmxlLoader = FXMLLoader.load(getClass().getResource("AfficherAnimal.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fmxlLoader);

    } 
    
}
