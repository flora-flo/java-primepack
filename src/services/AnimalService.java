/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Animal;
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
public class AnimalService implements IAnimal<Animal>{

    public Connection conx;
    public Statement stm;
    
    public AnimalService() {
        conx = MyDB.getInstance().getConx();
    }

    @Override
    public void ajoutAnimal(Animal a ) throws SQLException{
        String req="INSERT INTO animal(nom, race,genre,age,description)"
                + " VALUES ('"+a.getNom()+"','"+a.getRace()+"','"+a.getGenre()+"','"+a.getAge()+"','"+a.getDescription()+"')";
        
        stm = conx.createStatement();
        stm.executeUpdate(req);
        System.out.println("Animal ajoutée");
    }

//    @Override
//    public void ajoutAnimale(Animal a) throws SQLException{
//        String req="INSERT INTO `animal`(`nom`, `race`,`genre`,`age``description`) VALUES (?,?,?,?,?)";
//        
//        PreparedStatement ps = conx.prepareStatement(req);
//        ps.setString(1, a.getNom());
//        ps.setString(2,a.getRace());
//        ps.setString(3, a.getGenre());
//        ps.setString(4, a.getAge());
//        ps.setString(5, a.getDescription());
//        ps.executeUpdate();
//        System.out.println("animal ajoutée");
//
//    }
//
    @Override
    public List<Animal> afficherListeA() throws SQLException{
        String req= "SELECT * FROM `animal`";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        //System.out.println("rs: "+rs.toString());
        List<Animal> animals = new ArrayList<>();
        while(rs.next()){
           Animal a = new Animal(rs.getInt("id"), rs.getString("nom"), rs.getString("race"),rs.getString("genre"),rs.getString("age"),rs.getString("description"));
           animals.add(a);
        }
        return animals;
    }

      @Override
    public void supprimerAnimal(int id) {
 try {
            String sql = "DELETE FROM Animal WHERE id="+id+"";
            PreparedStatement ste  = conx.prepareStatement(sql);
           
            ste.executeUpdate();
            System.out.println("Animal Supprimée ");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    
    
    }

  public void modifier(int id, Animal a) throws SQLException {
        try {
          

            PreparedStatement st;
            st = conx.prepareStatement("UPDATE `animal` SET `nom`=?,`race`=?, `genre`=?,`age`=?,`description`=? WHERE id=?") ;
            st.setString(1, a.getNom());
            st.setString(2, a.getRace());
            st.setString(3, a.getGenre());
            st.setString(4, a.getAge());
            st.setString(5,a.getDescription());
            st.setInt(6,id);
          
            if (st.executeUpdate() == 1) {
                System.out.println("animal modifier avec success");
            } else {
                System.out.println("animal n'existe pas");
            }
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }

    }

    
   
}