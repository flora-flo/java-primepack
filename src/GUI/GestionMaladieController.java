/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Maladie;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.MaladieService;

/**
 * FXML Controller class
 *
 * @author Nessim Melliti
 */
public class GestionMaladieController implements Initializable {

    MaladieService service;
    ObservableList<Maladie> list = FXCollections.observableArrayList();
    Maladie maladie;
    GestionMaladieController controller;

    @FXML
    private TableView<Maladie> TableView;
    @FXML
    private TableColumn<Maladie, String> animaleCell;
    @FXML
    private TableColumn<Maladie, String> nomCell;
    @FXML
    private TableColumn<Maladie, String> descCell;
    @FXML
    private TableColumn<Maladie, String> typeAnimaleCell;
    @FXML
    private TableColumn<Maladie, String> dateCreationCell;
    @FXML
    private TableColumn<Maladie, String> dateMajCell;
    @FXML
    private TableColumn<Maladie, String> ActionCell;
    @FXML
    private Button ButtonAjout;
    @FXML
    private TextField searchTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = this;
        service = new MaladieService();
        LoadData();
    }

    public void refreshTable() {

        list.clear();
        list.addAll(service.getAll());
        TableView.setItems(list);

        if (list.size() > 0) {
            FilteredList<Maladie> filterData = recherche(list);
            String a = searchTextField.getText();
            TableView.setItems(filterData);
        }

    }

    private void LoadData() {

        refreshTable();
        animaleCell.setCellValueFactory(new PropertyValueFactory<>("animale_id"));
        nomCell.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descCell.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeAnimaleCell.setCellValueFactory(new PropertyValueFactory<>("typeAnimale"));
        dateCreationCell.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        dateMajCell.setCellValueFactory(new PropertyValueFactory<>("dateMaj"));
        ActionCell.setCellFactory(createActionButton());

    }

    private Callback<TableColumn<Maladie, String>, TableCell<Maladie, String>> createActionButton() {
        Callback<TableColumn<Maladie, String>, TableCell<Maladie, String>> cellFoctory = (TableColumn<Maladie, String> param) -> {
            // make cell containing buttons
            final TableCell<Maladie, String> cell = new TableCell<Maladie, String>() {
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
                                maladie = TableView.getSelectionModel().getSelectedItem();
                                service.delete(maladie);
                                refreshTable();
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {

                                maladie = TableView.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUpdateMaladie.fxml"));

                                Parent root;
                                root = loader.load();

                                AddUpdateMaladieController controllera = loader.getController();
                                controllera.initializeCategorieController(controller);
                                controllera.initializeTextField(maladie);
                                controllera.setWindowType("Update");
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(GestionMaladieController.class.getName()).log(Level.SEVERE, null, ex);
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

    private FilteredList<Maladie> recherche(ObservableList matchList) {
        FilteredList<Maladie> filterData = new FilteredList<Maladie>(matchList, b -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(SearchModel -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String serachKeeyword = newValue.toLowerCase();
                if (((Maladie) SearchModel).getNom().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if ((((Maladie) SearchModel).getDescription() + "").toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if ((((Maladie) SearchModel).getTypeAnimale() + "").toLowerCase().contains(serachKeeyword)) {
                    return true;
                }

                return false;
            });

        });

        return filterData;
    }

    @FXML
    private void openAjoutDialog(ActionEvent event) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("AddUpdateMaladie.fxml"));
            Parent root = fxml.load();
            AddUpdateMaladieController controller = fxml.getController();
            controller.setWindowType("Ajout");
            controller.initializeCategorieController(this);
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();

            Scene scene = new Scene(root, 600.0, Math.min(400.0, screenBounds.getHeight()));
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionMaladieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
