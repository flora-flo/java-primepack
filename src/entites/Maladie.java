/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Timestamp;

/**
 *
 * @author Nessim Melliti
 */
public class Maladie {

    private int id;
    private int animale_id;
    private String nom;
    private String description;
    private String typeAnimale;
    private Timestamp dateCreation = new Timestamp(System.currentTimeMillis());
    private Timestamp dateMaj;

    public Maladie() {
    }

    public Maladie(int id, int animale_id, String nom, String description, String typeAnimale, Timestamp dateMaj) {
        this.id = id;
        this.animale_id = animale_id;
        this.nom = nom;
        this.description = description;
        this.typeAnimale = typeAnimale;
        this.dateMaj = dateMaj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnimale_id() {
        return animale_id;
    }

    public void setAnimale_id(int animale_id) {
        this.animale_id = animale_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeAnimale() {
        return typeAnimale;
    }

    public void setTypeAnimale(String typeAnimale) {
        this.typeAnimale = typeAnimale;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Timestamp getDateMaj() {
        return dateMaj;
    }

    public void setDateMaj(Timestamp dateMaj) {
        this.dateMaj = dateMaj;
    }

    @Override
    public String toString() {
        return "Maladie{" + "id=" + id + ", animale_id=" + animale_id + ", nom=" + nom + ", description=" + description + ", typeAnimale=" + typeAnimale + ", dateCreation=" + dateCreation + ", dateMaj=" + dateMaj + '}';
    }

}
