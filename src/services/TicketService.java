/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Evenement;
import entites.Ticket;
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
 * @author Houssem Charef
 */
public class TicketService implements IService<Ticket> {

    private Connection con;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;

    public TicketService() {
        con = DataSource.getInstance().getCnx();
    }

    @Override
    public boolean insert(Ticket ticket) {
        String sql = "INSERT INTO `ticket`(  `evenement_id`,`code`, `prix`, `date_debut`, `date_fin`, `membre_id`) VALUES (?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ticket.getEvnement().getId());
            ps.setString(2, ticket.getCode());
            ps.setFloat(3, ticket.getPrix());
            ps.setTimestamp(4, ticket.getDate_debut());
            ps.setTimestamp(5, ticket.getDate_fin());
            if (ticket.getIdMembre() == 0) {
                ps.setNull(6, 1);
            } else {
                ps.setInt(6, ticket.getIdMembre());
            }

            System.out.println(ps);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Ticket ticket) {
        String sql = "UPDATE `ticket` SET `evenement_id`=?,`code`=?,`prix`=?,`date_debut`=?,`date_fin`=?,`membre_id`=? WHERE `id`=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ticket.getEvnement().getId());
            ps.setString(2, ticket.getCode());
            ps.setFloat(3, ticket.getPrix());
            ps.setTimestamp(4, ticket.getDate_debut());
            ps.setTimestamp(5, ticket.getDate_fin());
            if (ticket.getIdMembre() == 0) {
                ps.setNull(6, 1);
            } else {
                ps.setInt(6, ticket.getIdMembre());
            }
            ps.setInt(7, ticket.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Ticket ticket) {
        String sql = "DELETE FROM `ticket` WHERE id=?";
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, ticket.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Ticket> getAll() {

        String sql = "SELECT * FROM `ticket` inner join evenement on evenement.id = ticket.evenement_id;";

        List<Ticket> list = new ArrayList<>();
        try {
            st = con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                Evenement evenement = new Evenement();

                evenement.setId(rs.getInt(8));
                evenement.setDate(rs.getTimestamp("date"));
                evenement.setDescription(rs.getString("description"));
                evenement.setNbPlace(rs.getInt("nb_place"));
                evenement.setNom(rs.getString("nom"));
                evenement.setSponsor(rs.getString("sponsor"));

                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setCode(rs.getString("code"));
                ticket.setDate_debut(rs.getTimestamp("date_debut"));
                ticket.setDate_fin(rs.getTimestamp("date_fin"));
                ticket.setEvnement(evenement);
                ticket.setIdMembre(rs.getInt("membre_id"));
                ticket.setPrix(rs.getFloat("prix"));

                list.add(ticket);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Ticket getOne(int id) {
        String sql = "SELECT * FROM `ticket` inner join evenement on evenement.id = ticket.evenement_id where ticket.id=" + id;
        Ticket ticket = new Ticket();

        try {
            st = con.createStatement();

            rs = st.executeQuery(sql);

            rs.next();
            Evenement evenement = new Evenement();

            evenement.setId(rs.getInt(8));
            evenement.setDate(rs.getTimestamp("date"));
            evenement.setDescription(rs.getString("description"));
            evenement.setNbPlace(rs.getInt("nb_place"));
            evenement.setNom(rs.getString("nom"));
            evenement.setSponsor(rs.getString("sponsor"));

            ticket.setId(rs.getInt("id"));
            ticket.setCode(rs.getString("code"));
            ticket.setDate_debut(rs.getTimestamp("date_debut"));
            ticket.setDate_fin(rs.getTimestamp("date_fin"));
            ticket.setEvnement(evenement);
            ticket.setIdMembre(rs.getInt("membre_id"));
            ticket.setPrix(rs.getFloat("prix"));

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ticket;
    }
}
