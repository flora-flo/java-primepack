/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Timestamp;

public class Ticket {

    private int id;

    private String code;

    private float prix;

    private Timestamp date_debut;

    private Timestamp date_fin;
    private int nbJour;
    private Evenement evnement;
    private int idMembre;

    public Ticket() {
    }

    public Ticket(String code, float prix, Timestamp date_debut, Timestamp date_fin, int nbJour, Evenement evnement, int idMembre) {
        this.code = code;
        this.prix = prix;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbJour = nbJour;
        this.evnement = evnement;
        this.idMembre = idMembre;
    }

    public Ticket(int id, String code, float prix, Timestamp date_debut, Timestamp date_fin, int nbJour, Evenement evnement, int idMembre) {
        this.id = id;
        this.code = code;
        this.prix = prix;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbJour = nbJour;
        this.evnement = evnement;
        this.idMembre = idMembre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Timestamp getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Timestamp date_debut) {
        this.date_debut = date_debut;
    }

    public Timestamp getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Timestamp date_fin) {
        this.date_fin = date_fin;
    }

    public Evenement getEvnement() {
        return evnement;
    }

    public void setEvnement(Evenement evenement) {
        this.evnement = evenement;
    }

    public int getNbJour() {
        return nbJour;
    }

    public void setNbJour(int nbJour) {
        this.nbJour = nbJour;
    }

    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", code=" + code + ", prix=" + prix + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", nbJour=" + nbJour + ", evnement=" + evnement + ", idMembre=" + idMembre + '}';
    }

}
