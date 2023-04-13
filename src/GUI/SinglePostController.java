package GUI;

import Entities.Comment;
import Entities.Post;
import Services.ServiceComment;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SinglePostController implements Initializable {

    @FXML
    private Label titleLabel;
    @FXML
    private ImageView imageView;
    @FXML
    private Label contentLabel;

    private Post post;
    @FXML
    private Label dateLabel;
    @FXML
    private TextArea commentTextArea;
    @FXML
    private Button commentButton;
    @FXML
    private VBox commentSection;

    private ServiceComment sc;
    private Label themeLabel;
    @FXML
    private Label commentsLabel;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.sc = new ServiceComment();
       
    }

    public void setPost(Post post) {
        this.post = post;
        displayPost();
        displayComments();
    }

    private void displayPost() {
        titleLabel.setText(post.getTheme());
        if (post.getImage() != null) {
            imageView.setImage(new Image(new File(post.getImage()).toURI().toString()));
        }
        contentLabel.setText(post.getContenu());
        dateLabel.setText(post.getDate_Creation().toString());
    }

    private void displayComments() {
        commentSection.getChildren().clear();
        List<Comment> comments = sc.displayComments();
        for (Comment comment : comments) {
            if (comment.getIdPost() == post.getId()) {
                Label commentLabel = new Label(comment.getText());
                Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(e -> {
                sc.deleteComment(comment.getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setContentText("Comment deleted successfully");
              alert.showAndWait();
                displayComments();
            });
                 Button updateButton = new Button("Update");
            updateButton.setOnAction(e -> {
                TextArea editCommentArea = new TextArea(comment.getText());
                Button saveButton = new Button("Save");
                Button cancelButton = new Button("Cancel");
                HBox buttonBox = new HBox(saveButton, cancelButton);
                buttonBox.setSpacing(5);
                VBox editBox = new VBox(editCommentArea, buttonBox);
                editBox.setSpacing(10);
                Stage editStage = new Stage();
                editStage.setScene(new Scene(editBox));
                editStage.show();
                cancelButton.setOnAction(event -> editStage.close());
                saveButton.setOnAction(event -> {
                    String updatedComment = editCommentArea.getText();
                    if (updatedComment.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Comment cannot be empty");
                        alert.showAndWait();
                    } else {
                        comment.setText(updatedComment);
                        sc.updateComment(comment);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Comment updated successfully");
                         alert.showAndWait();
                        editStage.close();
                        displayComments();
                    }
                });
            });
             HBox buttonBox = new HBox(deleteButton, updateButton);
            buttonBox.setSpacing(5);
               VBox commentBox = new VBox(commentLabel, buttonBox);
            commentBox.setSpacing(5);
            commentSection.getChildren().add(commentBox);
            }
        }
    }

    @FXML
    private void addComment() {
        String comment = commentTextArea.getText();
        
        if (comment.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setContentText("Comment cannot be empty");
              alert.showAndWait();
        }
        else{
            Comment c = new Comment();
        c.setText(comment);
        c.setDate(new Date());
        c.setIdPost(post.getId());
       c.setIdMembre(1);
            sc.addComment(c);
            commentTextArea.clear();
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setContentText("Comment saved successfully");
              alert.showAndWait();
              displayComments();
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("PostFront.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Show Post");
                    stage.setScene(scene);
                    stage.show();
    }

}
