/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Post;
import Services.ServicePost;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class StatsController implements Initializable {

    @FXML
    private VBox statisticsBox;
    
      private List<Post> posts;
    @FXML
    private Button backButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ServicePost sp = new  ServicePost();
         posts = sp.displayPosts();
       Map<String, Integer> postCommentCounts = new HashMap<>();

       // Get number of comments for each post and store in a map
       for (Post post : posts) {
           int commentCount = sp.nbrComments(post.getId());
           System.out.println(commentCount);
           postCommentCounts.put(post.getTheme(), commentCount);
       }

          // Create a pie chart to display the statistics
            PieChart pieChart = new PieChart();
            pieChart.setTitle("Number of comments per post");

            // Add the data to the chart
            ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
            for (Map.Entry<String, Integer> entry : postCommentCounts.entrySet()) {
                String postName = entry.getKey();
                int commentCount = entry.getValue();
                data.add(new PieChart.Data(postName + " (" + commentCount + " comments)", commentCount));
            }
            pieChart.setData(data);

            // Add the chart to the statisticsBox
            statisticsBox.getChildren().add(pieChart);

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
