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
          
        
        Post p = new Post("modernPost","llllllllllllllllllllmm","tryhhrrty",date);
        
      // MyConnection con = MyConnection.getInstance();
       
     ServicePost sp = new ServicePost();
      // sp.addPost(p);
   // sp.displayPosts();
    // sp.deletePost(7);

      Post p1 = new Post(6,"Desktop_App","CodingWithFx","tryhhrrty",date);
     // sp.updatePost(p1);
    
   
       Comment c = new Comment(1,"randomComment",date);
       Comment c1 = new Comment(12,"yyyy");
     //ServiceComment sc = new ServiceComment();
   //   sc.addComment(c);
     //  sc.deleteComment(16);
   // sc.updateComment(c1);
     // sc.displayComments();
  
    }
    
}
