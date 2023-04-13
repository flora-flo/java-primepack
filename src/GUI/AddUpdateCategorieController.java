/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Categorie;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class AddUpdateCategorieController implements Initializable {

    CategorieService categorieService;
    Categorie categorie;
    String type;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label TitleLabel;
    @FXML
    private TextField nomField;
    @FXML
    private TextField iconeFiled;
    @FXML
    private Button actionButton;
    @FXML
    private TextField descriptionFiled;
    @FXML
    private TextField NoteFiled;
    private GestionCategorieController gestionCategorieController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categorieService = new CategorieService();
    }

    @FXML
    private void ajoutOrDelete(ActionEvent event) {
        if (controleDeSaisie()) {
            if (type.equals("Update")) {

                categorie.setIcone(iconeFiled.getText());
                categorie.setNom(nomField.getText());
                categorie.setDescription(descriptionFiled.getText());
                categorie.setNote(Float.parseFloat(NoteFiled.getText()));

                update(categorie);
            } else {
                categorie = new Categorie();
                categorie.setIcone(iconeFiled.getText());
                categorie.setNom(nomField.getText());
                categorie.setDescription(descriptionFiled.getText());
                categorie.setNote(Float.parseFloat(NoteFiled.getText()));
                ajout(categorie);
            }
            gestionCategorieController.refreshTable();
        }

    }

    private void update(Categorie c) {

        if (categorieService.update(c)) {
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

    private void ajout(Categorie c) {

        if (categorieService.insert(c)) {

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
        TitleLabel.setText(type + " Categorie");
    }

    public void initializeTextField(Categorie c) {
        categorie = c;
        nomField.setText(c.getNom());
        iconeFiled.setText(c.getIcone());
        descriptionFiled.setText(c.getDescription());
        NoteFiled.setText(c.getNote() + "");

    }

    private boolean controleDeSaisie() {

        if (nomField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le nom");
            alert.setTitle("fail");
            alert.show();
            return false;
        }
        if (iconeFiled.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le icone");
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
        try {
            Float.parseFloat(NoteFiled.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(NoteFiled.getText() + " n'est pas un nombre valide (nombre)");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        return true;
    }

    void initializeCategorieController(GestionCategorieController gestionCategorieController) {
        this.gestionCategorieController = gestionCategorieController;
    }

}
