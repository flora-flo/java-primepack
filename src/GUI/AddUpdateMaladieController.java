/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Maladie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.MaladieService;

/**
 * FXML Controller class
 *
 * @author Nessim Melliti
 */
public class AddUpdateMaladieController implements Initializable {

    MaladieService service;
    Maladie maladie;
    String type;
    private GestionMaladieController controller;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label TitleLabel;
    @FXML
    private TextField nomField;
    @FXML
    private TextField descriptionFiled;
    @FXML
    private Button actionButton;
    @FXML
    private TextField typeAnimaleFiled;
    @FXML
    private ComboBox<?> animaleCb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new MaladieService();
    }

    @FXML
    private void ajoutOrDelete(ActionEvent event) {
        if (controleDeSaisie()) {
            if (type.equals("Update")) {

                maladie.setAnimale_id(1); /// integration convert to animale id
                maladie.setNom(nomField.getText());
                maladie.setDescription(descriptionFiled.getText());
                maladie.setTypeAnimale(typeAnimaleFiled.getText());

                update(maladie);
            } else {
                maladie = new Maladie();
                maladie.setAnimale_id(1); /// integration convert to animale id
                maladie.setNom(nomField.getText());
                maladie.setDescription(descriptionFiled.getText());
                maladie.setTypeAnimale(typeAnimaleFiled.getText());
                ajout(maladie);
            }
            controller.refreshTable();
        }
    }

    private void update(Maladie m) {

        if (service.update(m)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("mise à jour avec succès");
            alert.setTitle("Succès");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("mise à jour fail !! ");
            alert.setTitle("fail");
            alert.show();
        }
    }

    private void ajout(Maladie m) {

        if (service.insert(m)) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("ajout avec succès");
            alert.setTitle("Succès");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("mise à jour fail ");
            alert.setTitle("fail");
            alert.show();
        }

    }

    public void setWindowType(String type) {
        this.type = type;
        actionButton.setText(type);
        TitleLabel.setText(type + " Maladie");
    }

    public void initializeTextField(Maladie m) {
        maladie = m;
        nomField.setText(m.getNom());
        descriptionFiled.setText(m.getDescription());
        typeAnimaleFiled.setText(m.getTypeAnimale());

    }

    private boolean controleDeSaisie() {

        if (nomField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le nom");
            alert.setTitle("fail");
            alert.show();
            return false;
        }
        if (descriptionFiled.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le description");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        if (typeAnimaleFiled.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le type animale");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        return true;
    }

    void initializeCategorieController(GestionMaladieController controller) {
        this.controller = controller;
    }

}
