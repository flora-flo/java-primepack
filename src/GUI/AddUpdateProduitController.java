/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Categorie;
import entites.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.CategorieService;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class AddUpdateProduitController implements Initializable {

   ProduitService produitService;
    CategorieService categorieService;
    Produit produit;
    String type;
    GestionProduitController gestionProduitController;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label TitleLabel;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prixFiled;
    @FXML
    private Button actionButton;
    @FXML
    private TextField stockFiled;
    @FXML
    private TextField imageFiled2;
    @FXML
    private ComboBox<Categorie> categorieCB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produitService = new ProduitService();
        categorieService = new CategorieService();
        ObservableList<Categorie> list = FXCollections.observableArrayList();
        list.addAll(categorieService.getAll());
        categorieCB.setItems(list);
    }

    @FXML
    private void ajoutOrDelete(ActionEvent event) {
        if (controleDeSaisie()) {
            if (type.equals("Update")) {

                produit.setCategory(categorieCB.getSelectionModel().getSelectedItem());
                produit.setImage(imageFiled2.getText());
                produit.setIsavailable(true);
                produit.setNom(nomField.getText());
                produit.setPrix(Float.parseFloat(prixFiled.getText()));
                produit.setStock((int) Float.parseFloat(stockFiled.getText()));

                update(produit);
            } else {
                produit = new Produit();
                produit.setCategory(categorieCB.getValue());
                produit.setImage(imageFiled2.getText());
                produit.setIsavailable(true);
                produit.setNom(nomField.getText());
                produit.setPrix(Float.parseFloat(prixFiled.getText()));
                produit.setStock((int) Float.parseFloat(stockFiled.getText()));
                ajout(produit);
            }
            gestionProduitController.refreshTable();
        }

    }

    private void update(Produit p) {

        if (produitService.update(p)) {
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

    private void ajout(Produit p) {

        if (produitService.insert(p)) {

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
        TitleLabel.setText(type + " Produit");
    }

    public void initializeTextField(Produit p) {
        produit = p;
        categorieCB.getSelectionModel().select(p.getCategory());
        imageFiled2.setText(p.getImage());
        nomField.setText(p.getNom());
        prixFiled.setText(p.getPrix() + "");
        stockFiled.setText(p.getStock() + "");

    }

    private boolean controleDeSaisie() {

        if (categorieCB.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le categorie");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        if (nomField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le nom");
            alert.setTitle("fail");
            alert.show();
            return false;
        }
        if (imageFiled2.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le icone");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        try {
            Float.parseFloat(prixFiled.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(prixFiled.getText() + " n'est pas un nombre valide (nombre)");
            alert.setTitle("fail");
            alert.show();
            return false;
        }
        try {
            Float.parseFloat(stockFiled.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(stockFiled.getText() + " n'est pas un nombre valide (nombre)");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        return true;
    }

    void initializeCategorieController(GestionProduitController gestionProduitController) {
        this.gestionProduitController = gestionProduitController;
    }

}
