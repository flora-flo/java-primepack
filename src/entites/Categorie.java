/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

public class Categorie {

    private int id;

    private String nom;

    private String icone;

    private String description;

    private float note;

    public Categorie() {
    }

    public Categorie(String nom, String icone, String description, float note) {
        this.nom = nom;
        this.icone = icone;
        this.description = description;
        this.note = note;
    }

    public Categorie(int id, String nom, String icone, String description, float note) {
        this.id = id;
        this.nom = nom;
        this.icone = icone;
        this.description = description;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return nom;
    }

}
