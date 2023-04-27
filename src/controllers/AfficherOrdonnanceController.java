/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entites.Animal;
import entites.Ordonnance;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.AnimalService;
import services.OrdonnanceService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author expert
 */
public class AfficherOrdonnanceController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Ordonnance> animatable;
    private TableColumn<Ordonnance, String> description;
    private TableColumn<Ordonnance, String> traitement;
    private TableColumn<Ordonnance, String> date;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;

    /**
     * Initializes the controller class.
     */
        OrdonnanceService ordonnanceService = new OrdonnanceService();
         ObservableList<Ordonnance> oList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> desc_col;
    @FXML
    private TableColumn<?, ?> trait_col;
    @FXML
    private TableColumn<?, ?> date_col;
    
      @FXML
         void add() throws IOException {
        Parent fmxlLoader = FXMLLoader.load(getClass().getResource("OrdonnnaceFXML.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fmxlLoader);

    }
         
    @FXML
                  public void catDelete() throws SQLException {
             
        Ordonnance cat=animatable.getSelectionModel().getSelectedItem();      
        int num = animatable.getSelectionModel().getSelectedIndex();
        if((num - 1)<-1){return;}
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cofirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to DELETE Categorie : " + cat.getDate() + "?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {

           ordonnanceService.supprimerOrdonnance(cat.getId());
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Deleted!");
            alert.showAndWait();
       
          
        }
    }
    @FXML
    public void aniimalUpdate() throws IOException {
        Ordonnance cat=animatable.getSelectionModel().getSelectedItem(); 
        int num = animatable.getSelectionModel().getSelectedIndex();
        if((num - 1)<-1){return;}
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierordonnanceFXML.fxml"));
        
        Parent root = loader.load();
     

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        
        TextField txt_id = (TextField) loader.getNamespace().get("txt_desc");
        TextField txt_desc = (TextField) loader.getNamespace().get("txt_desc");
        TextField txt_trait = (TextField) loader.getNamespace().get("txt_trait");
        DatePicker txt_date = (DatePicker) loader.getNamespace().get("txt_date");
        
       
        txt_id .setText(String.valueOf(cat.getId()));
        txt_desc.setText(cat.getDescription());
        txt_trait.setText(cat.getTraitement());
        txt_date.setValue(Instant.ofEpochMilli(cat.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
 
       
        
       

       
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            OrdonnanceService us = new OrdonnanceService();
            List<Ordonnance> ordonnances = us.afficherListeO();
            ObservableList<Ordonnance> list = FXCollections.observableArrayList(ordonnances);

            Connection con = MyDB.getInstance().getConx();
            ResultSet rs = con.createStatement().executeQuery("select * from ordonnance");

            while (rs.next()) {
                oList.add(new Ordonnance(
                        rs.getString("description"),
                        rs.getString("traitement"),
                        rs.getDate("date")
                        
                     ));
                       
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        loadDate();
        animatable.setItems(oList);
   
    }    

    
     private void loadDate() {
        desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        trait_col.setCellValueFactory(new PropertyValueFactory<>("traitement"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
       
       
    }
     
     
     
     
}
