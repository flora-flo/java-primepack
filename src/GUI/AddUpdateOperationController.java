/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Maladie;
import entites.Operation;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.MaladieService;
import services.OperationService;

/**
 * FXML Controller class
 *
 * @author Nessim Melliti
 */
public class AddUpdateOperationController implements Initializable {

    OperationService service;
    MaladieService maladieService;
    Operation operation;
    String type;
    private GestionOperationController controller;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label TitleLabel;
    @FXML
    private DatePicker dateF;
    @FXML
    private TextField medecinFiled;
    @FXML
    private TextField coutFiled;
    @FXML
    private TextField NoteFiled;
    @FXML
    private Button actionButton;
    @FXML
    private ComboBox<Maladie> maldieCB;
    @FXML
    private ComboBox<?> animeleCB;
    @FXML
    private TextField typeFiled;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new OperationService();
        maladieService = new MaladieService();

        ObservableList<Maladie> list = FXCollections.observableArrayList();
        list.addAll(maladieService.getAll());
        maldieCB.setItems(list);
    }

    @FXML
    private void ajoutOrDelete(ActionEvent event) {
        if (controleDeSaisie()) {
            if (type.equals("Update")) {

                operation.setMaladie(maldieCB.getSelectionModel().getSelectedItem());
                operation.setAnimaleId(1);
                operation.setDateOperation(Timestamp.valueOf(dateF.getValue().atStartOfDay()));
                operation.setTypeOperation(typeFiled.getText());
                operation.setNomMedecin(medecinFiled.getText());
                operation.setCoutOperation(Float.parseFloat(coutFiled.getText()));
                operation.setNoteOperation(NoteFiled.getText());

                update(operation);
            } else {
                Operation operation = new Operation();
                operation.setMaladie(maldieCB.getSelectionModel().getSelectedItem());
                operation.setAnimaleId(1);
                operation.setDateOperation(Timestamp.valueOf(dateF.getValue().atStartOfDay()));
                operation.setTypeOperation(typeFiled.getText());
                operation.setNomMedecin(medecinFiled.getText());
                operation.setCoutOperation(Float.parseFloat(coutFiled.getText()));
                operation.setNoteOperation(NoteFiled.getText());
                ajout(operation);
            }
            controller.refreshTable();
        }
    }

    private void update(Operation o) {

        if (service.update(o)) {
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

    private void ajout(Operation o) {

        if (service.insert(o)) {

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
        TitleLabel.setText(type + " Operation");
    }

    public void initializeTextField(Operation o) {
        operation = o;

        maldieCB.getSelectionModel().select(o.getMaladie());
        dateF.setValue(o.getDateOperation().toLocalDateTime().toLocalDate());
        typeFiled.setText(o.getTypeOperation());
        medecinFiled.setText(o.getNomMedecin());
        coutFiled.setText(o.getCoutOperation() + "");
        NoteFiled.setText(o.getNoteOperation());

    }

    private boolean controleDeSaisie() {

        if (dateF.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez selecet Date");
            alert.setTitle("fail");
            alert.show();
            return false;
        }
        try {
            Float.parseFloat(coutFiled.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(coutFiled.getText() + " n'est pas un nombre valide (nombre)");
            alert.setTitle("fail");
            alert.show();
            return false;
        }
        if (medecinFiled.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le nom medecin");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        if (typeFiled.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le type");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        if (NoteFiled.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le note");
            alert.setTitle("fail");
            alert.show();
            return false;
        }
        if (maldieCB.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le maladie");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        return true;
    }

    void initializeController(GestionOperationController controller) {
        this.controller = controller;
    }

}
