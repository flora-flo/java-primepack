/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Maladie;
import entites.Operation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author Nessim Melliti
 */
public class OperationService implements IService<Operation> {
    
    private Connection con;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public OperationService() {
        con = DataSource.getInstance().getCnx();
    }
    
    @Override
    public boolean insert(Operation t) {
        String sql = "INSERT INTO `operation`( `maladie_id`, `animal_id`, `date_operation`, `type_operation`, `nom_medecin`, `cout_operation`, `note_operation`) VALUES (?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, t.getMaladie().getId());
            ps.setInt(2, 1); /// change integration animale id
            ps.setTimestamp(3, t.getDateOperation());
            ps.setString(4, t.getTypeOperation());
            
            ps.setString(5, t.getNomMedecin());
            ps.setFloat(6, t.getCoutOperation());
            ps.setString(7, t.getNoteOperation());
            return ps.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(OperationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public boolean update(Operation t) {
        String sql = "UPDATE `operation` SET `maladie_id`=?,`animal_id`=?,`date_operation`=?,`type_operation`=?,`nom_medecin`=?,`cout_operation`=?,`note_operation`=? WHERE `id`=?";
        try {
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, t.getMaladie().getId());
            ps.setInt(2, t.getAnimaleId()); /// change integration animale id
            ps.setTimestamp(3, t.getDateOperation());
            ps.setString(4, t.getTypeOperation());
            
            ps.setString(5, t.getNomMedecin());
            ps.setFloat(6, t.getCoutOperation());
            ps.setString(7, t.getNoteOperation());
            ps.setInt(8, t.getId());
            return ps.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(OperationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public boolean delete(Operation t) {
        String sql = "DELETE FROM `operation` WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, t.getId());
            return ps.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(OperationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public List<Operation> getAll() {
        String sql = "SELECT * FROM `operation` INNER JOIN maladie on operation.maladie_id = maladie.id;";
        
        List<Operation> list = new ArrayList<>();
        try {
            st = con.createStatement();
            
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                Maladie maladie = new Maladie();
                maladie.setId(rs.getInt("maladie_id"));
                maladie.setAnimale_id(rs.getInt("animal_id"));
                maladie.setNom(rs.getString("nom"));
                maladie.setDescription(rs.getString("description"));
                maladie.setTypeAnimale(rs.getString("type_aniaml"));
                maladie.setDateCreation(rs.getTimestamp("date_creation"));
                maladie.setDateMaj(rs.getTimestamp("date_maj"));
                
                Operation operation = new Operation();
                operation.setMaladie(maladie);
                operation.setId(rs.getInt("id"));
                operation.setAnimaleId(rs.getInt("animal_id"));
                operation.setDateOperation(rs.getTimestamp("date_operation"));
                operation.setTypeOperation(rs.getString("type_operation"));
                operation.setNomMedecin(rs.getString("nom_medecin"));
                operation.setCoutOperation(rs.getFloat("cout_operation"));
                operation.setNoteOperation(rs.getString("note_operation"));
                
                list.add(operation);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OperationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @Override
    public Operation getOne(int id) {
        String sql = "SELECT * FROM `operation` INNER JOIN maladie on operation.maladie_id = maladie.id where id = ;" + id;
        Operation operation = new Operation();
        
        List<Operation> list = new ArrayList<>();
        try {
            st = con.createStatement();
            
            rs = st.executeQuery(sql);
            
            rs.next();
            Maladie maladie = new Maladie();
            maladie.setId(rs.getInt("maladie_id"));
            maladie.setAnimale_id(rs.getInt("animal_id"));
            maladie.setNom(rs.getString("nom"));
            maladie.setDescription(rs.getString("description"));
            maladie.setTypeAnimale(rs.getString("type_aniaml"));
            maladie.setDateCreation(rs.getTimestamp("date_creation"));
            maladie.setDateMaj(rs.getTimestamp("date_maj"));
            
            operation.setMaladie(maladie);
            operation.setId(rs.getInt("id"));
            operation.setAnimaleId(rs.getInt("animal_id"));
            operation.setDateOperation(rs.getTimestamp("date_operation"));
            operation.setTypeOperation(rs.getString("type_operation"));
            operation.setNomMedecin(rs.getString("nom_medecin"));
            operation.setCoutOperation(rs.getFloat("cout_operation"));
            operation.setNoteOperation(rs.getString("note_operation"));
            
            list.add(operation);
            
        } catch (SQLException ex) {
            Logger.getLogger(OperationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return operation;
    }
    
}
