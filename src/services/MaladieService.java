/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Maladie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author Nessim Melliti
 */
public class MaladieService implements IService<Maladie> {

    private Connection con;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;

    public MaladieService() {
        con = DataSource.getInstance().getCnx();
    }

    @Override
    public boolean insert(Maladie t) {
        String sql = "INSERT INTO `maladie`( `animal_id`, `nom`, `description`, `type_aniaml`, `date_creation`, date_maj) VALUES (?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, 1); /// change integration animale id
            ps.setString(2, t.getNom());
            ps.setString(3, t.getDescription());
            ps.setString(4, t.getTypeAnimale());

            ps.setTimestamp(5, t.getDateCreation());
            ps.setNull(6, 0);

            System.out.println(ps);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(OperationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Maladie t) {
        String sql = "UPDATE `maladie` SET `animal_id`=?,`nom`=?,`description`=?,`type_aniaml`=?, `date_maj`=? WHERE `id`=?";
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, t.getAnimale_id());////// change integration animale id
            ps.setString(2, t.getNom());
            ps.setString(3, t.getDescription());
            ps.setString(4, t.getTypeAnimale());
            t.setDateMaj(new Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(5, t.getDateMaj());
            ps.setInt(6, t.getId());
            System.out.println(ps);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(OperationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Maladie t) {
        String sql = "DELETE FROM `maladie` WHERE id=?";
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
    public List<Maladie> getAll() {
        String sql = "SELECT * FROM `maladie`";

        List<Maladie> list = new ArrayList<>();
        try {
            st = con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                Maladie maladie = new Maladie();
                maladie.setId(rs.getInt("id"));
                maladie.setAnimale_id(rs.getInt("animal_id"));
                maladie.setNom(rs.getString("nom"));
                maladie.setDescription(rs.getString("description"));
                maladie.setTypeAnimale(rs.getString("type_aniaml"));
                maladie.setDateCreation(rs.getTimestamp("date_creation"));
                maladie.setDateMaj(rs.getTimestamp("date_maj"));

                list.add(maladie);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OperationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Maladie getOne(int id) {
        Maladie maladie = new Maladie();

        try {
            String sql = "SELECT * FROM `maladie` where id =" + id;

            st = con.createStatement();

            rs = st.executeQuery(sql);

            rs.next();
            maladie.setId(rs.getInt("id"));
            maladie.setAnimale_id(rs.getInt("animal_id"));
            maladie.setNom(rs.getString("nom"));
            maladie.setDescription(rs.getString("description"));
            maladie.setTypeAnimale(rs.getString("type_aniaml"));
            maladie.setDateCreation(rs.getTimestamp("date_creation"));
            maladie.setDateMaj(rs.getTimestamp("date_maj"));

        } catch (SQLException ex) {
            Logger.getLogger(MaladieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maladie;
    }

}
