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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class PostFrontController implements Initializable {

    @FXML
    private VBox postView;
    @FXML
    private ListView<Post> postList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicePost service = new ServicePost();
        // Get all posts from the back-end
        ObservableList<Post> listPosts = service.displayPosts();
        
        // Set the post list items to be the posts
         postList.setItems(listPosts);
         
         
         // set the cell factory to use custom cells for the posts
    postList.setCellFactory(param -> new ListCell<Post>() {
        private ImageView imageView = new ImageView();

        @Override
        protected void updateItem(Post post, boolean empty) {
            super.updateItem(post, empty);

            if (empty || post == null) {
                setText(null);
                setGraphic(null);
            } else {
                // set the post's image if it exists
                if (post.getImage() != null) {
                    imageView.setImage(new Image(new File(post.getImage()).toURI().toString()));
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);
                }

                // set the post's details in the list view cell
               setText("Theme :"+post.getTheme());
                
                setGraphic(imageView);
                
                // add an onMouseClicked event to redirect to the post's single page
        setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("singlePost.fxml"));
                Parent root = loader.load();

                // pass the selected post to the single post controller
                SinglePostController controller = loader.getController();
                controller.setPost(post);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
            }
        }
        
    });
   
    }    
    
}
