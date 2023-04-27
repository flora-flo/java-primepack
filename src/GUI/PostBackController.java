/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Post;
import Entities.Rating;
import Services.ServicePost;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class PostBackController implements Initializable {

   
    @FXML
    private TableView<Post> tftableview;
   
    @FXML
    private TableColumn<Post, String> tctheme;
   
    @FXML
    private TableColumn<Post, Date> tcdate;
    @FXML
    private TableColumn<Post, String> tccontenu;
    



    private File imageFile;
    private TextField nomField;
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
    @FXML
    private Button tfadd1;
    @FXML
    private TableColumn<Post, Integer> tclike;
    @FXML
    private TableColumn<Post, Integer> tcdislike;
    @FXML
    private TextField searchField;
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ServicePost service = new ServicePost();
         ObservableList<Post> list = service.displayPosts();
          //System.out.print(list);
         
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
           // tccmnts.setCellValueFactory(new PropertyValueFactory<Post, Integer>("commentCount"));
         /* tclike.setCellValueFactory(new Callback<CellDataFeatures<Post, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(CellDataFeatures<Post, Integer> param) {
                int postId = param.getValue().getId();
                int likeCount = service.getLikeCount(postId);
                return new SimpleIntegerProperty(likeCount).asObject();
            }
        });

        tcdislike.setCellValueFactory(new Callback<CellDataFeatures<Post, Integer>, ObservableValue<Integer>>() {
           @Override
           public ObservableValue<Integer> call(CellDataFeatures<Post, Integer> param) {
               int postId = param.getValue().getId();
               int dislikeCount = service.getDislikeCount(postId);
               return new SimpleIntegerProperty(dislikeCount).asObject();
           }
       });   
           */
        // add an event listener to the search field
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        searchPosts();
    });
    
           
          tftableview.setItems(list);
    }    
       
     public void refreshtable()
    {
        ServicePost service=new ServicePost();
         ObservableList<Post> list = service.displayPosts();
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
           //  tccmnts.setCellValueFactory(new PropertyValueFactory<Post, Integer>("commentCount"));
           /*  tclike.setCellValueFactory(new Callback<CellDataFeatures<Post, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(CellDataFeatures<Post, Integer> param) {
                    int postId = param.getValue().getId();
                    int likeCount = service.getLikeCount(postId);
                    return new SimpleIntegerProperty(likeCount).asObject();
                }
            });

               tcdislike.setCellValueFactory(new Callback<CellDataFeatures<Post, Integer>, ObservableValue<Integer>>() {
                 @Override
                 public ObservableValue<Integer> call(CellDataFeatures<Post, Integer> param) {
                     int postId = param.getValue().getId();
                     int dislikeCount = service.getDislikeCount(postId);
                     return new SimpleIntegerProperty(dislikeCount).asObject();
                 }
             });   

             */  
               // add an event listener to the search field
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        searchPosts();
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
          ServicePost service = new ServicePost();
          Dialog<ButtonType> confirmationDialog = new Dialog<>();
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Are you sure to delete this post?");

        // set the graphic for the confirmation dialog window
        Stage stage = (Stage) confirmationDialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/image/logo.png"));

        ButtonType confirmButton = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmationDialog.getDialogPane().getButtonTypes().setAll(confirmButton, cancelButton);

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.isPresent() && result.get() == confirmButton) {
            // delete the post
            service.deletePost(p.getId());
            refreshtable();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Post deleted successfully");
            alert.showAndWait();
        }

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

    @FXML
    private void displayStats(javafx.event.ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Stats.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Stats Post");
            stage.setScene(scene);
            stage.show();
    }
   
    
    public void searchPosts() {
         ServicePost service=new ServicePost();
    String query = searchField.getText();
    List<Post> searchResults = service.searchByTitle(query);
    tftableview.setItems(FXCollections.observableArrayList(searchResults));
}
}   

