/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Rendezvous;
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
public class RendezvousService implements IRendezvous<Rendezvous>{

    public Connection conx;
    public Statement stm;
    
    public RendezvousService() {
        conx = MyDB.getInstance().getConx();
    }

    @Override
    public void ajoutRendezvous(Rendezvous r) throws SQLException{
        String req="INSERT INTO `rendez_vous`(`duree`, `date`) "+" VALUES ('"+r.getDuree()+"','"+r.getDate()+"')";
        
        stm = conx.createStatement();
        stm.executeUpdate(req);
        System.out.println("rendezvous ajoutée");
    }

   
    @Override
    public List<Rendezvous> afficherListeR() throws SQLException{
        String req= "SELECT * FROM `rendezvous`";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        //System.out.println("rs: "+rs.toString());
        List<Rendezvous> Rendezvouses = new ArrayList<>();
        while(rs.next()){
           Rendezvous r = new Rendezvous(rs.getInt("id"), rs.getInt(2), rs.getDate("date"));
           Rendezvouses.add(r);
        }
        return Rendezvouses;
    }
}


