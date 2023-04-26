/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class BaseController implements Initializable {

    public static BaseController baseController;
    @FXML
    private Label TitreLabel;
    @FXML
    private StackPane AnchorePaneLayout;
    @FXML
    private Label ConnectedUserNameLabel;
    @FXML
    private ImageView logoImageview;
    @FXML
    private HBox gestionMaladieButton;
    @FXML
    private HBox GestionOperationButon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        baseController = this;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI/GestionMaladie.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Maladie");
            gestionMaladieButton.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeSelectedStyle() {
        gestionMaladieButton.getStyleClass().clear();
        GestionOperationButon.getStyleClass().clear();

        gestionMaladieButton.getStyleClass().add("btns");
        GestionOperationButon.getStyleClass().add("btns");

    }

    @FXML
    private void LoadGestionMaladie(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GestionMaladie.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Maladie ");
            gestionMaladieButton.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadGestionOperation(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GestionOperation.fxml"));
            removeSelectedStyle();
            TitreLabel.setText("Gestion Operation");
            GestionOperationButon.getStyleClass().add("btn-selected");
            AnchorePaneLayout.getChildren().removeAll();
            AnchorePaneLayout.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
