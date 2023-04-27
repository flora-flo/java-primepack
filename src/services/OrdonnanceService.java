/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Animal;
import entites.Ordonnance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Ennou
 */
public class OrdonnanceService implements IOrdonnance<Ordonnance>{

    public Connection conx;
    public Statement stm;
    
    public OrdonnanceService() {
        conx = MyDB.getInstance().getConx();
    }

    @Override
    public void ajoutOrdonnance(Ordonnance o ) throws SQLException{
        String req="INSERT INTO ordonnance(description, traitement,date)"+" VALUES ('"+o.getDescription()+"','"+o.getTraitement()+"','"+o.getDate()+"')";
        
        stm = conx.createStatement();
        stm.executeUpdate(req);
        System.out.println("ordonnace ajoutée");
    }
 
    
    
    
    
   
//    @Override
//    public void ajoutOrdonnancee(Ordonnance o) throws SQLException{
//        String req="INSERT INTO `ordonnace`(`description`, `traitement`,'date') VALUES (?,?,?)";
//        
//        PreparedStatement ps = conx.prepareStatement(req);
//        ps.setString(1, o.getDescription());
//        ps.setString(2,o.getTraitement());
//        ps.setDate(3,o.getDate());
//        
//        ps.executeUpdate();
//        System.out.println("animal ajoutée");
//
//    }
//
   @Override
    public List<Ordonnance> afficherListeO() throws SQLException{
        String req= "SELECT * FROM `ordonnance`";
        stm = conx.createStatement();
       ResultSet rs = stm.executeQuery(req);
      
      List<Ordonnance> ordonnances = new ArrayList<>();
       while(rs.next()){
           Ordonnance o = new Ordonnance(rs.getInt("id"), rs.getString("description"), rs.getString("traitement"),rs.getDate("date"));
          ordonnances.add(o);
       }
       return ordonnances;
   }
 
      @Override
    public void supprimerOrdonnance(int id) {
 try {
            String sql = "DELETE FROM Ordonnance WHERE id="+id+"";
            PreparedStatement ste  = conx.prepareStatement(sql);
           
            ste.executeUpdate();
            System.out.println("ordonnance Supprimée ");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    
    
    }
      public void modifier(int id,Ordonnance o) throws SQLException {
        try {
          

            PreparedStatement st;
            st = conx.prepareStatement("UPDATE `ordonnance` SET `description`=?,`traitement`=?, `date`=? WHERE id=?") ;
            st.setString(1, o.getDescription());
            st.setString(2, o.getTraitement());
            st.setDate(3,new java.sql.Date(o.getDate().getTime()));   
            st.setInt(4,id);
            
           
            
          
            if (st.executeUpdate() == 1) {
                System.out.println("Ordonnance modifier avec success");
            } else {
                System.out.println("Ordonnannce n'existe pas");
            }
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }

    }


}

   
