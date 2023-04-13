/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Post;
import Services.ServicePost;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class PostBackController implements Initializable {

   
    @FXML
    private TableView<Post> tftableview;
    @FXML
    private TableColumn<Post, Integer> tcpost;
    @FXML
    private TableColumn<Post, String> tctheme;
    @FXML
    private TableColumn<Post, Date> tcdate;
    @FXML
    private TableColumn<Post, String> tccontenu;
    



    private File imageFile;
    private TextField themeField;
    private TextArea contentArea;
    @FXML
    private Button tfremove;
    @FXML
    private Button tfupdate;
    @FXML
    private TableColumn<Post, String> images;
    @FXML
    private Button tfadd;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ServicePost service = new ServicePost();
         ObservableList<Post> list = service.displayPosts();
          //System.out.print(list);
          tcpost.setCellValueFactory(new PropertyValueFactory<Post, Integer>("id"));
          tctheme.setCellValueFactory(new PropertyValueFactory<Post, String>("Theme"));
          tcdate.setCellValueFactory(new PropertyValueFactory<Post, Date>("date_Creation"));
          tccontenu.setCellValueFactory(new PropertyValueFactory<Post, String>("Contenu"));
          images.setCellValueFactory(new PropertyValueFactory<Post, String>("image"));
            images.setCellFactory(param -> {
                return new TableCell<Post, String>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setGraphic(null);
                        } else {
                            try {
                                // Load the image from the file path
                                
                                Image image = new Image(new File(item).toURI().toString());
                                imageView.setImage(image);
                                imageView.setFitHeight(100);
                                imageView.setFitWidth(100);
                                setGraphic(imageView);
                            
                                
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
            });

          tftableview.setItems(list);
    }    
       
     public void refreshtable()
    {
        ServicePost service=new ServicePost();
         ObservableList<Post> list = service.displayPosts();
         tcpost.setCellValueFactory(new PropertyValueFactory<Post, Integer>("id"));
          tctheme.setCellValueFactory(new PropertyValueFactory<Post, String>("Theme"));
         
          tcdate.setCellValueFactory(new PropertyValueFactory<Post, Date>("date_Creation"));
          tccontenu.setCellValueFactory(new PropertyValueFactory<Post, String>("Contenu"));
          images.setCellValueFactory(new PropertyValueFactory<Post, String>("image"));
            images.setCellFactory(param -> {
                return new TableCell<Post, String>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setGraphic(null);
                        } else {
                            try {
                                // Load the image from the file path
                                Image image = new Image(new File(item).toURI().toString());
                                imageView.setImage(image);
                                imageView.setFitHeight(100);
                                imageView.setFitWidth(100);
                                setGraphic(imageView);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
            });
          tftableview.setItems(list);
    }

    @FXML
    public void remove(javafx.event.ActionEvent event) {
         Post p=tftableview.getSelectionModel().getSelectedItem();
           
        if ( p== null) {
        // show an error message if no post is selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Please select a post");
        alert.showAndWait();
        return;
    }
        else{
        ServicePost service=new ServicePost();
        service.deletePost(p.getId());
         refreshtable();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Post deleted successfully");
        alert.showAndWait();
         }
    }
    @FXML
   public void redirectToUpdate(javafx.event.ActionEvent event) throws IOException {
       
        if ( tftableview.getSelectionModel().getSelectedItem()== null) {
        // show an error message if no post is selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Please select a post");
        alert.showAndWait();
        return;
    }
        
        else{  FXMLLoader loader = new FXMLLoader(getClass().getResource("EditPost.fxml"));
            Parent root = loader.load();
            EditPostController editController = loader.getController();
            editController.setSelectedPost(tftableview.getSelectionModel().getSelectedItem());
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Edit Post");
            stage.setScene(scene);
            stage.show();
}
   
    
        
   }

    @FXML
    private void redirectToAdd(javafx.event.ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PostAdd.fxml"));
            Parent root = loader.load();
           
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Add Post");
            stage.setScene(scene);
            stage.show();
        
    }
   
}   

