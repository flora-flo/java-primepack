/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Evenement;
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

public class EvenementService implements IService<Evenement> {

    private Connection con;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;

    public EvenementService() {
        con = DataSource.getInstance().getCnx();
    }

    @Override
    public boolean insert(Evenement evenement) {

        String sql = "INSERT INTO `evenement`( `nom`, `description`, `date`, `sponsor`, `nb_place`) VALUES (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, evenement.getNom());
            ps.setString(2, evenement.getDescription());
            ps.setTimestamp(3, evenement.getDate());
            ps.setString(4, evenement.getSponsor());

            ps.setInt(5, evenement.getNbPlace());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Evenement evenement) {
        String sql = "UPDATE `evenement` SET `nom`=?,`description`=?,`date`=?,`sponsor`=?,`nb_place`=? WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, evenement.getNom());
            ps.setString(2, evenement.getDescription());
            ps.setTimestamp(3, evenement.getDate());
            ps.setString(4, evenement.getSponsor());

            ps.setInt(5, evenement.getNbPlace());
            ps.setInt(6, evenement.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Evenement evenement) {
        String sql = "DELETE FROM `evenement` WHERE id=?";
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, evenement.getId());
            System.out.println("delete");
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Evenement> getAll() {
        String sql = "SELECT * FROM `evenement`";

        List<Evenement> list = new ArrayList<>();
        try {
            st = con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                Evenement evenement = new Evenement();
                evenement.setId(rs.getInt("id"));
                evenement.setDate(rs.getTimestamp("date"));
                evenement.setDescription(rs.getString("description"));
                evenement.setNbPlace(rs.getInt("nb_place"));
                evenement.setNom(rs.getString("nom"));
                evenement.setSponsor(rs.getString("sponsor"));

                list.add(evenement);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Evenement getOne(int id) {
        String sql = "SELECT * FROM `evenement` where id=" + id;
        Evenement evenement = new Evenement();
        System.out.println(sql);
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            evenement.setId(rs.getInt("id"));
            evenement.setDate(rs.getTimestamp("date"));
            evenement.setDescription(rs.getString("description"));
            evenement.setNbPlace(rs.getInt("nb_place"));
            evenement.setNom(rs.getString("nom"));
            evenement.setSponsor(rs.getString("sponsor"));

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evenement;
    }

}
