/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Categorie;
import entites.Produit;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.CategorieService;
import services.ProduitService;

public class GestionProduitController implements Initializable {

    ProduitService produitService;
    ObservableList<Produit> listProduit = FXCollections.observableArrayList();
    Produit produit;
    GestionProduitController gestionProduitController;
    @FXML
    private TableView<Produit> categorieTableView;
    @FXML
    private TableColumn<Produit, String> nomCell;
    @FXML
    private TableColumn<Produit, String> prixCell;
    @FXML
    private TableColumn<Produit, String> stokCell;
    @FXML
    private TableColumn<Produit, String> availbaleCell;
    @FXML
    private TableColumn<Produit, String> categorieCell;
    @FXML
    private TableColumn<Produit, String> ActionCell;
    @FXML
    private Button ButtonAjout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gestionProduitController = this;
        produitService = new ProduitService();
        LoadData();
    }

    public void refreshTable() {

        listProduit.clear();
        listProduit.addAll(produitService.getAll());
        categorieTableView.setItems(listProduit);

    }

    private void LoadData() {

        refreshTable();
        nomCell.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixCell.setCellValueFactory(new PropertyValueFactory<>("prix"));
        stokCell.setCellValueFactory(new PropertyValueFactory<>("stock"));
        availbaleCell.setCellValueFactory(cellData
                -> {
            return new SimpleStringProperty(cellData.getValue().getStock() > 0 ? "true" : "false");
        });
        categorieCell.setCellValueFactory(cellData
                -> {
            return new SimpleStringProperty(cellData.getValue().getCategory().getNom());
        });
        ActionCell.setCellFactory(createActionButton());

    }

    private Callback<TableColumn<Produit, String>, TableCell<Produit, String>> createActionButton() {
        Callback<TableColumn<Produit, String>, TableCell<Produit, String>> cellFoctory = (TableColumn<Produit, String> param) -> {
            // make cell containing buttons
            final TableCell<Produit, String> cell = new TableCell<Produit, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView logoIcon = new FontAwesomeIconView(FontAwesomeIcon.PLUS_CIRCLE);
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirmation de suppression ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                            alert.showAndWait();

                            if (alert.getResult() == ButtonType.YES) {
                                produit = categorieTableView.getSelectionModel().getSelectedItem();
                                produitService.delete(produit);
                                refreshTable();
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {

                                produit = categorieTableView.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUpdateProduit.fxml"));

                                Parent root;
                                root = loader.load();

                                AddUpdateProduitController addUpdateProduitController = loader.getController();
                                addUpdateProduitController.initializeCategorieController(gestionProduitController);
                                addUpdateProduitController.initializeTextField(produit);
                                addUpdateProduitController.setWindowType("Update");
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(GestionCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                        logoIcon.setOnMouseClicked((MouseEvent event) -> {

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(logoIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        return cellFoctory;
    }

    @FXML
    private void openAjoutDialog(ActionEvent event) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("AddUpdateProduit.fxml"));
            Parent root = fxml.load();
            AddUpdateProduitController addUpdateProduitController = fxml.getController();
            addUpdateProduitController.setWindowType("Ajout");
            addUpdateProduitController.initializeCategorieController(this);
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();

            Scene scene = new Scene(root, 600.0, Math.min(400.0, screenBounds.getHeight()));
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
