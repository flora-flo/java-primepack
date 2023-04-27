/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Comment;
import Entities.Membre;
import Entities.Post;

import Interfaces.InterfaceComment;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class ServiceComment implements InterfaceComment{
    
    Connection cnx;
    
     public ServiceComment() {
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public void addComment(Comment c) {
      try {
            PreparedStatement stm = cnx.prepareStatement("insert into commentaire (actualite_id, membre_id, text, date) values (?,?,?,?)");
            
          
            stm.setInt(1, c.getPost().getId());
            stm.setInt(2, c.getMembre().getId());
            stm.setString(3, c.getText());
            stm.setDate(4,  new java.sql.Date(c.getDate().getTime()));
           
            stm.executeUpdate();
            
            System.out.println("Commentaire ajouté avec succés!!!");
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @Override
    public void deleteComment(int id) {
            
     try
    { 
      Statement st = cnx.createStatement();
      String req = "DELETE FROM commentaire WHERE id = "+id+"";
                st.executeUpdate(req);      
      System.out.println("Commentaire supprimé avec succès...");
    } 
     catch (SQLException ex) {
       System.out.println(ex.getMessage());        
       }
        
    }

    @Override
    public void updateComment(Comment c) {
       
           try {
            String req = "update commentaire set text=? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setString(1, c.getText());
          
            ps.setInt(2, (int) c.getId());
          
            ps.executeUpdate();
            System.out.println("Commentaire modifié avec succés!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public List<Comment> displayComments() {
       
         List<Comment> cmnts = new ArrayList<>();
       try {
            String req = "select * from commentaire ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt(1));
                Post post = new Post();
                post.setId(rs.getInt(2));
                c.setPost(post);
                Membre membre = new Membre();
                membre.setId(rs.getInt(3));
                c.setMembre(membre);
                c.setText(rs.getString("Text"));
            
                c.setDate(rs.getDate("Date"));
               
                cmnts.add(c);
            }
            System.out.print(cmnts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cmnts;
        
    }
    
     public List<Comment> searchByComment(String query) {
    List<Comment> searchResults = new ArrayList<>();
    try {
        PreparedStatement ps = cnx.prepareStatement("SELECT * FROM commentaire WHERE LOWER(text) LIKE ?");
        ps.setString(1, "%" + query.toLowerCase() + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Comment c = new Comment();
                c.setId(rs.getInt(1));
                Post post = new Post();
                post.setId(rs.getInt(2));
                c.setPost(post);
                Membre membre = new Membre();
                membre.setId(rs.getInt(3));
                c.setMembre(membre);
                c.setText(rs.getString("Text"));
            
                c.setDate(rs.getDate("Date"));
            searchResults.add(c);
          //  System.out.println(searchResults);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return searchResults;
}

    
}
