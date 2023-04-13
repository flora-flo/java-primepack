/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Evenement;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class AddUpdateEvenementController implements Initializable {

    EvenementService evenementService;
    Evenement evenement;
    String type;
    GestionEvenementController gestionEvenementController;

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
    private TextField sponsorFiled;
    @FXML
    private TextField nbplaceFiled;
    @FXML
    private DatePicker dateField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        evenementService = new EvenementService();
    }

    @FXML
    private void ajoutOrDelete(ActionEvent event) {
        if (controleDeSaisie()) {
            if (type.equals("Update")) {

                evenement.setNom(nomField.getText());
                evenement.setDate(Timestamp.valueOf(dateField.getValue().atStartOfDay()));
                evenement.setDescription(descriptionFiled.getText());
                evenement.setNbPlace((int) Float.parseFloat(nbplaceFiled.getText()));
                evenement.setSponsor(sponsorFiled.getText());

                update(evenement);
            } else {
                evenement = new Evenement();

                evenement.setNom(nomField.getText());
                evenement.setDate(Timestamp.valueOf(dateField.getValue().atStartOfDay()));
                evenement.setDescription(descriptionFiled.getText());
                evenement.setNbPlace((int) Float.parseFloat(nbplaceFiled.getText()));
                evenement.setSponsor(sponsorFiled.getText());

                ajout(evenement);
            }
            gestionEvenementController.refreshTable();
        }

    }

    private void update(Evenement e) {

        if (evenementService.update(e)) {
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

    private void ajout(Evenement e) {

        if (evenementService.insert(e)) {

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
        TitleLabel.setText(type + " Evenement");
    }

    public void initializeTextField(Evenement e) {
        evenement = e;
        nomField.setText(e.getNom());
        descriptionFiled.setText(e.getDescription());
        sponsorFiled.setText(e.getSponsor());
        nbplaceFiled.setText(e.getNbPlace() + "");
        dateField.setValue(e.getDate().toLocalDateTime().toLocalDate());

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
            alert.setContentText("Veuillez saisir le icone");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        if (sponsorFiled.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le description");
            alert.setTitle("fail");
            alert.show();
            return false;
        }
        try {
            Float.parseFloat(nbplaceFiled.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(nbplaceFiled.getText() + " n'est pas un nombre valide (nombre)");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        return true;
    }

    void initializeEvenementController(GestionEvenementController gestionEvenementController) {
        this.gestionEvenementController = gestionEvenementController;
    }
}
