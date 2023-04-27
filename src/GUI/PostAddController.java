/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.Comment;
import Entities.Post;
import Services.ServicePost;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
public class PostAddController  {

    /**
     * Initializes the controller class.
     */
      Post post = new Post();
  

    @FXML
    private TextField themeField;
    

    @FXML
    private TextArea contentArea;

    @FXML
    private Button chooseImageButton;

    private File imageFile;


    
    @FXML
    private Button saveButton;

    private ServicePost postService;
    @FXML
    private VBox postView;
    @FXML
    private TextField imageTextField;
    @FXML
    private Button backButton;

    public void initialize() {
        postService = new ServicePost();
    }

    @FXML
    public void chooseImage() {
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
    public void savePost(javafx.event.ActionEvent event) throws IOException {
        
        String content = contentArea.getText();
        String theme = themeField.getText();
        
        /*LocalDate localDate = datePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);*/
        

      
        
        if (theme.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter theme");
            alert.showAndWait();
            return;
        }
        if (content.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter content");
            alert.showAndWait();
            return;
        }
        if (imageFile==null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter image");
            alert.showAndWait();
            return;
        }
        if( content.length()<20 ){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter a content greater than 20 caracters");
            alert.showAndWait();
            return;
        }
      //  Post post = new Post(theme,nom, content);
      
        if (imageFile != null) {
    try {
        String imagePath = imageFile.getAbsolutePath();
        post.setImage(imagePath);
    } catch (Exception ex) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Failed to read image file");
        alert.showAndWait();
    }
}

        //  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
          Date now = new Date(); 
         
          //String date= dtf.format(now);
          post.setDate_Creation(now);
          post.setTheme(theme);
      
          post.setContenu(content);
      
        postService.addPost(post);

        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Post saved successfully");
        alert.showAndWait();

      
        themeField.clear();
        contentArea.clear();
       // datePicker.setValue(null);
        imageFile = null;
         FXMLLoader loader = new FXMLLoader(getClass().getResource("PostBack.fxml"));
         Parent root = loader.load();
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setTitle("Show Post");
         stage.setScene(scene);
         stage.show(); 

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
