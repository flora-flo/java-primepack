/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Categorie;
import entites.Produit;
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

public class ProduitService implements IService<Produit> {

    private Connection con;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;

    public ProduitService() {
        con = DataSource.getInstance().getCnx();
    }

    @Override
    public boolean insert(Produit produit) {
        String sql = "INSERT INTO `produit`( `categorie_id`, `prix`, `stock`, `disponibilite`, `nom`, `image`) VALUES (?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, produit.getCategory().getId());
            ps.setFloat(2, produit.getPrix());
            ps.setInt(3, produit.getStock());
            ps.setBoolean(4, produit.isIsavailable());

            ps.setString(5, produit.getNom());
            ps.setString(6, produit.getImage());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Produit produit) {
        String sql = "UPDATE `produit` SET `categorie_id`=?,`prix`=?,`stock`=?,`disponibilite`=?,`nom`=?,`image`=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, produit.getCategory().getId());
            ps.setFloat(2, produit.getPrix());
            ps.setInt(3, produit.getStock());
            ps.setBoolean(4, produit.isIsavailable());

            ps.setString(5, produit.getNom());
            ps.setString(6, produit.getImage());
            ps.setInt(7, produit.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Produit produit) {
        String sql = "DELETE FROM `produit` WHERE id=?";
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, produit.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Produit> getAll() {
        String sql = "SELECT * FROM `produit` INNER JOIN categorie on categorie.id=categorie_id	;";

        List<Produit> list = new ArrayList<>();
        try {
            st = con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(rs.getInt("categorie_id"));
                categorie.setDescription(rs.getString("description"));
                categorie.setIcone(rs.getString("icone"));
                categorie.setNom(rs.getString(10));
                categorie.setNote(rs.getFloat("note"));

                Produit produit = new Produit();
                produit.setId(rs.getInt("id"));
                produit.setCategory(categorie);
                produit.setImage(rs.getString("image"));
                produit.setIsavailable(rs.getBoolean("disponibilite"));
                produit.setNom(rs.getString("nom"));
                produit.setPrix(rs.getFloat("prix"));
                produit.setStock(rs.getInt("stock"));

                list.add(produit);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Produit getOne(int id) {
        String sql = "SELECT * FROM `produit` where id=" + id;
        Produit produit = new Produit();

        try {
            st = con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(rs.getInt("categorie_id"));
                categorie.setDescription(rs.getString("description"));
                categorie.setIcone(rs.getString("icone"));
                categorie.setNom(rs.getString(10));
                categorie.setNote(rs.getFloat("note"));

                produit.setId(rs.getInt("id"));
                produit.setCategory(categorie);
                produit.setImage(rs.getString("image"));
                produit.setIsavailable(rs.getBoolean("disponibilite"));
                produit.setNom(rs.getString("nom"));
                produit.setPrix(rs.getFloat("prix"));
                produit.setStock(rs.getInt("stock"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produit;
    }

}
