/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Categorie;
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

public class CategorieService implements IService<Categorie> {

    private Connection con;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;

    public CategorieService() {
        con = DataSource.getInstance().getCnx();
    }

    @Override
    public boolean insert(Categorie categorie) {
        String sql = "INSERT INTO `categorie`( `nom`, `icone`, `description`, `note`) VALUES (?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, categorie.getNom());
            ps.setString(2, categorie.getIcone());
            ps.setString(3, categorie.getDescription());
            ps.setFloat(4, categorie.getNote());

            System.out.println(ps);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Categorie categorie) {
        String sql = "UPDATE `categorie` SET `nom`=?,`icone`=?,`description`=?,`note`=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, categorie.getNom());
            ps.setString(2, categorie.getIcone());
            ps.setString(3, categorie.getDescription());
            ps.setFloat(4, categorie.getNote());
            ps.setInt(5, categorie.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Categorie categorie) {
        String sql = "DELETE FROM `categorie` WHERE id=?";
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, categorie.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Categorie> getAll() {
        String sql = "SELECT * FROM `categorie`";

        List<Categorie> list = new ArrayList<>();
        try {
            st = con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(rs.getInt("id"));
                categorie.setDescription(rs.getString("description"));
                categorie.setIcone(rs.getString("icone"));
                categorie.setNom(rs.getString("nom"));
                categorie.setNote(rs.getFloat("note"));

                list.add(categorie);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Categorie getOne(int id) {
        String sql = "SELECT * FROM `categorie`where id=" + id;
        Categorie categorie = new Categorie();

        try {
            st = con.createStatement();

            rs = st.executeQuery(sql);

            rs.next();
            categorie.setId(rs.getInt("id"));
            categorie.setDescription(rs.getString("description"));
            categorie.setIcone(rs.getString("icone"));
            categorie.setNom(rs.getString("nom"));
            categorie.setNote(rs.getFloat("note"));

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorie;
    }

}
