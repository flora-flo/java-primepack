/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Desktop_App;
import Entities.Comment;
import Entities.Post;
import Services.ServiceComment;
import Services.ServicePost;
import Utils.MyConnection;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author MSI
 */
public class Desktop_App{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Date date = new Date();  
          
        
     //   Post p = new Post("modernPost","dev","llllllllllllllllllllmm","tryhhrrty",date);
        
      // MyConnection con = MyConnection.getInstance();
       
     ServicePost sp = new ServicePost();
    // System.out.println(sp.comments(56)); 
       //sp.addPost(p);
   // sp.displayPosts();
    // sp.deletePost(53);

     // Post p1 = new Post(52,"Desktop_App","JDBC","CodingWithFx","tryhhrrty",date);
     // sp.updatePost(p1);
    
    
       //Comment c = new Comment(4,36,"randomComment",date);
       Comment c1 = new Comment(108,"yyyy");
//     ServiceComment sc = new ServiceComment();
  //    sc.addComment(c);
       //sc.deleteComment(112);
  //  sc.updateComment(c1);
      //sc.displayComments();
  
    }
    
}
