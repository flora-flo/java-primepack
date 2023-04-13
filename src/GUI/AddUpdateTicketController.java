/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Evenement;
import entites.Ticket;
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
import services.EvenementService;
import services.TicketService;

public class AddUpdateTicketController implements Initializable {

    EvenementService evenementService;
    TicketService ticketService;
    Ticket ticket;
    String type;
    GestionTicketController gestionTicketController;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label TitleLabel;
    @FXML
    private TextField codeField;
    @FXML
    private Button actionButton;
    @FXML
    private TextField nbjourField;
    @FXML
    private ComboBox<Evenement> evenementcb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        evenementService = new EvenementService();
        ticketService = new TicketService();
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        list.addAll(evenementService.getAll());
        evenementcb.setItems(list);
    }

    @FXML
    private void ajoutOrDelete(ActionEvent event) {
        if (controleDeSaisie()) {
            if (type.equals("Update")) {

                ticket.setCode(codeField.getText());
                ticket.setEvnement(evenementcb.getValue());
                ticket.setNbJour((int) Float.parseFloat(nbjourField.getText()));
                ticket.setPrix(ticket.getNbJour() * 10);

                update(ticket);
            } else {
                ticket = new Ticket();
                ticket.setCode(codeField.getText());
                ticket.setEvnement(evenementcb.getValue());
                ticket.setNbJour((int) Float.parseFloat(nbjourField.getText()));
                ticket.setPrix(ticket.getNbJour() * 10);

                ajout(ticket);
            }
            gestionTicketController.refreshTable();
        }

    }

    private void update(Ticket t) {

        if (ticketService.update(t)) {
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

    private void ajout(Ticket t) {

        if (ticketService.insert(t)) {

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

    public void initializeTextField(Ticket t) {
        ticket = t;
        evenementcb.getSelectionModel().select(t.getEvnement());
        codeField.setText(t.getCode());
        nbjourField.setText(t.getPrix() / 10 + "");

    }

    private boolean controleDeSaisie() {

        if (evenementcb.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le evenement");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        if (codeField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir le nom");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        try {
            Float.parseFloat(nbjourField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(nbjourField.getText() + " n'est pas un nombre valide (nombre)");
            alert.setTitle("fail");
            alert.show();
            return false;
        }

        return true;
    }

    void initializeTicketController(GestionTicketController gestionTicketController) {
        this.gestionTicketController = gestionTicketController;
    }

}
