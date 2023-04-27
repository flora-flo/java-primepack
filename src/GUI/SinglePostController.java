package GUI;

import Entities.Comment;
import Entities.Membre;
import Entities.Post;
import Entities.Rating;
import Services.ServiceComment;
import Services.ServicePost;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;



public class SinglePostController implements Initializable {

    @FXML
    private Label titleLabel;
    @FXML
    private ImageView imageView;
    @FXML
    private Label contentLabel;

    private Post post;
    
    private Membre membre;
    @FXML
    private Label dateLabel;
    @FXML
    private TextArea commentTextArea;
    @FXML
    private Button commentButton;
    @FXML
    private VBox commentSection;

    private ServiceComment sc;
    private ServicePost sp;
    @FXML
    private Label themeLabel;
    @FXML
    private Label commentsLabel;
    @FXML
    private Button backButton;
    @FXML
    private Button likeButton;
    @FXML
    private Button dislikeButton;
    @FXML
    private Label likeLabel;
    @FXML
    private Label dislikeLabel;
    
    private int likeCount = 0;
    private int dislikeCount = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.sc = new ServiceComment();
        this.sp = new ServicePost();
       this.membre=new Membre();
    }

    public void setPost(Post post) {
        this.post = post;
        displayPost();
        displayComments(); 
        displayRates();
       
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
            if (comment.getPost().getId() == post.getId()) {
                Label commentLabel = new Label(comment.getText());
                Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(e -> {
                
                
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
            // delete the comment
           sc.deleteComment(comment.getId());
       //    this.post.removeComment(comment);
            displayComments();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setContentText("Comment deleted successfully");
              alert.showAndWait();  
              
        }
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
                editStage.getIcons().add(new Image("/image/logo.png"));
                editStage.setTitle("Update your comment");
                editStage.show();
                cancelButton.setOnAction(event -> editStage.close());
                saveButton.setOnAction(event -> {
                    String updatedComment = editCommentArea.getText();
                    if (updatedComment.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Comment cannot be empty");
                        alert.showAndWait();
                    } else {
                        
                        // Send HTTP request to Neutrino API to check for bad words
                     try {
                        URL url = new URL("https://neutrinoapi.net/bad-word-filter");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setRequestProperty("User-ID", "dhiaa");
                        connection.setRequestProperty("API-Key", "G0xqXJGgRKqnmp9antKSChgQnE1dIODmHJ3FfQLkl30nm06D");
                        
                        // Send request content
                        connection.setDoOutput(true);
                       OutputStream os = connection.getOutputStream();
                       os.write(("content=" + updatedComment).getBytes());
                       os.flush();
                       os.close();
                       
                        // Read API response
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String inputLine;
                        StringBuilder response = new StringBuilder();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                        // Check if content contains bad words
                        if (response.toString().contains("true")) {
                            // Handle bad word found
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Your comment contains inappropriate language and cannot be posted.");
                            alert.showAndWait();
                        }
                        else{
                        comment.setText(updatedComment);
                        sc.updateComment(comment);
                        
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Comment updated successfully");
                         alert.showAndWait();
                        editStage.close();
                        displayComments();
                    }
                         } catch (IOException ex) {
        System.out.println("Error checking for bad words: " + ex.getMessage());
    }
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
    String content = commentTextArea.getText();

    if (content.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Comment cannot be empty");
        alert.showAndWait();
    } else {
    // Send HTTP request to Neutrino API to check for bad words
    try {
        URL url = new URL("https://neutrinoapi.net/bad-word-filter");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-ID", "dhiaa");
        connection.setRequestProperty("API-Key", "G0xqXJGgRKqnmp9antKSChgQnE1dIODmHJ3FfQLkl30nm06D");
       
        
        // Send request content
         connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        os.write(("content=" + content).getBytes());
        os.flush();
        os.close();
        
        // Read API response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        
        // Check if content contains bad words
        if (response.toString().contains("true")) {
            // Handle bad word found
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Your comment contains inappropriate language and cannot be posted.");
            alert.showAndWait();
        } else {
            // Add comment to list of comments
            Comment c = new Comment();
            c.setText(content);
            c.setDate(new Date());
            c.setPost(this.post);
         
           // this.post.addComment(c);
            this.membre.setId(3);
            c.setMembre(this.membre);
            sc.addComment(c);
            commentTextArea.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Comment saved successfully");
            alert.showAndWait();
            displayComments();
        }
    } catch (IOException e) {
        System.out.println("Error checking for bad words: " + e.getMessage());
    }
}
}
    @FXML
    private void back(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("PostFront.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Post Front");
                    stage.setScene(scene);
                 
                    stage.show(); 
    }
    
   private void displayRates() {
    List<Rating> rates = sp.isRatedByUser(post.getId(), 3);
   // List<Rating> allRates = sp.rates(post.getId());
    boolean hasRated = false;
    
    for (Rating rate : rates) {
        if (rate.getRate() == 1) {
            likeButton.setStyle("-fx-background-color: blue;");
            dislikeButton.setDisable(true);
            hasRated = true;
        } else {
            dislikeButton.setStyle("-fx-background-color: blue;");
            likeButton.setDisable(true);
            hasRated = true;
        }
    }
    if (!hasRated) {
        likeButton.setStyle("");
        dislikeButton.setStyle("");
    }  
    likeLabel.setText(String.valueOf(sp.getLikeCount(post.getId())));
    dislikeLabel.setText(String.valueOf(sp.getDislikeCount(post.getId())));
}

    
@FXML
private void rater(ActionEvent event) {
    Date now = new Date();
    List<Rating> rates = sp.isRatedByUser(post.getId(), 3);
    boolean hasRated = false;
    for (Rating rate : rates) {
        if (rate.getRate() == 1 && event.getSource() == likeButton) {
            sp.deleteRate(post.getId(), 3);
            likeButton.setStyle("");
            dislikeButton.setDisable(false);
            displayRates();
            hasRated = true;
        } else if (rate.getRate() == -1 && event.getSource() == dislikeButton) {
            sp.deleteRate(post.getId(), 3);
            dislikeButton.setStyle("");
            likeButton.setDisable(false);
            displayRates();
            hasRated = true;
        }
    }

    if (!hasRated) {
        if (event.getSource() == likeButton) {
            sp.addRate(post.getId(), 3, 1, now);
            likeLabel.setText(String.valueOf(sp.getLikeCount(post.getId())));
            dislikeLabel.setText(String.valueOf(sp.getDislikeCount(post.getId())));
            likeButton.setStyle("-fx-background-color: blue;");
            dislikeButton.setDisable(true);
            likeButton.setOnAction(e -> {
                sp.deleteRate(post.getId(), 3);
                likeLabel.setText(String.valueOf(sp.getLikeCount(post.getId())));
                dislikeLabel.setText(String.valueOf(sp.getDislikeCount(post.getId())));
                likeButton.setStyle("");
                dislikeButton.setDisable(false);
                likeButton.setOnAction(this::rater);
                displayRates();
            });
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Thanks for rating this post.");
            alert.showAndWait();
        } else if (event.getSource() == dislikeButton) {
            sp.addRate(post.getId(), 3, -1, now);
            likeLabel.setText(String.valueOf(sp.getLikeCount(post.getId())));
            dislikeLabel.setText(String.valueOf(sp.getDislikeCount(post.getId())));
            dislikeButton.setStyle("-fx-background-color: blue;");
            likeButton.setDisable(true);
            dislikeButton.setOnAction(e -> {
                sp.deleteRate(post.getId(), 3);
                likeLabel.setText(String.valueOf(sp.getLikeCount(post.getId())));
                dislikeLabel.setText(String.valueOf(sp.getDislikeCount(post.getId())));
                dislikeButton.setStyle("");
                likeButton.setDisable(false);
                dislikeButton.setOnAction(this::rater);
                displayRates();
            });
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Thanks for rating this post.");
            alert.showAndWait();
        }
    }
}

   
    
}
