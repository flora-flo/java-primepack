/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Comment;
import Entities.Post;
import Services.ServiceComment;
import java.net.URL;
import static java.nio.file.Files.list;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<Comment, Integer> tccomment;
    @FXML
    private TableColumn<Comment, Integer> tcpost;
    @FXML
    private TableColumn<Comment, Integer> tcmembre;
    @FXML
    private TableColumn<Comment, String> tctext;
    @FXML
    private TableColumn<Comment, Date> tcdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceComment sc = new ServiceComment();
         List<Comment> lc = sc.displayComments();
          ObservableList<Comment> obsList = FXCollections.observableArrayList(lc);
          tccomment.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("id"));
          tcpost.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("idPost"));
          tcmembre.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("idMembre"));
          tctext.setCellValueFactory(new PropertyValueFactory<Comment, String>("text"));
          tcdate.setCellValueFactory(new PropertyValueFactory<Comment, Date>("date"));
          tftableview.setItems(obsList);
    }    

     public void refreshtable()
    {
        ServiceComment sc = new ServiceComment();
         List<Comment> lc = sc.displayComments();
          ObservableList<Comment> obsList = FXCollections.observableArrayList(lc);
          tccomment.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("id"));
          tcpost.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("idPost"));
          tcmembre.setCellValueFactory(new PropertyValueFactory<Comment, Integer>("idMembre"));
          tctext.setCellValueFactory(new PropertyValueFactory<Comment, String>("text"));
          tcdate.setCellValueFactory(new PropertyValueFactory<Comment, Date>("date"));
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
        ServiceComment service=new ServiceComment();
        service.deleteComment(c.getId());
         refreshtable();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Comment deleted successfully");
        alert.showAndWait();
         }

    }
    
}
