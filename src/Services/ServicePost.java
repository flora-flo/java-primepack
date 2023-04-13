/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Post;
import Interfaces.InterfacePost;
import Utils.MyConnection;
//import com.mysql.cj.xdevapi.Statement;
import java.util.List;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MSI
 */
public class ServicePost implements InterfacePost{
    
    Connection cnx;
    
     public ServicePost() {
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public void addPost(Post p) {
        try {
            PreparedStatement stm = cnx.prepareStatement("insert into actualite (theme, contenu, image,date_creation) values (?,?,?,?)");
            
          
            stm.setString(1, p.getTheme());
            stm.setString(2, p.getContenu());
            stm.setString(3, p.getImage());
            stm.setDate(4, new java.sql.Date(p.getDate_Creation().getTime()) );
                
            stm.executeUpdate();
            
            System.out.println("Post ajouté avec succés!!!");
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deletePost(int id) {   
        
     try
    { 
      Statement st = cnx.createStatement();
      String req = "DELETE FROM actualite WHERE id = "+id+"";
                st.executeUpdate(req);      
      System.out.println("post supprimé avec succès...");
    } 
     catch (SQLException ex) {
       System.out.println(ex.getMessage());        
       }
    
    }

    @Override
    public void updatePost(Post p) {
        
        try {
            String req = "update actualite set theme=?,image=?,contenu=?,date_creation=? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setString(1, p.getTheme());
            ps.setString(2, p.getImage());
            ps.setString(3, p.getContenu());
           ps.setDate(4, new java.sql.Date(p.getDate_Creation().getTime()) );
            ps.setInt(5, (int) p.getId());
          
            ps.executeUpdate();
            System.out.println("Post modifié avec succés!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public ObservableList<Post> displayPosts() {
       ObservableList<Post> posts = FXCollections.observableArrayList();
       try {
            String req = "select * from actualite ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt(1));
                p.setTheme(rs.getString("theme"));
                p.setImage(rs.getString("image"));
                p.setContenu(rs.getString("Contenu"));
                p.setDate_Creation(rs.getDate("date_creation"));
                posts.add(p);
            }
            System.out.print(posts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;
    }
    
}
