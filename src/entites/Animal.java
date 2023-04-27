/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author expert
 */
public class Animal {
     private int id;
      private String nom,race,genre,age,description;

    public Animal() {
    }

    public Animal(int id, String nom, String race) {
        this.id = id;
        this.nom = nom;
        this.race = race;
    }

    public Animal(int id, String nom, String race, String genre, String age, String description) {
        this.id = id;
        this.nom = nom;
        this.race = race;
        this.genre = genre;
        this.age = age;
        this.description = description;
    }

    public Animal(String nom, String race, String genre, String age, String description) {
        this.nom = nom;
        this.race = race;
        this.genre = genre;
        this.age = age;
        this.description = description;
    }

    

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", nom=" + nom + ", race=" + race + ", genre=" + genre + ", age=" + age + ", description=" + description + '}';
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

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
      
}
