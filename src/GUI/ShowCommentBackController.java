/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Comment;
import Entities.Post;
import Services.ServiceComment;
import Services.ServicePost;
import java.net.URL;
import static java.nio.file.Files.list;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ShowCommentBackController implements Initializable {

    @FXML
    private Button tfremove;
    @FXML
    private TableView<Comment> tftableview;
   
    
    @FXML
    private TableColumn<Comment, String> tctext;
    @FXML
    private TableColumn<Comment, Date> tcdate;
    @FXML
    private TextField searchField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceComment sc = new ServiceComment();
         List<Comment> lc = sc.displayComments();
          ObservableList<Comment> obsList = FXCollections.observableArrayList(lc);
                    
          tctext.setCellValueFactory(new PropertyValueFactory<Comment, String>("text"));
          tcdate.setCellValueFactory(new PropertyValueFactory<Comment, Date>("date"));
          
           // add an event listener to the search field
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
       searchComments();
    });
          tftableview.setItems(obsList);
    }    

     public void refreshtable()
    {
        ServiceComment sc = new ServiceComment();
         List<Comment> lc = sc.displayComments();
          ObservableList<Comment> obsList = FXCollections.observableArrayList(lc);
         
        
          tctext.setCellValueFactory(new PropertyValueFactory<Comment, String>("text"));
          tcdate.setCellValueFactory(new PropertyValueFactory<Comment, Date>("date"));
          
           // add an event listener to the search field
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
       searchComments();
    });
          tftableview.setItems(obsList);
    }
    
    @FXML
    private void remove(ActionEvent event) {
         Comment c=tftableview.getSelectionModel().getSelectedItem();
           
        if ( c== null) {
        // show an error message if no post is selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Please select a comment");
        alert.showAndWait();
        return;
    }
        else{
          ServiceComment service = new ServiceComment();
          Dialog<ButtonType> confirmationDialog = new Dialog<>();
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Are you sure to delete this comment?");

        // set the graphic for the confirmation dialog window
        Stage stage = (Stage) confirmationDialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/image/logo.png"));

        ButtonType confirmButton = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmationDialog.getDialogPane().getButtonTypes().setAll(confirmButton, cancelButton);

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.isPresent() && result.get() == confirmButton) {
            // delete the post
            service.deleteComment(c.getId());
            refreshtable();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Comment deleted successfully");
            alert.showAndWait();
        }

    }
    
}
     public void searchComments() {
    ServiceComment service=new ServiceComment();
    String query = searchField.getText();
    List<Comment> searchResults = service.searchByComment(query);
    tftableview.setItems(FXCollections.observableArrayList(searchResults));
}

}