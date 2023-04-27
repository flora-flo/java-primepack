/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Post;
import Services.ServicePost;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class EditPostController implements Initializable {

    @FXML
    private VBox postView;
   
    @FXML
    private TextField themeField;
    @FXML
    private TextArea contentArea;
    @FXML
    private Button saveButton;
    
    private ServicePost postService;
    
    private Post selectedPost;
    @FXML
    private Button chooseImageButton;
    @FXML
    private TextField imageTextField;
    
    private File imageFile;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        postService = new ServicePost();
         
    }    


    public void setSelectedPost(Post post) {
        this.selectedPost = post;
         
        contentArea.setText(selectedPost.getContenu());
        themeField.setText(selectedPost.getTheme());
        imageTextField.setText(selectedPost.getImage());
    }
    
    @FXML
     public void updatePost(javafx.event.ActionEvent event) throws IOException {
        
       
       
                
                String content = contentArea.getText();
                String theme = themeField.getText();
        
            if ( content.isEmpty() || theme.isEmpty()  ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter content, theme ");
            alert.showAndWait();
            return;
            }
        if( content.length()<20 ){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter a content greater than 20 caracters");
            alert.showAndWait();
            return;
        }
         if (imageFile != null) {
        try {
            String imagePath = imageFile.getAbsolutePath();
            selectedPost.setImage(imagePath);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Failed to read image file");
            alert.showAndWait();
        }
    }
                  selectedPost.setTheme(theme);
               
                  selectedPost.setContenu(content);
                  
                  postService.updatePost(selectedPost);
                  
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Post updated successfully");
                    alert.showAndWait();
                    
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("PostBack.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Show Post");
                    stage.setScene(scene);
                    stage.show(); 
              
             
    }

    @FXML
    private void chooseImage(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
      
        if (file != null) {
            imageFile = file;
            imageTextField.setText(file.getName());
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
         
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("PostBack.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Show Post");
                    stage.setScene(scene);
                    stage.show(); 
    }
    
    
}
